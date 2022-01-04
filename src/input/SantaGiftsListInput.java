package input;

import enums.Category;

public final class SantaGiftsListInput {
    private String productName;
    private Double price;
    private String category;

//    public SantaGiftsListInput(String productName, Double price, String category) {
//        this.productName = productName;
//        this.price = price;
//        this.category = category;
//    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "\nSantaGiftsListInput{" +
                "\nproductName='" + productName + '\'' +
                ",\n price=" + price +
                ",\n category=" + category +
                '}';
    }
}
