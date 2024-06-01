package bean;

import java.io.Serializable;

public class ListLogBean implements Serializable {
	
	private int logId;
	private int orderId;
	private int changeDate;
	private String status;
	private String cancelComment;
	private String refundComment;
	
	

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}
	
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
	
	public int getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(int changeDate) {
		this.changeDate = changeDate;
	}
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	
	

	
	
	
	
	
	
	
}
