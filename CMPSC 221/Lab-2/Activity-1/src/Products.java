public class Products {
    private int productID;
    private String productDescription;
    private boolean productAvailability;
    private int quantity;
    private String units;
    private float unitPrice;

    // Default constructor
    public Products() {
        productID = 123;
        productDescription = "Milk";
        productAvailability = true;
        quantity = 10;
        units = "Gallon";
        unitPrice = 4f;
    }

    // Copy constructor
    public Products(int ID, String desc, boolean available, int qty, String unit, float price) {
        productID = ID;
        productDescription = desc;
        productAvailability = available;
        quantity = qty;
        units = unit;
        unitPrice = price;
    }

    // Display product info
    public void getProduct() {
        System.out.println("\nProduct ID: " + productID);
        System.out.println("Product Description: " + productDescription);
        System.out.println("Product is available: " + productAvailability);
        System.out.println("Quantity: " + quantity);
        System.out.println("Unit/Scale: " + units);
        System.out.println("Unit Price: " + unitPrice);
        System.out.println("Inventory Cost: $" + inventoryCost()); // added this line to fit with the lab assignment
    }

    // Update product info
    public void setProduct(int ID, String desc, boolean available, int qty, String unit, float price) {
        productID = ID;
        productDescription = desc;
        productAvailability = available;
        quantity = qty;
        units = unit;
        unitPrice = price;
    }

    // Purchase product
    public void Purchase(int qty, float uprice) {
        productAvailability = true;
        unitPrice = (quantity * unitPrice + qty * uprice) / (quantity + qty);
        quantity = quantity + qty;
    }

    // Sale product with warning if quantity insufficient
    public void Sale(int qty) {
        if (qty > quantity) {
            System.out.println("Warning: Sale quantity exceeds existing stock! Only " + quantity + " available.");
            quantity = 0;
            productAvailability = false;
        } else {
            quantity = quantity - qty;
            if (quantity == 0) {
                productAvailability = false;
            }
        }
    }

    // Check availability
    public boolean checkAvailability() {
        if (quantity <= 0) {
            productAvailability = false;
        }
        return productAvailability;
    }

    // Compute inventory cost
    public float inventoryCost() {
        return quantity * unitPrice;
    }
}