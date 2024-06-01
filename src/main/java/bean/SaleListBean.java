package bean;

import java.io.Serializable;

public class SaleListBean implements Serializable{
	
	    private int CusId;
	    private String CusName;
	    private String Date;
	    private int TotalPrice;
	    
		public int getCusId() {
			return CusId;
		}
		public void setCusId(int cusId) {
			CusId = cusId;
		}
		public String getCusName() {
			return CusName;
		}
		public void setCusName(String cusName) {
			CusName = cusName;
		}
		public String getDate() {
			return Date;
		}
		public void setDate(String date) {
			Date = date;
		}
		public int getTotalPrice() {
			return TotalPrice;
		}
		public void setTotalPrice(int totalPrice) {
			TotalPrice = totalPrice;
		}
	    

}
