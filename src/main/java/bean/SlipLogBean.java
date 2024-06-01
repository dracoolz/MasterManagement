package bean;

import java.io.Serializable;

public class SlipLogBean implements Serializable {
	
	private int slipLogId;
	private int logId;
	private int proId;
	private int orderQty;
	private int cancelQty;
	private int refundQty;
	
	

	public int getSlipLogId() {
		return slipLogId;
	}

	public void setSlipLogId(int slipLogId) {
		this.slipLogId = slipLogId;
	}
	
	
	
	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	
	
	
	
	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}
	
	
	
	
	
	public int getProId() {
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
