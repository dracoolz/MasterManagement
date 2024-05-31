package bean;

import java.io.Serializable;

public class CustomerViewBean implements Serializable {
	private int cus_id;
	private String cus_name;
	private String contact_name;
	private String district;


	public int getCusId() {
		return cus_id;
	}

	public void setCusId(int cus_id) {
		this.cus_id = cus_id;
	}
	
	

	public String getCusName() {
		return cus_name;
	}

	public void setCusName(String cus_name) {
		this.cus_name = cus_name;
	}
	
	

	
	
	
	public String getContactName() {
		return contact_name;
	}

	public void setContactName(String contact_name) {
		this.contact_name = contact_name;
	}
	
	
	
	
	
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	

	

	
	
}
