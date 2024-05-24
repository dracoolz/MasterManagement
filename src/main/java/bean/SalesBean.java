package bean;

import java.io.Serializable;
import java.time.LocalDate;

public class SalesBean implements Serializable {
	private int sale_id;
	private int cus_id;
	private String pro_id;
	private int sale_amount;
	private int sale_price;
	private LocalDate date;
	
	
	public int getSale_id() {
		return sale_id;
	}
	
	public void setSale_id(int sale_id) {
		this.sale_id = sale_id;
	}
	
	public int getCus_id() {
		return cus_id;
	}
	
	public void setCus_id(int cus_id) {
		this.cus_id = cus_id;
	}
	
	public String getPro_id() {
		return pro_id;
	}
	
	public void setPro_id(String pro_id) {
		this.pro_id = pro_id;
	}
	
	public int getSale_amount() {
		return sale_amount;
	}
	
	public void setSale_amount(int sale_amount) {
		this.sale_amount = sale_amount;
	}
	
	public int getSale_price() {
		return sale_price;
	}
	
	public void setSale_price(int sale_price) {
		this.sale_price = sale_price;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}

}
