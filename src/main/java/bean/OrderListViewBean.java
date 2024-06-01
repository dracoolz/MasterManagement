package bean;

import java.io.Serializable;

public class OrderListViewBean implements Serializable{
	
	    private int orderId;
	    private String date;
	    private String customerName;
	    private int totalAmountMoney;
	    
		public int getOrderId() {
			return orderId;
		}
		public void setOrderId(int orderId) {
			this.orderId = orderId;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
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
