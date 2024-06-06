package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OrderListBean;

public class OrderListDao extends DBAccess{
	
	public ArrayList<OrderListBean> getOrdersByCustomerId(int customerId) {
	    ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
	    String sql = "SELECT o.order_id, o.order_date, c.cus_name, " +
	             "SUM(pr.wholesale * pr.set_quantity * 1.1 * (os.order_qty - (os.cancel_qty + os.refund_qty))) AS TotalAmountMoney " +
	             "FROM order_list o " +
	             "JOIN customer c ON o.cus_id = c.cus_id " +
	             "JOIN order_slip os ON o.order_id = os.order_id " +
	             "JOIN product pr ON os.pro_id = pr.pro_id " +
	             "WHERE c.cus_id = ? " + // 顧客IDで絞り込む
	             "GROUP BY o.order_id, o.order_date, c.cus_name " +
	             "ORDER BY o.order_date DESC";

	    try {
	        connect();
	        PreparedStatement ps = getConnection().prepareStatement(sql);
	        ps.setInt(1, customerId); // バインド変数をセット
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            OrderListBean bean = new OrderListBean();
	            bean.setOrderId(rs.getInt("order_id"));
	            bean.setOrderDate(rs.getString("order_date"));
	            bean.setCustomerName(rs.getString("cus_name"));
	            bean.setTotalAmountMoney(rs.getInt("TotalAmountMoney"));
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
    public ArrayList<OrderListBean> selectCancelOrderList(int customerId) {
    	 ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
    	 ArrayList<OrderListBean> orders = new ArrayList<OrderListBean>();
    	 
    	 String sql = "SELECT o.order_id, o.order_date, c.cus_name, " +
                 "SUM(pr.wholesale * pr.set_quantity * 1.1 * (os.order_qty - (os.cancel_qty + os.refund_qty))) AS TotalAmountMoney " +
                 "FROM order_list o " +
                 "JOIN customer c ON o.cus_id = c.cus_id " +
                 "JOIN order_slip os ON o.order_id = os.order_id " +
                 "JOIN product pr ON os.pro_id = pr.pro_id " +
                 "WHERE c.cus_id = ? " + // 顧客IDをプレースホルダーに指定
                 "GROUP BY o.order_id, o.order_date, c.cus_name " +
                 "HAVING SUM(os.cancel_qty) > 0 " + // キャンセル数量がゼロでない受注のみ表示
                 "ORDER BY o.order_date DESC";


 	    try {
 	        connect();
 	        PreparedStatement ps = getConnection().prepareStatement(sql);
 	        ps.setInt(1, customerId); // バインド変数をセット
 	        ResultSet rs = ps.executeQuery();

 	        while (rs.next()) {
 	            OrderListBean bean = new OrderListBean();
 	            bean.setOrderId(rs.getInt("order_id"));
 	            bean.setOrderDate(rs.getString("order_date"));
 	            bean.setCustomerName(rs.getString("cus_name"));
 	            bean.setTotalAmountMoney(rs.getInt("TotalAmountMoney"));
 	            orders.add(bean);
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    } finally {
 	        disconnect();
 	    }
 	    try {
 	        connect();
 	        PreparedStatement ps = getConnection().prepareStatement(sql);
 	        ps.setInt(1, customerId); // バインド変数をセット
 	        ResultSet rs = ps.executeQuery();

 	        while (rs.next()) {
 	            OrderListBean bean = new OrderListBean();
 	            bean.setOrderId(rs.getInt("order_id"));
 	            bean.setOrderDate(rs.getString("order_date"));
 	            bean.setCustomerName(rs.getString("cus_name"));
 	            bean.setTotalAmountMoney(rs.getInt("TotalAmountMoney"));
 	           list.add(bean);
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    } finally {
 	        disconnect();
 	    }
 	   return list;
    }
    
    
    // 顧客番号を条件として、返品がある注文情報のリストを取得する処理
    public ArrayList<OrderListBean> selectRefundOrderList(int customerId) {
    	 ArrayList<OrderListBean> list = new ArrayList<OrderListBean>();
    	 String sql = "SELECT o.order_id, o.order_date, c.cus_name, " +
                 "SUM(pr.wholesale * pr.set_quantity * 1.1 * (os.order_qty - (os.cancel_qty + os.refund_qty))) AS TotalAmountMoney " +
                 "FROM order_list o " +
                 "JOIN customer c ON o.cus_id = c.cus_id " +
                 "JOIN order_slip os ON o.order_id = os.order_id " +
                 "JOIN product pr ON os.pro_id = pr.pro_id " +
                 "WHERE c.cus_id = ? " + // 顧客IDで絞り込む
                 "GROUP BY o.order_id, o.order_date, c.cus_name " +
                 "HAVING SUM(os.refund_qty) > 0 " + // 返品数量がゼロでない受注のみ表示
                 "ORDER BY o.order_date DESC";

 	    try {
 	        connect();
 	        PreparedStatement ps = getConnection().prepareStatement(sql);
 	        ps.setInt(1, customerId); // バインド変数をセット
 	        ResultSet rs = ps.executeQuery();

 	        while (rs.next()) {
 	            OrderListBean bean = new OrderListBean();
 	            bean.setOrderId(rs.getInt("order_id"));
 	            bean.setOrderDate(rs.getString("order_date"));
 	            bean.setCustomerName(rs.getString("cus_name"));
 	            bean.setTotalAmountMoney(rs.getInt("TotalAmountMoney"));
 	           list.add(bean);
 	        }
 	    } catch (SQLException e) {
 	        e.printStackTrace();
 	    } finally {
 	        disconnect();
 	    }
 	   return list;
    }
    
    
    // 日付降順で全ての受注情報を取得し、注文情報のリストを返す処理
    public ArrayList<OrderListBean> orderList() {
    	
        ArrayList<OrderListBean> list = new ArrayList<>();

        // データベースから受注情報を取得するSQLクエリ
        String sql = "SELECT o.order_id, o.order_date, c.cus_name, " +
                "SUM(pr.wholesale * pr.set_quantity * 1.1 * (os.order_qty - (os.cancel_qty + os.refund_qty))) AS TotalAmountMoney " +
                "FROM order_list o " +
                "JOIN customer c ON o.cus_id = c.cus_id " +
                "JOIN order_slip os ON o.order_id = os.order_id " +
                "JOIN product pr ON os.pro_id = pr.pro_id " +
                "GROUP BY o.order_id, o.order_date, c.cus_name " +
                "ORDER BY o.order_date DESC";


                
        try {
            connect();
            
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderListBean bean = new OrderListBean();
                bean.setOrderId(rs.getInt("order_id"));
                bean.setOrderDate(rs.getString("order_date"));
                bean.setCustomerName(rs.getString("cus_name"));
                bean.setTotalAmountMoney(rs.getInt("TotalAmountMoney"));
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
    	  ArrayList<OrderListBean> list = new ArrayList<>();

          // データベースから受注情報を取得するSQLクエリ
    	  String sql = "SELECT o.order_id, o.order_date, c.cus_name, " +
                  "SUM(pr.wholesale * pr.set_quantity * 1.1 * (os.order_qty - (os.cancel_qty + os.refund_qty))) AS TotalAmountMoney " +
                  "FROM order_list o " +
                  "JOIN customer c ON o.cus_id = c.cus_id " +
                  "JOIN order_slip os ON o.order_id = os.order_id " +
                  "JOIN product pr ON os.pro_id = pr.pro_id " +
                  "GROUP BY o.order_id, o.order_date, c.cus_name " +
                  "HAVING SUM(os.cancel_qty) > 0 " + // キャンセル数量がゼロでない受注のみ表示
                  "ORDER BY o.order_date DESC";

                  
          try {
              connect();
              
              PreparedStatement ps = getConnection().prepareStatement(sql);
              ResultSet rs = ps.executeQuery();

              while (rs.next()) {
                  OrderListBean bean = new OrderListBean();
                  bean.setOrderId(rs.getInt("order_id"));
                  bean.setOrderDate(rs.getString("order_date"));
                  bean.setCustomerName(rs.getString("cus_name"));
                  bean.setTotalAmountMoney(rs.getInt("TotalAmountMoney"));
                  list.add(bean);
              }
          } catch (SQLException e) {
              e.printStackTrace();
          } finally {
              disconnect();
          }
    	
		return list;
    }
    
    
    // 日付降順で全ての返品がある受注情報を取得し、注文情報のリストを返す処理
    public ArrayList<OrderListBean> refundOrderList() {
    	ArrayList<OrderListBean> list = new ArrayList<>();

        // データベースから受注情報を取得するSQLクエリ
    	 String sql = "SELECT o.order_id, o.order_date, c.cus_name, " +
                 "SUM(pr.wholesale * pr.set_quantity * 1.1 * (os.order_qty - (os.cancel_qty + os.refund_qty))) AS TotalAmountMoney " +
                 "FROM order_list o " +
                 "JOIN customer c ON o.cus_id = c.cus_id " +
                 "JOIN order_slip os ON o.order_id = os.order_id " +
                 "JOIN product pr ON os.pro_id = pr.pro_id " +
                 "GROUP BY o.order_id, o.order_date, c.cus_name " +
                 "HAVING SUM(os.refund_qty) > 0 " + // 返金数量がゼロでない受注のみ表示
                 "ORDER BY o.order_date DESC";

                
        try {
            connect();
            
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrderListBean bean = new OrderListBean();
                bean.setOrderId(rs.getInt("order_id"));
                bean.setOrderDate(rs.getString("order_date"));
                bean.setCustomerName(rs.getString("cus_name"));
                bean.setTotalAmountMoney(rs.getInt("TotalAmountMoney"));
                list.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
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
    	String refundComment = null;
    	
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			//execute
			rs = ps.executeQuery();
			while(rs.next()) {
				refundComment = rs.getString("refund_comment");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return refundComment;
    }
    
    //キャンセルによる更新
    public void updateCancelData(int orderId, String cancelComment) {
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
    public void updateRefundData(int orderId, String refundComment) {
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
			//コメントなし
			if(refundComment == "") {
				ps.setString(1, null);
			}else {
				ps.setString(1, refundComment);
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
    
    //受注追加
    public void insertOrder(int customerId, String orderDate) {
    	String sql="""
    			insert into
    			order_list(cus_id, order_date)
    			values(?,?)
    			""";
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, customerId);
			ps.setString(2, orderDate);
			//execute
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
    }
    
    //最新の注文番号を取得
    public int selectMaxOrderId() {
    	String sql="select max(order_id) as id from order_list";
    	int orderId = 0;
    	ResultSet rs = null;
    	
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			//execute
			rs = ps.executeQuery();
			while(rs.next()) {
				orderId = rs.getInt("id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
    	return orderId;
    }
}
	

