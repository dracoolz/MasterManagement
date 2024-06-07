package bean;

import java.io.Serializable;

public class OrderListBean implements Serializable{
	private static final long serialVersionUID = 1L;

	//field
	private int orderId;
	private int cusId;
	private String orderDate;
	private String cancelComment;
	private String refundComment;
	
	private String customerName;
    private int totalAmountMoney; //注文単位合計金額
    
    // getter and setter
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getCancelComment() {
		return cancelComment;
	}
	public void setCancelComment(String cancelComment) {
		this.cancelComment = cancelComment;
	}
	public String getRefundComment() {
		return refundComment;
	}
	public void setRefundComment(String refundComment) {
		this.refundComment = refundComment;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getTotalAmountMoney() {
		return totalAmountMoney;
	}
	public void setTotalAmountMoney(int totalAmountMoney) {
		this.totalAmountMoney = totalAmountMoney;
	}
	
}