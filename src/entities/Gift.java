package entities;

import enums.Category;

public final class Gift {
    private String productName;
    private Double price;
    private Category category;
    private int quantity;

    public Gift() {
    }

    /**
     * Decrease the quantity of a gift from Santa's bag
     */
    public void decreaseQuantity() {
        quantity--;
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
}
