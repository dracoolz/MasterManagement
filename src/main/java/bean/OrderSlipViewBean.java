package bean;

import java.io.Serializable;

public class OrderSlipViewBean implements Serializable{
	
	    private String productId;
	    private String productName;
	    private int salePrice;
	    private int unitCost;
	    private int orderQty;
	    private int cancelQty;
	    private int refundQty;
	    private int saleAmount;
	    private int grossProfit;
	    
		public String getProductId() {
			return productId;
		}
		public void setProductId(String productId) {
			this.productId = productId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public int getSalePrice() {
			return salePrice;
		}
		public void setSalePrice(int salePrice) {
			this.salePrice = salePrice;
		}
		public int getUnitCost() {
			return unitCost;
		}
		public void setUnitCost(int unitCost) {
			this.unitCost = unitCost;
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
		public int getSaleAmount() {
			return saleAmount;
		}
		public void setSaleAmount(int saleAmount) {
			this.saleAmount = saleAmount;
		}
		public int getGrossProfit() {
			return grossProfit;
		}
		public void setGrossProfit(int grossProfit) {
			this.grossProfit = grossProfit;
		}
	    
		
}
