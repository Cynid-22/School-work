import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:derby:RestaurantDB;create=true";

    public DatabaseManager() {
        initDB();
    }

    // --- INNER CLASS FOR GUI/LISTS ---
    public static class RestaurantEntry {
        public int id;
        public String name;
        public String location;

        public RestaurantEntry(int id, String name, String location) {
            this.id = id;
            this.name = name;
            this.location = location;
        }

        @Override
        public String toString() {
            return name + " - " + location + " (ID: " + id + ")";
        }
    }

    private void initDB() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            try {
                stmt.execute("CREATE TABLE Restaurants (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, Name VARCHAR(50), Location VARCHAR(100), OpenTime VARCHAR(10), CloseTime VARCHAR(10))");
            } catch (SQLException e) { if (!e.getSQLState().equals("X0Y32")) throw e; }

            try {
                stmt.execute("CREATE TABLE Tables (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, RestID INT, Capacity INT)");
            } catch (SQLException e) { if (!e.getSQLState().equals("X0Y32")) throw e; }

            try {
                stmt.execute("CREATE TABLE Reservations (ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY, RestID INT, CustomerName VARCHAR(50), Phone VARCHAR(20), HeadCount INT, StartTime VARCHAR(10), EndTime VARCHAR(10))");
            } catch (SQLException e) { if (!e.getSQLState().equals("X0Y32")) throw e; }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // --- Admin Features ---
    public void addRestaurant(String name, String loc) {
        String sql = "INSERT INTO Restaurants (Name, Location, OpenTime, CloseTime) VALUES (?, ?, '07:30', '22:00')";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, loc);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void deleteRestaurant(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            Statement stmt = conn.createStatement();
            stmt.execute("DELETE FROM Tables WHERE RestID = " + id);
            stmt.execute("DELETE FROM Reservations WHERE RestID = " + id);
            stmt.execute("DELETE FROM Restaurants WHERE ID = " + id);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void updateRestaurantDetails(int restId, String newName, String loc, String open, String close) {
        String sql = "UPDATE Restaurants SET Name=?, Location=?, OpenTime=?, CloseTime=? WHERE ID=?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, loc);
            pstmt.setString(3, open);
            pstmt.setString(4, close);
            pstmt.setInt(5, restId);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void updateTableConfig(int restId, int capacity, int targetQuantity) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String countSql = "SELECT ID FROM Tables WHERE RestID = ? AND Capacity = ?";
            PreparedStatement pstmt = conn.prepareStatement(countSql);
            pstmt.setInt(1, restId);
            pstmt.setInt(2, capacity);
            ResultSet rs = pstmt.executeQuery();

            List<Integer> existingIDs = new ArrayList<>();
            while (rs.next()) existingIDs.add(rs.getInt("ID"));

            int currentCount = existingIDs.size();

            if (currentCount < targetQuantity) {
                int toAdd = targetQuantity - currentCount;
                String insertSql = "INSERT INTO Tables (RestID, Capacity) VALUES (?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                for (int i = 0; i < toAdd; i++) {
                    insertStmt.setInt(1, restId);
                    insertStmt.setInt(2, capacity);
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch();
            } else if (currentCount > targetQuantity) {
                int toRemove = currentCount - targetQuantity;
                String delSql = "DELETE FROM Tables WHERE ID = ?";
                PreparedStatement delStmt = conn.prepareStatement(delSql);
                for (int i = 0; i < toRemove; i++) {
                    delStmt.setInt(1, existingIDs.get(i));
                    delStmt.addBatch();
                }
                delStmt.executeBatch();
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // --- Helper Getters ---

    public List<RestaurantEntry> getAllRestaurants() {
        List<RestaurantEntry> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT ID, Name, Location FROM Restaurants");
            while (rs.next()) {
                list.add(new RestaurantEntry(rs.getInt("ID"), rs.getString("Name"), rs.getString("Location")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<RestaurantEntry> findRestaurantsByName(String name) {
        List<RestaurantEntry> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT ID, Name, Location FROM Restaurants WHERE LOWER(Name) = ?")) {
            pstmt.setString(1, name.toLowerCase());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(new RestaurantEntry(rs.getInt("ID"), rs.getString("Name"), rs.getString("Location")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public String getRestaurantInfo(int id) {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Restaurants WHERE ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                sb.append("ID: ").append(rs.getInt("ID")).append("\n");
                sb.append("Name: ").append(rs.getString("Name")).append("\n");
                sb.append("Location: ").append(rs.getString("Location")).append("\n");
                sb.append("Hours: ").append(rs.getString("OpenTime")).append(" - ").append(rs.getString("CloseTime")).append("\n");

                Statement tStmt = conn.createStatement();
                ResultSet trs = tStmt.executeQuery("SELECT Capacity, COUNT(*) as Qty FROM Tables WHERE RestID=" + id + " GROUP BY Capacity");
                sb.append("Tables:\n");
                while(trs.next()) {
                    sb.append("  Cap ").append(trs.getInt("Capacity")).append(": ").append(trs.getInt("Qty")).append("\n");
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return sb.toString();
    }

    // NEW: Used to auto-fill the edit boxes
    public String[] getRestaurantDetailsForEdit(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT Name, Location, OpenTime, CloseTime FROM Restaurants WHERE ID = ?")) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new String[]{
                        rs.getString("Name"),
                        rs.getString("Location"),
                        rs.getString("OpenTime"),
                        rs.getString("CloseTime")
                };
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // --- Reservation Logic ---
    public void addReservation(int restId, String custName, String phone, int headcount, String start, String end) {
        String sql = "INSERT INTO Reservations (RestID, CustomerName, Phone, HeadCount, StartTime, EndTime) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, restId);
            pstmt.setString(2, custName);
            pstmt.setString(3, phone);
            pstmt.setInt(4, headcount);
            pstmt.setString(5, start);
            pstmt.setString(6, end);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public boolean checkActiveReservation(String phone) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT Count(*) FROM Reservations WHERE Phone = ?")) {
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // NEW: Get details for cancellation message
    // Returns [RestaurantName, StartTime, EndTime] or null if not found
    public String[] getReservationDetailsByPhone(String phone) {
        String sql = "SELECT r.Name, res.StartTime, res.EndTime FROM Reservations res " +
                "JOIN Restaurants r ON res.RestID = r.ID WHERE res.Phone = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new String[]{
                        rs.getString("Name"),
                        rs.getString("StartTime"),
                        rs.getString("EndTime")
                };
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    // NEW: Delete reservation by phone
    public void deleteReservationByPhone(String phone) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Reservations WHERE Phone = ?")) {
            pstmt.setString(1, phone);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public String getReservationsForRestaurant(int restId) {
        StringBuilder sb = new StringBuilder();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM Reservations WHERE RestID=" + restId + " ORDER BY StartTime ASC");
            while(rs.next()) {
                sb.append(rs.getString("StartTime")).append(" - ")
                        .append(rs.getString("CustomerName")).append(" (")
                        .append(rs.getInt("HeadCount")).append(" ppl) [")
                        .append(rs.getString("Phone")).append("]\n");
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return sb.toString();
    }

    public boolean checkTableExists(int restId, int headcount) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT Count(*) FROM Tables WHERE RestID=? AND Capacity >= ?")) {
            pstmt.setInt(1, restId);
            pstmt.setInt(2, headcount);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) return rs.getInt(1) > 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }

    // --- TIME CALCULATION HELPERS ---

    public String[] getRestaurantHours(int restId) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT OpenTime, CloseTime FROM Restaurants WHERE ID = ?")) {
            pstmt.setInt(1, restId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new String[]{rs.getString("OpenTime"), rs.getString("CloseTime")};
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public int getSpecificTableCount(int restId, int headcount) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            PreparedStatement p1 = conn.prepareStatement("SELECT Capacity FROM Tables WHERE RestID=? AND Capacity >= ? ORDER BY Capacity ASC FETCH FIRST 1 ROWS ONLY");
            p1.setInt(1, restId);
            p1.setInt(2, headcount);
            ResultSet rs1 = p1.executeQuery();
            if(!rs1.next()) return 0;

            int targetCap = rs1.getInt(1);

            PreparedStatement p2 = conn.prepareStatement("SELECT Count(*) FROM Tables WHERE RestID=? AND Capacity=?");
            p2.setInt(1, restId);
            p2.setInt(2, targetCap);
            ResultSet rs2 = p2.executeQuery();
            if(rs2.next()) return rs2.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return 0;
    }

    public int getBestFitCapacity(int restId, int headcount) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement("SELECT Capacity FROM Tables WHERE RestID=? AND Capacity >= ? ORDER BY Capacity ASC FETCH FIRST 1 ROWS ONLY")) {
            pstmt.setInt(1, restId);
            pstmt.setInt(2, headcount);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) return rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    public List<String[]> getAllReservations(int restId) {
        List<String[]> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT StartTime, EndTime, HeadCount FROM Reservations WHERE RestID=" + restId);
            while(rs.next()) {
                list.add(new String[]{rs.getString("StartTime"), rs.getString("EndTime"), String.valueOf(rs.getInt("HeadCount"))});
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}