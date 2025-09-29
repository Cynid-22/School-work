public class PC {
    private String assetTag;
    private String model;

    public PC(String assetTag, String model) {
        this.assetTag = assetTag;
        this.model = model;
    }

    public String toString() {
        return "\n  - PC Model: " + model + " (Tag: " + assetTag + ")";
    }
}