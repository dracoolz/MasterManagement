package bean;

import java.io.Serializable;

public class ProductViewBean implements Serializable {
    
    private int productId;   
    private String productName; 
    private int scId;           
    private String scCategory;  
    private int bcId;           
    private String bcCategory;  

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getScId() {
        return scId;
    }

    public void setScId(int scId) {
        this.scId = scId;
    }

    public String getScCategory() {
        return scCategory;
    }

    public void setScCategory(String scCategory) {
        this.scCategory = scCategory;
    }

    public int getBcId() {
        return bcId;
    }

    public void setBcId(int bcId) {
        this.bcId = bcId;
    }

    public String getBcCategory() {
        return bcCategory;
    }

    public void setBcCategory(String bcCategory) {
        this.bcCategory = bcCategory;
    }
}