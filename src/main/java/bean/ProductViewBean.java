package bean;

import java.io.Serializable;

public class ProductViewBean implements Serializable {
	private String pro_id;
	private String pi_id;
	private String pd_id;
	private int wholesale;
	private int set_quantity;


	public String getProId() {
		return pro_id;
	}

	public void setProId(String pro_id) {
		this.pro_id = pro_id;
	}
	
	

	
	
	
	
	
	public String getOrderId() {
		return pi_id;
	}

	public void setOrderId(String pi_id) {
		this.pi_id = pi_id;
	}
	
	
	
	

	
	public String getPdId() {
		return pd_id;
	}

	public void setPdId(String pd_id) {
		this.pd_id = pd_id;
	}
	
	
	
	public int getWholesale() {
		return wholesale;
	}

	public void setWholesale(int wholesale) {
		this.wholesale = wholesale;
	}
	
	
	
	
	public int getSetQuantity() {
		return set_quantity;
	}

	public void setSetQuantity(int set_quantity) {
		this.set_quantity = set_quantity;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	
}
