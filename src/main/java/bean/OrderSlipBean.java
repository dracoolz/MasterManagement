package bean;

import java.io.Serializable;

public class OrderSlipBean implements Serializable {
	private int os_id;
	private int order_id;
	private String pro_id;
	private int quantity;


	public int getOsId() {
		return os_id;
	}

	public void setOsId(int os_id) {
		this.os_id = os_id;
	}
	
	

	public int getOrderId() {
		return order_id;
	}

	public void setOrderId(int order_id) {
		this.order_id = order_id;
	}
	
	
	
	
	
	public String getProId() {
		return pro_id;
	}

	public void setProId(String pro_id) {
		this.pro_id = pro_id;
	}
	
	
	
	
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

	

	
	
}
