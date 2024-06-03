package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.UserBean;

public class UserDao extends DBAccess{
	
	//ユーザ一覧(パスワードなし)を表示するメソッド
		public ArrayList<UserBean> selectAll() {
			
			ArrayList<UserBean> list = new ArrayList<UserBean>();
			
			String sql = "select * from emp_info;";
			
			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				
				 ResultSet rs = ps.executeQuery();
				 
				while (rs.next()) {
					UserBean bean = new UserBean();
					bean.setEmp_id(rs.getInt("emp_id"));
					bean.setEmp_name(rs.getString("emp_name"));
					bean.setFurigana(rs.getString("furigana"));
					bean.setEmp_email(rs.getString("emp_email"));
					bean.setPassword(rs.getString("password"));
					bean.setRole(rs.getInt("role"));
					list.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
		//ユーザテーブルで部分一致で検索するメソッド
		public ArrayList<UserBean> selectPart(String str) {
			
			ArrayList<UserBean> list = new ArrayList<UserBean>();
			String sql = "select * from emp_info where emp_id like %?% or emp_name like %?% or furigana like %?% or emp_email like %?% or role like %?%";
			
			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, str);
				
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					UserBean bean = new UserBean();
					bean.setEmp_id(rs.getInt("emp_id"));
					bean.setEmp_name(rs.getString("emp_name"));
					bean.setFurigana(rs.getString("furigana"));
					bean.setEmp_email(rs.getString("emp_email"));
					bean.setFurigana(rs.getString("password"));
					bean.setFurigana(rs.getString("role"));
					list.add(bean);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
		//ユーザテーブルで検索するメソッド
		public UserBean select(int id) {
			
			UserBean bean = new UserBean();
			String sql = "select * from emp_info where emp_id=?";
			
			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					bean.setEmp_id(rs.getInt("emp_id"));
					bean.setEmp_name(rs.getString("emp_name"));
					bean.setFurigana(rs.getString("furigana"));
					bean.setEmp_email(rs.getString("emp_email"));
					bean.setPassword(rs.getString("password"));
					bean.setRole(rs.getInt("role"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return bean;
		}
				
		//ユーザテーブルに値を追加するメソッド
		public void insert(String emp_name, String furigana, String emp_email, String password, int role) {
			
			String sql = "insert into emp_info(emp_name, furigana, emp_email, password, role) values (?,?,?,?,?)";
			
			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, emp_name);
				ps.setString(2, furigana);
				ps.setString(3, emp_email);
				ps.setString(4, password);
				ps.setInt(5, role);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		
		//ユーザのパスワードを更新（アップデート）するメソッド
		public void updatePass(int emp_id, String password) {
			
			String sql = "update emp_info set password=? where emp_id=?";
			
			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, password);
				ps.setInt(2, emp_id);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		
		//ユーザを更新（アップデート）するメソッド
		public void update(int emp_id, String emp_name, String furigana, String emp_email, int role) {
			
			String sql = "update emp_info set emp_name=?, furigana=?, emp_email=? role=? where emp_id=?";
			
			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, emp_name);
				ps.setString(2, furigana);
				ps.setString(3,emp_email);
				ps.setInt(4, role);
				ps.setInt(5, emp_id);
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		
		//ユーザを削除するメソッド
		public  void delete(int id) {
			
			String sql = "delete from emp_info where emp_id=?";
			
			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}

}
