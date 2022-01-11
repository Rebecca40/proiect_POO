package entities;

import enums.Category;

public final class Gift {
    private String productName;
    private Double price;
    private Category category;
    private int quantity;

    public Gift() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Gift{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", quantity=" + quantity +
                '}';
    }
}
