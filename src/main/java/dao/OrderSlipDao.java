package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OrderSlipBean;

public class OrderSlipDao extends DBAccess{
	
	//キャンセル処理,返品処理画面に必要な情報取得
	public ArrayList<OrderSlipBean> selectSlipForCancelAndRefund(int orderId){
		String sql="""
				select order_slip.order_slip_id, order_slip.pro_id ,product_info.pi_name,order_slip.order_qty, order_slip.cancel_qty, order_slip.refund_qty
				from order_slip, product, product_info
				where order_slip.pro_id = product.pro_id and
				product.pi_id = product_info.pi_id and
				order_slip.order_id = ?
				""";

		ResultSet rs = null;
		ArrayList<OrderSlipBean> orderSlip = new ArrayList<OrderSlipBean>();
		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			//execute
			rs = ps.executeQuery();
			while(rs.next()) {
				OrderSlipBean bean = new OrderSlipBean();
				bean.setOrderSlipId(rs.getInt("order_slip.order_slip_id"));
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
	
	//キャンセル数を更新
	public void updateCancelQty(ArrayList<OrderSlipBean> cancelSlip){
		String sql="""
				update order_slip
				set cancel_qty = ?
				where order_slip_id = ?
				""";
		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for(OrderSlipBean item:cancelSlip) {
				ps.setInt(1, item.getCancelQty());
				ps.setInt(2, item.getOrderSlipId());
				//execute
				ps.executeUpdate();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}

	}
	
	//返品数を更新
	public void updateRefundQty(ArrayList<OrderSlipBean> refundSlip){
		String sql="""
				update order_slip
				set refund_qty = ?
				where order_slip_id = ?
				""";
		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for(OrderSlipBean item:refundSlip) {
				ps.setInt(1, item.getRefundQty());
				ps.setInt(2, item.getOrderSlipId());
				//execute
				ps.executeUpdate();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}

	}
}
