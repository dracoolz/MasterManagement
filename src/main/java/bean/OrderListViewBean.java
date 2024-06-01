package bean;

import java.io.Serializable;

public class OrderListViewBean implements Serializable{
	
	    private int OrderId;
	    private String Date;
	    private String CustomerName;
	    private int TotalAmountMoney;
	    
		public int getOrderId() {
			return OrderId;
		}
		public void setOrderId(int orderId) {
			OrderId = orderId;
		}
		public String getDate() {
			return Date;
		}
		public void setDate(String date) {
			Date = date;
		}
		public String getCustomerName() {
			return CustomerName;
		}
		public void setCustomerName(String customerName) {
			CustomerName = customerName;
		}
		public int getTotalAmountMoney() {
			return TotalAmountMoney;
		}
		public void setTotalAmountMoney(int totalAmountMoney) {
			TotalAmountMoney = totalAmountMoney;
		}

}
