public class MVCProductDemo {
    public static void main(String[] args) {

        Product model = retrieveProductFromDatabase();
        ProductView view = new ProductView();
        ProductController controller = new ProductController(model, view);

        System.out.println("Initial product data:");
        controller.updateView();

        controller.setProductName("Gaming Keyboard");
        controller.setProductQuantity(75);

        System.out.println("Updated product data:");
        controller.updateView();
    }

    private static Product retrieveProductFromDatabase() {
        Product product = new Product();
        product.setProductId("Logitech G Pro x Superlight");
        product.setProductName("Wireless Mouse");
        product.setQuantity(120);
        return product;
    }
}