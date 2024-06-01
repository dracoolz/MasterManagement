package dao;
import java.sql.PreparedStatement;
import dao.DBAccess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ListLogDao extends DBAccess{
	
	//履歴追加
	public void insertListLog(int String String String String) {
		
	}
	
	
	//select method
	public ArrayList<> select(){
		String sql="select from where";
		ResultSet rs = null;
		ArrayList<> beanList = new ArrayList<>();

		try {
			connect();
			//create statement
			PreparedStatement ps = getConnection().prepareStatement(sql);
			//execute
			result = ps.executeQuery();
			while(result.next()) {
				ShohinBean bean = new ShohinBean();
				bean.set(result.getString(""));
				bean.set(result.getInt(""));
				
				beanList.add(bean);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return beanList;
	}
	
	
	// update insert delete
	public int something(){
		String sql = "update tabel set ";
		//ResultSet result = null;
		int numOfRecord = 0;
		
		try {
			connect();
			//create statment
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, );
			ps.setString(2, );
			ps.setInt(3, );
			//execute
			numOfRecord = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return numOfRecord;
	}
	
}
