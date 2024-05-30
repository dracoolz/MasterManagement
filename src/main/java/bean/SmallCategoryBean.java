package bean;

import java.io.Serializable;

public class SmallCategoryBean implements Serializable {
	
	private int sc_id;
	private int bc_id;
	private String sc_category;
	
	
	public int getSc_id() {
		return sc_id;
	}
	public void setSc_id(int sc_id) {
		this.sc_id = sc_id;
	}
	public int getBc_id() {
		return bc_id;
	}
	public void setBc_id(int bc_id) {
		this.bc_id = bc_id;
	}
	public String getSc_category() {
		return sc_category;
	}
	public void setSc_category(String sc_category) {
		this.sc_category = sc_category;
	}
	
}
