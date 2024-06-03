package bean;

import java.io.Serializable;

public class BigCategoryBean implements Serializable {
	
	private int bc_id;
	private String bc_category;
	
	public int getBc_id() {
		return bc_id;
	}
	
	public void setBc_id(int bc_id) {
		this.bc_id = bc_id;
	}
	
	public String getBc_category() {
		return bc_category;
	}
	
	public void setBc_category(String bc_category) {
		this.bc_category = bc_category;
	}
}
