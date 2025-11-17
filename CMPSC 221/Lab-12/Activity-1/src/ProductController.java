public class ProductController {
    private Product model;
    private ProductView view;

    public ProductController(Product model, ProductView view) {
        this.model = model;
        this.view = view;
    }

    public void setProductId(String id) {
        model.setProductId(id);
    }

    public String getProductId() {
        return model.getProductId();
    }

    public void setProductName(String name) {
        model.setProductName(name);
    }

    public String getProductName() {
        return model.getProductName();
    }

    public void setProductQuantity(int quantity) {
        model.setQuantity(quantity);
    }

    public int getProductQuantity() {
        return model.getQuantity();
    }

    public void updateView() {
        view.printProductDetails(model.getProductId(), model.getProductName(), model.getQuantity());
    }
}