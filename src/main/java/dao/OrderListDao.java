package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OrderListBean;

public class OrderListDao extends DBAccess{
	
	 // 顧客番号を条件として、キャンセルや返品のない注文情報のリストを取得する処理
    public ArrayList<OrderListBean> selectLookOrderList(int cusId) {
    	ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
    	
    	String sql = "select * from small_category;";

		try {
			 connect();
		        PreparedStatement ps = getConnection().prepareStatement(sql);
		        ps.setInt(1, cusId);
		        ResultSet rs = ps.executeQuery();
		        
			while (rs.next()) {
				OrderListBean bean = new OrderListBean();
				bean.setOrderId(rs.getInt("order_id"));
				bean.setOrderDate(rs.getString("date"));
				bean.setCustomerName(rs.getString("cus_name"));
				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
    }
    
    
     // 顧客番号を条件として、キャンセルがある注文情報のリストを取得する処理
    public ArrayList<OrderListBean> selectCancelOrderList(int cusId) {
    	ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
		return list;
    }
    
    
    // 顧客番号を条件として、返品がある注文情報のリストを取得する処理
    public ArrayList<OrderListBean> selectRefundOrderList(int cusId) {
    	ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
		return list;
    }
    
    
    // 日付降順で全ての受注情報を取得し、注文情報のリストを返す処理
    public ArrayList<OrderListBean> orderList() {
    	
        ArrayList<OrderListBean> list = new ArrayList<>();

        // データベースから受注情報を取得するSQLクエリ
        String sql = "SELECT o.order_id, o.order_date, c.cus_name " +
                "FROM order_list o " +
                "JOIN customer c ON o.cus_id = c.cus_id " +
                "ORDER BY o.order_date DESC;";

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderListBean bean = new OrderListBean();
                bean.setOrderId(rs.getInt("order_id"));
                bean.setOrderDate(rs.getString("order_date"));
                bean.setCustomerName(rs.getString("cus_name"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

        return list;
    }
    
    
     // 日付降順で全てのキャンセルがある受注情報を取得し、注文情報のリストを返す処理
    public ArrayList<OrderListBean> cancelOrderList() {
    	ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
		return list;
    }
    
    
    // 日付降順で全ての返品がある受注情報を取得し、注文情報のリストを返す処理
    public ArrayList<OrderListBean> refundOrderList() {
    	ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
		return list;
    }
    
    
    // 対象注文のキャンセル理由を返す
    public String selectCancelComment(int orderId) {
    	String sql = "select cancel_comment from order_list where order_id = ?"; 
    	ResultSet rs = null;
    	String cancelComment = null;
    	
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			//execute
			rs = ps.executeQuery();
			while(rs.next()) {
				cancelComment = rs.getString("cancel_comment");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cancelComment;
    	
    }
    
    // 対象注文の返品理由を返す
    public String selectRefundComment(int orderId) {
    	String sql = "select refund_comment from order_list where order_id = ?"; 
    	ResultSet rs = null;
    	String cancelComment = null;
    	
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			//execute
			rs = ps.executeQuery();
			while(rs.next()) {
				cancelComment = rs.getString("refund_comment");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return cancelComment;
    }
    
    //キャンセルによる更新
    public void updateCancelData(int orderId,String cancelComment) {
    	String sql ="""
    			update order_list
    			set cancel_comment = ?
    			where order_id = ?
    			""";
    	
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			//コメントなし
			if(cancelComment == "") {
				ps.setString(1, null);
			}else {
				ps.setString(1, cancelComment);
			}
			ps.setInt(2, orderId);
			//execute
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
    }
    
    //返品による更新
    public void updateRefundData(int orderId,String refundComment) {
    	String sql ="""
    			update order_list
    			set refund_comment = ?
    			where order_id = ?
    			""";
    	
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, refundComment);
			ps.setInt(2, orderId);
			//execute
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
    }
}
	

