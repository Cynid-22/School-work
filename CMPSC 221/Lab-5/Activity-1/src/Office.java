public class Office {
    private String buildingName;
    private String roomNumber;

    public Office(String buildingName, String roomNumber) {
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
    }

    public String toString() {
        return "Office: " + buildingName + ", Room " + roomNumber;
    }
}