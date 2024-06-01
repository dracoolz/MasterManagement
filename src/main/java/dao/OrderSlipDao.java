package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OrderSlipViewBean;

public class OrderSlipDao extends DBAccess{
	
	
	

    // 注文番号を条件に受注情報の詳細を取得するメソッド
    public ArrayList<OrderSlipViewBean> selectOrderSlip(int orderId) {
   ArrayList<OrderSlipViewBean> list = new ArrayList<OrderSlipViewBean>();
    	
         String sql = "SELECT p.pro_id, pi.pi_name, os.order_qty, os.cancel_qty, os.refund_qty " +
        "FROM order_slip os " +
        "JOIN product p ON os.pro_id = p.pro_id " +
        "JOIN product_info pi ON p.pi_id = pi.pi_id " +
        "WHERE os.order_id = ?";
		try {
			 connect();
			 
		        PreparedStatement ps = getConnection().prepareStatement(sql);
		        ps.setInt(1, orderId);
		        ResultSet rs = ps.executeQuery();
		        
			while (rs.next()) {
				OrderSlipViewBean bean = new OrderSlipViewBean();
				bean.setProductId(rs.getString("pro_id"));//ダイレクト商品ID
				bean.setProductName(rs.getString("pi_name"));//商品名
				//bean.setSalePrice(rs.getInt("order_id"));//販売単価
				//bean.setUnitCost(rs.getInt("order_id"));//仕入単価
				bean.setOrderQty(rs.getInt("order_qty"));//注文数
				bean.setCancelQty(rs.getInt("cancel_qty"));//キャンセル数
				bean.setRefundQty(rs.getInt("refund_qty"));//返品数
				//bean.setSaleAmount(rs.getInt("cus_name"));//売上金額
				//bean.setGrossProfit(rs.getInt("date"));//粗利
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
    }

    // 注文番号を条件にキャンセル情報の詳細を取得するメソッド
    public ArrayList<OrderSlipViewBean> selectCancelSlip(int orderId) {
        // キャンセル情報の詳細をデータベースから取得する処理を記述する
        // ここではダミーのリストを返す
        return new ArrayList<OrderSlipViewBean>();
    }

    // 注文番号を条件に返品情報の詳細を取得するメソッド
    public ArrayList<OrderSlipViewBean> selectRefundSlip(int orderId) {
        // 返品情報の詳細をデータベースから取得する処理を記述する
        // ここではダミーのリストを返す
        return new ArrayList<OrderSlipViewBean>();
    }
    
}
	

