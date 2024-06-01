package bean;

import java.io.Serializable;

public class OrderSlipViewBean implements Serializable{
	
	    private String ProductId;
	    private String ProductName;
	    private int SalePrice;
	    private int UnitCost;
	    private int OrderQty;
	    private int CancelQty;
	    private int RefundQty;
	    private int SaleAmount;
	    private int GrossProfit;
	    
		public String getProductId() {
			return ProductId;
		}
		public void setProductId(String productId) {
			ProductId = productId;
		}
		public String getProductName() {
			return ProductName;
		}
		public void setProductName(String productName) {
			ProductName = productName;
		}
		public int getSalePrice() {
			return SalePrice;
		}
		public void setSalePrice(int salePrice) {
			SalePrice = salePrice;
		}
		public int getUnitCost() {
			return UnitCost;
		}
		public void setUnitCost(int unitCost) {
			UnitCost = unitCost;
		}
		public int getOrderQty() {
			return OrderQty;
		}
		public void setOrderQty(int orderQty) {
			OrderQty = orderQty;
		}
		public int getCancelQty() {
			return CancelQty;
		}
		public void setCancelQty(int cancelQty) {
			CancelQty = cancelQty;
		}
		public int getRefundQty() {
			return RefundQty;
		}
		public void setRefundQty(int refundQty) {
			RefundQty = refundQty;
		}
		public int getSaleAmount() {
			return SaleAmount;
		}
		public void setSaleAmount(int saleAmount) {
			SaleAmount = saleAmount;
		}
		public int getGrossProfit() {
			return GrossProfit;
		}
		public void setGrossProfit(int grossProfit) {
			GrossProfit = grossProfit;
		}
}
