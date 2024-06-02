package bean;

import java.io.Serializable;

public class OrderSlipBean implements Serializable{
	private static final long serialVersionUID = 1L;

	//field
	private int orderSlipId;
	private int orderId;
	private String productId;
    private String productName;
	private int orderQty;
	private int cancelQty;
	private int refundQty;
	
	private int salePrice; //販売単価
    private int unitCost; //仕入れ単価
    
    private int saleAmount; //商品単位売上金額
    private int grossProfit; //商品単位粗利
    
    
    //getter and setter
	public int getOrderSlipId() {
		return orderSlipId;
	}
	public void setOrderSlipId(int orderSlipId) {
		this.orderSlipId = orderSlipId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
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
