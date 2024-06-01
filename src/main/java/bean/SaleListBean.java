package bean;

import java.io.Serializable;

public class SaleListBean implements Serializable{
	
	    private int cusId;
	    private String cusName;
	    private String date;
	    private int totalPrice;
	    
		public int getCusId() {
			return cusId;
		}
		public void setCusId(int cusId) {
			this.cusId = cusId;
		}
		public String getCusName() {
			return cusName;
		}
		public void setCusName(String cusName) {
			this.cusName = cusName;
		}
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public int getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(int totalPrice) {
			this.totalPrice = totalPrice;
		}
	    
		
	    

}
