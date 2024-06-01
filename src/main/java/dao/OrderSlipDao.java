package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OrderSlipViewBean;

public class OrderSlipDao extends DBAccess{
	
	//キャンセル処理,返品処理画面に必要な情報取得
	public ArrayList<OrderSlipViewBean> selectSlipForCancelAndRefund(int orderId){
		String sql="select order_slip.pro_id ,product_info.pi_name, order_slip.order_qty, order_slip.cancel_qty, order_slip.refund_qty from order_slip, product, product_info where order_slip.pro_id = product.pro_id and product.pi_id = product_info.pi_id and order_slip.order_id = ?";

		ResultSet rs = null;
		ArrayList<OrderSlipViewBean> orderSlip = new ArrayList<OrderSlipViewBean>();
		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			//execute
			rs = ps.executeQuery();
			while(rs.next()) {
				OrderSlipViewBean bean = new OrderSlipViewBean();
				bean.setProductId(rs.getString("order_slip.pro_id"));
				bean.setProductName(rs.getString("product_info.pi_name"));
				bean.setOrderQty(rs.getInt("order_slip.order_qty"));
				bean.setCancelQty(rs.getInt("order_slip.cancel_qty"));
				bean.setRefundQty(rs.getInt("order_slip.refund_qty"));
				orderSlip.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return orderSlip;
	}
}
