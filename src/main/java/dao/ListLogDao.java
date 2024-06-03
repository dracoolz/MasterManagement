package dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListLogDao extends DBAccess{
	
	//履歴追加
	public void insertListLog(int orderId, String changeDate, String flag, String cancelComment, String refundComment) {
		String sql="""
				insert into
				list_log(order_id,change_date,flag,cancel_comment,refund_comment)
				values(?,?,?,?,?)
				""";
		
		try {
			connect();
			//create statment
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			ps.setString(2, changeDate);
			ps.setString(3, flag);
			ps.setString(4, cancelComment);
			ps.setString(5, refundComment);
			
			//execute
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//追加キャンセルか
	public int checkListLogExists(int orderId) {
		String sql="""
				select count(*)
				from list_log
				where order_id = ?
				""";
		ResultSet rs = null;
		int num = 0;
		
    	try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setInt(1, orderId);
			//execute
			rs = ps.executeQuery();
			while(rs.next()) {
				num = rs.getInt("count(*)");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return num;
	}
	
	
}
