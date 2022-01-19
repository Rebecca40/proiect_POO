package fileio.output;

import entities.Gift;
import enums.Category;

public final class GiftOutput {
    private String productName;
    private Double price;
    private Category category;

    public GiftOutput(final Gift gift) {
        productName = gift.getProductName();
        price = gift.getPrice();
        category = gift.getCategory();
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
