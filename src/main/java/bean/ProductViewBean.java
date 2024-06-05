package bean;

import java.util.List;

public class ProductViewBean {
    private String productId;
    private String productName;
    private List<String> smallCategories;
    private int retailPrice;

    // Getter and Setter methods
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getSmallCategories() {
        return smallCategories;
    }

    public void setSmallCategories(List<String> smallCategories) {
        this.smallCategories = smallCategories;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }
}