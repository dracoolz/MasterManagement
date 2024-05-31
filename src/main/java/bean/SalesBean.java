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
    private String cus_name;
    private int gross_profit;
    private String pi_name;
    private String category;
    private int stock_price;
    private int profit;
    private double comparison;

    // Getters and Setters
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

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public int getGross_profit() {
        return gross_profit;
    }

    public void setGross_profit(int gross_profit) {
        this.gross_profit = gross_profit;
    }

    public String getPi_name() {
        return pi_name;
    }

    public void setPi_name(String pi_name) {
        this.pi_name = pi_name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock_price() {
        return stock_price;
    }

    public void setStock_price(int stock_price) {
        this.stock_price = stock_price;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

	public double getComparison() {
		return comparison;
	}

	public void setComparison(double comparison) {
		this.comparison = comparison;
	}
    
    
}
