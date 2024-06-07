package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OrderSlipBean;

public class OrderSlipDao extends DBAccess {

	// 注文番号を条件に受注情報の詳細を取得するメソッド
	public ArrayList<OrderSlipBean> selectOrderSlip(int orderId) {
		ArrayList<OrderSlipBean> list = new ArrayList<OrderSlipBean>();
		String sql = "SELECT p.pro_id, pi.pi_name, os.order_qty, os.cancel_qty, os.refund_qty, p.wholesale, p.set_quantity, pi.retail_price, (os.order_qty - (os.cancel_qty + os.refund_qty)) AS final_quantity, (p.wholesale * p.set_quantity * 1.1) AS SalePrice "
				+
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
				OrderSlipBean bean = new OrderSlipBean();
				bean.setProductId(rs.getString("pro_id"));//ダイレクト商品ID
				bean.setProductName(rs.getString("pi_name"));//商品名

				int order_qty = rs.getInt("order_qty");
				int cancel_qty = rs.getInt("cancel_qty");
				int refund_qty = rs.getInt("refund_qty");
				int final_quantity = rs.getInt("final_quantity");
				int SalePrice = rs.getInt("SalePrice");
				int UnitCost = rs.getInt("SalePrice") * 5 / 6;
				int SaleAmount = SalePrice * final_quantity;
				int GrossProfit = SaleAmount - UnitCost * final_quantity;

				bean.setSalePrice(SalePrice);//販売単価
				bean.setUnitCost(UnitCost);//仕入単価
				bean.setOrderQty(order_qty);//注文数
				bean.setCancelQty(cancel_qty);//キャンセル数
				bean.setRefundQty(refund_qty);//返品数
				bean.setSaleAmount(SaleAmount);//売上金額
				bean.setGrossProfit(GrossProfit);//粗利

				list.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	//キャンセル処理,返品処理画面に必要な情報取得
	public ArrayList<OrderSlipBean> selectSlipForCancelAndRefund(int orderId) {
		String sql = """
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
			while (rs.next()) {
				OrderSlipBean bean = new OrderSlipBean();
				bean.setOrderSlipId(rs.getInt("order_slip.order_slip_id"));
				bean.setProductId(rs.getString("order_slip.pro_id"));
				bean.setProductName(rs.getString("product_info.pi_name"));
				bean.setOrderQty(rs.getInt("order_slip.order_qty"));
				bean.setCancelQty(rs.getInt("order_slip.cancel_qty"));
				bean.setRefundQty(rs.getInt("order_slip.refund_qty"));
				orderSlip.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return orderSlip;
	}

	//キャンセル数を更新
	public void updateCancelQty(ArrayList<OrderSlipBean> cancelSlip) {
		String sql = """
				update order_slip
				set cancel_qty = ?
				where order_slip_id = ?
				""";
		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for (OrderSlipBean item : cancelSlip) {
				ps.setInt(1, item.getCancelQty());
				ps.setInt(2, item.getOrderSlipId());
				//execute
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	//返品数を更新
	public void updateRefundQty(ArrayList<OrderSlipBean> refundSlip) {
		String sql = """
				update order_slip
				set refund_qty = ?
				where order_slip_id = ?
				""";
		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for (OrderSlipBean item : refundSlip) {
				ps.setInt(1, item.getRefundQty());
				ps.setInt(2, item.getOrderSlipId());
				//execute
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}
	
	//受注追加
    public void insertOrder(int orderId, ArrayList<OrderSlipBean> slip) {
    	String sql="""
    			insert into
    			order_slip(order_id, pro_id, order_qty)
    			values(?,?,?)
    			""";
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for(OrderSlipBean item:slip) {
				ps.setInt(1, orderId);
				ps.setString(2,item.getProductId());
				ps.setInt(3,item.getOrderQty());
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