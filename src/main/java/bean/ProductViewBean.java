package bean;

import java.io.Serializable;

public class ProductViewBean implements Serializable {
	
	private int scId;
	private String scCategory;
	private int bcId ;
	private String bcCategory;
	private int categoryId;



	public int getScId() {
		return scId;
	}

	public void setScId(int scId) {
		this.scId = scId;
	}
	
	


	
	public String scCategory() {
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
	
	
	
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
}
