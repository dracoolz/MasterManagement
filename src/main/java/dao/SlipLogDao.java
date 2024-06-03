package dao;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.OrderSlipBean;

public class SlipLogDao extends DBAccess{
	
	//履歴追加
	public void insertSlipLog(ArrayList<OrderSlipBean> list){
		String sql="""
				insert into
				slip_log(log_id, pro_id,order_qty,cancel_qty,refund_qty)
				values(?,?,?,?,?)
				""";
		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			for(OrderSlipBean item:list) {
				ps.setInt(1, item.getOrderId());
				ps.setString(2, item.getProductId());
				ps.setInt(3, item.getOrderQty());
				ps.setInt(4, item.getCancelQty());
				ps.setInt(5, item.getRefundQty());
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
