import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Table {
    String dbName = "KFC";
    String connectionURL = "jdbc:derby:" + dbName + ";create=true";
    Connection conn = null;
    Statement stm;
    PreparedStatement psstm;
    ResultSet myResult;
    String createString = "CREATE TABLE Products (productNo INT, productName VARCHAR(30), price INT)";

    String createString2 = "CREATE TABLE Ingredients (ingredientNo INT, ingredientName VARCHAR(30), Product_ID INT)";
    List<String> productList = new ArrayList<>();

    List<String> ingredients = new ArrayList<>();

    public void createTable() { //Create table, must be called start of every program to set the values
        try {
            conn = DriverManager.getConnection(connectionURL);
            stm = conn.createStatement();
            myResult = conn.getMetaData().getTables(null, null, null, new
                    String[]{"Products"});
            if (myResult.next())
                // Table exist, then no other created
                System.out.println("Tables already created");
            else {
                stm.execute(createString);
                stm.execute(createString2);
                System.out.println("Tables Products and Ingredients are created");
            }
            conn.close();
            stm.close();
        } catch (Throwable e) {
            System.out.println("Tables already created");
        }

    }

    public void insertProduct() throws SQLException {

        psstm = conn.prepareStatement("SELECT productName from Products");
        myResult = psstm.executeQuery();
        if (myResult.next()) {
            System.out.println("Products already exist");
        } else {
            try {
                psstm = conn.prepareStatement("INSERT INTO Products (productNo, productName, price) VALUES (?,?,?)");
                psstm.setInt(1, 10);
                psstm.setString(2, "Pizza");
                psstm.setInt(3, 20);
                psstm.executeUpdate();

                psstm.setInt(1, 20);
                psstm.setString(2, "Burger");
                psstm.setInt(3, 15);
                psstm.executeUpdate();

                psstm.setInt(1, 30);
                psstm.setString(2, "Coffee");
                psstm.setInt(3, 5);
                psstm.executeUpdate();

                System.out.println("Products are inserted.");

            } catch (Throwable e) {
                System.out.println("Exception thrown.");
            }

        }

    }

    public void insertIngredients() throws SQLException {
        psstm = conn.prepareStatement("SELECT ingredientName from Ingredients");
        myResult = psstm.executeQuery();
        ingredients.clear();
        if (myResult.next()) {
            System.out.println("Ingredients already exist");

        } else {
            try {
                psstm = conn.prepareStatement("INSERT INTO Ingredients (ingredientNo, ingredientName, Product_ID) VALUES (?,?,?)");
                psstm.setInt(1, 1);
                psstm.setString(2, "Chicken");
                psstm.setInt(3, 10);
                psstm.executeUpdate();

                psstm.setInt(1, 2);
                psstm.setString(2, "Kabab");
                psstm.setInt(3, 20);
                psstm.executeUpdate();

                psstm.setInt(1, 3);
                psstm.setString(2, "Pizza Dough");
                psstm.setInt(3, 10);
                psstm.executeUpdate();

                psstm.setInt(1, 4);
                psstm.setString(2, "Mayo");
                psstm.setInt(3, 10);
                psstm.executeUpdate();

                psstm.setInt(1, 5);
                psstm.setString(2, "Syed");
                psstm.setInt(3, 20);
                psstm.executeUpdate();

                psstm.setInt(1, 6);
                psstm.setString(2, "Milk");
                psstm.setInt(3, 30);
                psstm.executeUpdate();

                System.out.println("Ingredients are inserted.");

            } catch (Throwable e) {
                System.out.println("Exception thrown");
            }
        }
    }

    public String[] getProducts() throws SQLException {
        productList.clear();

        try {
            psstm = conn.prepareStatement("SELECT productName from Products");
            myResult = psstm.executeQuery();
            while (myResult.next()) {
                productList.add(myResult.getString("productName"));
            }
        } catch (Throwable e) {
            System.out.println("Exception thrown");
        }

        return productList.toArray(new String[0]);
    }

    public String[] getIngredientsByProduct(String productName) throws SQLException {
        ingredients.clear(); // Clear previous ingredients list
        int productNo;

        try {
            psstm = conn.prepareStatement("SELECT productNo FROM Products where productName = ?");
            psstm.setString(1, productName);
            myResult = psstm.executeQuery();
            myResult.next();
            productNo = myResult.getInt("productNo");

            psstm = conn.prepareStatement("SELECT ingredientName FROM Ingredients WHERE Product_ID = ?");
            psstm.setInt(1, productNo);
            myResult = psstm.executeQuery();

            while (myResult.next()) {
                ingredients.add(myResult.getString("ingredientName"));
            }
        } catch (Throwable e) {
            System.out.println("Exception thrown while getting ingredients");
        }

        return ingredients.toArray(new String[0]);
    }
}
