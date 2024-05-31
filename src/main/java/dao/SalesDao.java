package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.SalesBean;

public class SalesDao extends DBAccess {

	public ArrayList<SalesBean> selectProSales() {
		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		String sql = "SELECT s.pro_id, pi.pi_name, sc.sc_category AS category, s.sale_price, " +
	             "(s.sale_price * 5 / 6) AS stock_price, s.sale_amount, " +
	             "((s.sale_price * s.sale_amount) - ((s.sale_price * 5 / 6) * s.sale_amount)) AS profit, " +
	             "((s.sale_price * s.sale_amount) / " +
	             "(SELECT SUM(sale_price * sale_amount) FROM sale_list WHERE YEAR(date) = YEAR(CURDATE()) - 1)) AS comparison " +
	             "FROM sale_list s " +
	             "JOIN product p ON s.pro_id = p.pro_id " +
	             "JOIN product_info pi ON p.pi_id = pi.pi_id " +
	             "JOIN category c ON pi.category_id = c.category_id " +
	             "JOIN small_category sc ON c.sc_id = sc.sc_id " +
	             "ORDER BY s.pro_id";


		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setPro_id(rs.getString("pro_id"));
				bean.setPi_name(rs.getString("pi_name"));
				bean.setCategory(rs.getString("category"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setStock_price(rs.getInt("stock_price"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setProfit(rs.getInt("profit"));
				bean.setComparison(rs.getDouble("comparison"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}




	//商品売上情報一覧を返す
	public ArrayList<SalesBean> selectProSalesById(int id){

		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		// SQL文を作成する
		String sql = "SELECT s.sale_id, s.cus_id, s.pro_id, s.sale_amount, s.sale_price, s.date, " +
	             "pi.pi_name, sc.name AS category, (s.sale_price * 5 / 6) AS stock_price, " +
	             "((s.sale_price * s.sale_amount) - ((s.sale_price * 5 / 6) * s.sale_amount)) AS profit, " +
	             "((s.sale_price * s.sale_amount) / (SELECT SUM(sale_price * sale_amount) FROM sale_list WHERE YEAR(date) = YEAR(CURDATE()) - 1)) AS comparison " +
	             "FROM sale_list s " +
	             "JOIN product p ON s.pro_id = p.pro_id " +
	             "JOIN product_info pi ON p.pi_id = pi.pi_id " +
	             "JOIN category c ON pi.category_id = c.category_id " +
	             "JOIN small_category sc ON c.sc_id = sc.sc_id " +
	             "WHERE s.pro_id = ?";

		try {

			// Connectionオブジェクトを取得する
			connect();
			// ステートメントを作成する
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1,id);
			// SQLを発行する
			ResultSet rs = ps.executeQuery();

			// ResultSetからbeanにユーザ情報を設定する
			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setPro_id(rs.getString("pro_id"));
				bean.setPi_name(rs.getString("pi_name"));
				bean.setCategory(rs.getString("category"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setStock_price(rs.getInt("stock_price"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setProfit(rs.getInt("profit"));
				bean.setComparison(rs.getDouble("comparison"));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}


	//商品名を条件にして商品売上情報を返す
	public ArrayList<SalesBean> selectName(String product_name){

		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		// SQL文を作成する
		String sql = "SELECT * FROM sale_list WHERE sale_id = ?";

		try {

			// Connectionオブジェクトを取得する
			connect();
			// ステートメントを作成する
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,product_name);
			// SQLを発行する
			ResultSet rs = ps.executeQuery();

			// ResultSetからbeanにユーザ情報を設定する
			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setSale_id(rs.getInt("sale_id"));
				bean.setCus_id(rs.getInt("cus_id"));
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setDate(rs.getDate("date").toLocalDate());			
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	//大カテゴリ名を条件にして商品売上情報を返す
	public ArrayList<SalesBean> selectBigCategory(String big_category){

		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		// SQL文を作成する
		String sql = "SELECT * FROM sale_list WHERE sale_id = ?";

		try {

			// Connectionオブジェクトを取得する
			connect();
			// ステートメントを作成する
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,big_category);
			// SQLを発行する
			ResultSet rs = ps.executeQuery();

			// ResultSetからbeanにユーザ情報を設定する
			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setSale_id(rs.getInt("sale_id"));
				bean.setCus_id(rs.getInt("cus_id"));
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setDate(rs.getDate("date").toLocalDate());			
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	//小カテゴリ名を条件にして商品売上情報を返す
	public ArrayList<SalesBean> selectSmallCategory(String small_category){

		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		// SQL文を作成する
		String sql = "SELECT * FROM sale_list WHERE sale_id = ?";

		try {

			// Connectionオブジェクトを取得する
			connect();
			// ステートメントを作成する
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1,small_category);
			// SQLを発行する
			ResultSet rs = ps.executeQuery();

			// ResultSetからbeanにユーザ情報を設定する
			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setSale_id(rs.getInt("sale_id"));
				bean.setCus_id(rs.getInt("cus_id"));
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setDate(rs.getDate("date").toLocalDate());			
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}


	//取引先売上情報一覧を返す
	public ArrayList<SalesBean> selectCusSales() {
		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		String sql = "SELECT sl.cus_id, c.cus_name, sl.sale_amount, " +
	             "((sl.sale_price * sl.sale_amount - sl.sale_price * 5 / 6) * 0.01) AS gross_profit " +
	             "FROM sale_list sl " +
	             "JOIN customer c ON sl.cus_id = c.cus_id " +
	             "JOIN product p ON sl.pro_id = p.pro_id " +
	             "ORDER BY sl.cus_id";



		try {
			// Connectionオブジェクトを取得する
			connect();
			// ステートメントを作成する
			PreparedStatement ps = getConnection().prepareStatement(sql);
			// SQLを発行する
			ResultSet rs = ps.executeQuery();

			// ResultSetからbeanにユーザ情報を設定する
			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setCus_id(rs.getInt("cus_id"));
				bean.setCus_name(rs.getString("cus_name"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setGross_profit(rs.getInt("gross_profit"));
				list.add(bean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}


	//取引先売上IDを条件にして取引先売上情報
	public ArrayList<SalesBean> selectCusSalesById(int id){
	    ArrayList<SalesBean> list = new ArrayList<SalesBean>();
	    String sql = "SELECT sl.cus_id, c.cus_name, sl.sale_amount, ((sl.sale_price * sl.sale_amount) - (sl.sale_price * 5/6)) AS gross_profit " +
	                "FROM sale_list sl " +
	                "JOIN customer c ON sl.cus_id = c.cus_id " +
	                "JOIN product p ON sl.pro_id = p.pro_id " +
	                "WHERE sl.cus_id = ?";

	    try {
	        connect();
	        PreparedStatement ps = getConnection().prepareStatement(sql);
	        ps.setInt(1, id);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            SalesBean bean = new SalesBean();
	            bean.setCus_id(rs.getInt("cus_id"));
	            bean.setCus_name(rs.getString("cus_name"));
	            bean.setSale_amount(rs.getInt("sale_amount"));
	            bean.setGross_profit(rs.getInt("gross_profit"));
	            list.add(bean);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        disconnect();
	    }
	    return list;
	}



	//取引先名を条件にして取引先売上情報を返す
	public ArrayList<SalesBean> selectCusName(String customerName) {
		ArrayList<SalesBean> list = new ArrayList<SalesBean>();
		String sql = "SELECT sl.sale_id, sl.cus_id, sl.pro_id, sl.sale_amount, sl.sale_price, sl.date, c.cus_name, " +
				"((sl.sale_price * sl.sale_amount) - (sl.sale_price * 5/6)) AS gross_profit " +
				"FROM sale_list sl " +
				"JOIN customer c ON sl.cus_id = c.cus_id " +
				"JOIN product p ON sl.pro_id = p.pro_id " +
				"WHERE c.cus_name = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, customerName);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setSale_id(rs.getInt("sale_id"));
				bean.setCus_id(rs.getInt("cus_id"));
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setDate(rs.getDate("date").toLocalDate());
				bean.setCus_name(rs.getString("cus_name"));
				bean.setGross_profit(rs.getInt("gross_profit"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}


	public ArrayList<SalesBean> selectContact(String contact) {
		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		String sql = "SELECT sl.sale_id, sl.cus_id, sl.pro_id, sl.sale_amount, sl.sale_price, sl.date, c.cus_name, " +
				"(((sl.sale_price - p.wholesale) * sl.sale_amount) / (sl.sale_price * sl.sale_amount)) * 100 AS gross_profit " +
				"FROM sale_list sl " +
				"JOIN customer c ON sl.cus_id = c.cus_id " +
				"JOIN product p ON sl.pro_id = p.pro_id " +
				"WHERE c.contact_name = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, contact);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setSale_id(rs.getInt("sale_id"));
				bean.setCus_id(rs.getInt("cus_id"));
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setDate(rs.getDate("date").toLocalDate());
				bean.setCus_name(rs.getString("cus_name"));
				bean.setGross_profit(rs.getInt("gross_profit"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	public ArrayList<SalesBean> selectDistrict(String district) {
		ArrayList<SalesBean> list = new ArrayList<SalesBean>();

		String sql = "SELECT sl.sale_id, sl.cus_id, sl.pro_id, sl.sale_amount, sl.sale_price, sl.date, c.cus_name, " +
				"(((sl.sale_price - p.wholesale) * sl.sale_amount) / (sl.sale_price * sl.sale_amount)) * 100 AS gross_profit " +
				"FROM sale_list sl " +
				"JOIN customer c ON sl.cus_id = c.cus_id " +
				"JOIN product p ON sl.pro_id = p.pro_id " +
				"WHERE c.district = ?";

		try {
			connect();
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, district);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				SalesBean bean = new SalesBean();
				bean.setSale_id(rs.getInt("sale_id"));
				bean.setCus_id(rs.getInt("cus_id"));
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSale_amount(rs.getInt("sale_amount"));
				bean.setSale_price(rs.getInt("sale_price"));
				bean.setDate(rs.getDate("date").toLocalDate());
				bean.setCus_name(rs.getString("cus_name"));
				bean.setGross_profit(rs.getInt("gross_profit"));
				list.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

}
