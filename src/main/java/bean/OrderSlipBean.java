package bean;

import java.io.Serializable;

public class OrderSlipBean implements Serializable{
	private static final long serialVersionUID = 1L;

	//field
	private int orderSlipId;
	private int orderId;
	private String proId;
	private int orderQty;
	private int cancelQty;
	private int refundQty;
	
	//constructor
	public OrderSlipBean(){}

	public int getOrderSlipId() {
		return orderSlipId;
	}

	//setter and getter
	public void setOrderSlipId(int orderSlipId) {
		this.orderSlipId = orderSlipId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public int getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}

	public int getCancelQty() {
		return cancelQty;
	}

	public void setCancelQty(int cancelQty) {
		this.cancelQty = cancelQty;
	}

	public int getRefundQty() {
		return refundQty;
	}

	public void setRefundQty(int refundQty) {
		this.refundQty = refundQty;
	}
}
