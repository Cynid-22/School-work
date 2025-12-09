import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerTextSim {
    enum State { IDLE, ASK_NAME, ASK_PHONE, ASK_REST, SELECT_BRANCH, ASK_HEADCOUNT, ASK_TIME, CONFIRM, ASK_PHONE_CANCEL }

    private State state = State.IDLE;
    private DatabaseManager db;

    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("[H:mm][HH:mm]");

    private String tempName;
    private String tempPhone;

    private int tempRestId = -1;
    private String tempRestNameDisplay;

    private int tempCount;
    private LocalTime tempStart;
    private long calculatedDurationMinutes;

    private LocalTime openTime;
    private LocalTime closeTime;
    private int targetTableCap;
    private int totalTargetTables;

    private List<DatabaseManager.RestaurantEntry> foundBranches;

    public CustomerTextSim(DatabaseManager db) {
        this.db = db;
    }

    public void startLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- SMS SIMULATOR STARTED ---");
        System.out.println("System: Send 'Book' to start or 'Cancel' to cancel a reservation.");

        while(true) {
            System.out.print("Customer: ");
            String input = scanner.nextLine().trim();
            if(input.equalsIgnoreCase("exit")) break;

            processInput(input);
        }
    }

    private void processInput(String input) {
        String lower = input.toLowerCase();

        switch(state) {
            case IDLE:
                if(lower.contains("book")) {
                    System.out.println("System: Please enter your name");
                    state = State.ASK_NAME;
                } else if (lower.contains("cancel")) {
                    System.out.println("System: Please enter your phone number to cancel");
                    state = State.ASK_PHONE_CANCEL;
                } else {
                    System.out.println("System: To start, type 'Book' or 'Cancel'.");
                }
                break;

            case ASK_PHONE_CANCEL:
                String[] resDetails = db.getReservationDetailsByPhone(input);
                if (resDetails != null) {
                    db.deleteReservationByPhone(input);
                    System.out.println("System: Your reservation for " + resDetails[0] +
                            " from " + resDetails[1] + " to " + resDetails[2] +
                            " has been canceled");
                } else {
                    System.out.println("System: No active reservation found for this phone number.");
                }
                state = State.IDLE;
                break;

            case ASK_NAME:
                tempName = input;
                System.out.println("System: Please enter your phone number");
                state = State.ASK_PHONE;
                break;

            case ASK_PHONE:
                tempPhone = input;
                if(db.checkActiveReservation(tempPhone)) {
                    System.out.println("System: This phone number already has an active reservation. Limit 1 per customer.");
                    state = State.IDLE;
                } else {
                    System.out.println("System: Please choose the restaurant you would like to book a table at");
                    state = State.ASK_REST;
                }
                break;

            case ASK_REST:
                foundBranches = db.findRestaurantsByName(input);
                if (foundBranches.isEmpty()) {
                    System.out.println("System: Restaurant not found. Try again.");
                } else if (foundBranches.size() == 1) {
                    tempRestId = foundBranches.get(0).id;
                    tempRestNameDisplay = foundBranches.get(0).name;
                    System.out.println("System: Please enter headcount, including children");
                    state = State.ASK_HEADCOUNT;
                } else {
                    System.out.println("System: Multiple locations found. Please enter the number of the one you want:");
                    for (int i = 0; i < foundBranches.size(); i++) {
                        System.out.println((i + 1) + ". " + foundBranches.get(i).name + " - " + foundBranches.get(i).location);
                    }
                    state = State.SELECT_BRANCH;
                }
                break;

            case SELECT_BRANCH:
                try {
                    int choice = Integer.parseInt(input);
                    if (choice >= 1 && choice <= foundBranches.size()) {
                        DatabaseManager.RestaurantEntry selected = foundBranches.get(choice - 1);
                        tempRestId = selected.id;
                        tempRestNameDisplay = selected.name;
                        System.out.println("System: Selected " + selected.location + ". Please enter headcount, including children");
                        state = State.ASK_HEADCOUNT;
                    } else {
                        System.out.println("System: Invalid number. Try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("System: Please enter a number.");
                }
                break;

            case ASK_HEADCOUNT:
                try {
                    tempCount = Integer.parseInt(input);
                    targetTableCap = db.getBestFitCapacity(tempRestId, tempCount);
                    totalTargetTables = db.getSpecificTableCount(tempRestId, tempCount);

                    if(targetTableCap != -1 && totalTargetTables > 0) {
                        String[] hours = db.getRestaurantHours(tempRestId);
                        try {
                            openTime = LocalTime.parse(hours[0], TIME_FMT);
                            closeTime = LocalTime.parse(hours[1], TIME_FMT);
                        } catch (Exception e) {
                            System.out.println("System Debug: Error parsing hours.");
                            openTime = LocalTime.of(12, 0);
                            closeTime = LocalTime.of(22, 0);
                        }

                        System.out.println("System: Checking availability...");
                        printAvailableWindows();

                        System.out.println("System: Please enter time (HH:MM format, e.g., 17:00)");
                        state = State.ASK_TIME;
                    } else {
                        System.out.println("System: We do not have tables suitable for " + tempCount + ". Resetting.");
                        state = State.IDLE;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("System: Please enter a valid number.");
                }
                break;

            case ASK_TIME:
                try {
                    tempStart = LocalTime.parse(input, TIME_FMT);

                    if (tempStart.isBefore(openTime) || tempStart.isAfter(closeTime)) {
                        System.out.println("System: We are closed. Open: " + openTime + " - Close: " + closeTime);
                        return;
                    }

                    calculatedDurationMinutes = checkBookingDuration(tempStart);

                    if (calculatedDurationMinutes < 45) {
                        System.out.println("System: Sorry, we cannot accept a booking at " + tempStart +
                                ". The available slot is less than 45 minutes.");
                    } else if (calculatedDurationMinutes < 120) {
                        LocalTime end = tempStart.plusMinutes(calculatedDurationMinutes);
                        System.out.println("System: You are booking a shorter time from " + tempStart + " to " + end +
                                " (" + formatDuration(calculatedDurationMinutes) + "). Do you want to continue (Yes/No)");
                        state = State.CONFIRM;
                    } else {
                        LocalTime end = tempStart.plusMinutes(120);
                        System.out.println("System: The reservation will be booked for 2 hours from " + tempStart +
                                " to " + end + ". Do you want to continue (Yes/No)");
                        state = State.CONFIRM;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("System: Invalid time format. Use HH:MM");
                }
                break;

            case CONFIRM:
                if(lower.startsWith("y")) {
                    long finalDuration = Math.min(120, calculatedDurationMinutes);
                    LocalTime end = tempStart.plusMinutes(finalDuration);
                    db.addReservation(tempRestId, tempName, tempPhone, tempCount, tempStart.toString(), end.toString());
                    System.out.println("System: You have successfully booked a table.");
                } else {
                    System.out.println("System: Booking cancelled.");
                }
                state = State.IDLE;
                break;
        }
    }

    private String formatDuration(long minutes) {
        long h = minutes / 60;
        long m = minutes % 60;
        if (h > 0) return h + "hr " + m + "mins";
        return m + "mins";
    }

    private long checkBookingDuration(LocalTime start) {
        int[] timeline = buildTimeline();
        int startIndex = timeToIdx(start);

        if (startIndex < 0 || startIndex >= timeline.length || timeline[startIndex] >= totalTargetTables) {
            return 0;
        }

        int durationMins = 0;
        boolean hitBlockage = false;

        for (int i = startIndex; i < timeline.length; i++) {
            if (timeline[i] >= totalTargetTables) {
                hitBlockage = true;
                break;
            }
            durationMins++;
        }

        if (hitBlockage) {
            durationMins -= 15;
        }

        return Math.max(0, durationMins);
    }

    private void printAvailableWindows() {
        int[] timeline = buildTimeline();

        List<String> windows = new ArrayList<>();
        LocalTime windowStart = null;

        for (int i = 0; i < timeline.length; i++) {
            boolean isFree = timeline[i] < totalTargetTables;
            LocalTime currentTime = idxToTime(i);

            if (isFree) {
                if (windowStart == null) windowStart = currentTime;
            } else {
                if (windowStart != null) {
                    LocalTime customerEndTime = currentTime.minusMinutes(15);
                    if (windowStart.isBefore(customerEndTime)) {
                        long gap = ChronoUnit.MINUTES.between(windowStart, customerEndTime);
                        if (gap >= 45) {
                            windows.add(windowStart + " to " + customerEndTime);
                        }
                    }
                    windowStart = null;
                }
            }
        }

        if (windowStart != null) {
            long gap = ChronoUnit.MINUTES.between(windowStart, closeTime);
            if (gap >= 45) {
                windows.add(windowStart + " to " + closeTime);
            }
        }

        if (windows.isEmpty()) {
            System.out.println("System: No tables available today.");
        } else {
            System.out.println("System: Here is a list of time available for booking: " + String.join(", ", windows));
        }
    }

    private int[] buildTimeline() {
        long totalMins = ChronoUnit.MINUTES.between(openTime, closeTime);
        if(totalMins <= 0) return new int[0];

        int[] timeline = new int[(int)totalMins + 1];

        List<String[]> resList = db.getAllReservations(tempRestId);

        for (String[] res : resList) {
            try {
                int resHeadCount = Integer.parseInt(res[2]);
                int resBestFit = db.getBestFitCapacity(tempRestId, resHeadCount);

                if (resBestFit == targetTableCap) {
                    LocalTime rStart = LocalTime.parse(res[0], TIME_FMT);
                    LocalTime rEnd = LocalTime.parse(res[1], TIME_FMT);

                    LocalTime effectiveEnd = rEnd;
                    if (rEnd.isBefore(closeTime)) {
                        effectiveEnd = rEnd.plusMinutes(15);
                    }

                    int sIdx = timeToIdx(rStart);
                    int eIdx = timeToIdx(effectiveEnd);

                    for (int i = sIdx; i < eIdx; i++) {
                        if (i >= 0 && i < timeline.length) {
                            timeline[i]++;
                        }
                    }
                }
            } catch (Exception e) {}
        }
        return timeline;
    }

    private int timeToIdx(LocalTime t) {
        return (int) ChronoUnit.MINUTES.between(openTime, t);
    }

    private LocalTime idxToTime(int idx) {
        return openTime.plusMinutes(idx);
    }
}