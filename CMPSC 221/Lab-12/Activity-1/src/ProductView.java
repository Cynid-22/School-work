public class ProductView {
    public void printProductDetails(String productId, String productName, int quantity) {
        System.out.println("Product Details:");
        System.out.println("  ID: " + productId);
        System.out.println("  Name: " + productName);
        System.out.println("  Quantity: " + quantity);
        System.out.println("--------------------");
    }
}