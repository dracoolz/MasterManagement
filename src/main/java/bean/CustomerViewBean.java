package bean;

import java.io.Serializable;

public class CustomerViewBean implements Serializable {
	private int cusId;
	private String cusName;
	private String contactName;
	private String district;
	
	
	//getter and setter
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
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
}
