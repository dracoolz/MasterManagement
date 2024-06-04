package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.BigCategoryBean;
import bean.SmallCategoryBean;

public class BigCategoryDao extends DBAccess{
	
	
	//大カテゴリ一覧を表示するメソッド
		public ArrayList<BigCategoryBean> selectAll() {

			ArrayList<BigCategoryBean> list = new ArrayList<BigCategoryBean>();

			String sql = "select * from big_category;";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);

				 ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					BigCategoryBean bean = new BigCategoryBean();
					bean.setBc_id(rs.getInt("bc_id"));
					bean.setBc_category(rs.getString("bc_category"));
					list.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
		
		//大カテゴリテーブルで条件をつけて検索するメソッド
		public BigCategoryBean selectIf(String name) {

			BigCategoryBean bean = new BigCategoryBean();

			String sql = "select * from big_category where bc_category=?";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, name);

				ResultSet rs = ps.executeQuery();

				bean.setBc_id(rs.getInt("bc_id"));
				bean.setBc_category(rs.getString("bc_category"));
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return bean;
		}
		
		
		public ArrayList<SmallCategoryBean> select(int id) {

			ArrayList<SmallCategoryBean> list = new ArrayList<SmallCategoryBean>();

			String sql = "select * from small_category where bc_id=?";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();
				
				while (rs.next()) {
					SmallCategoryBean bean = new SmallCategoryBean();
					bean.setSc_id(rs.getInt("sc_id"));
					bean.setBc_id(rs.getInt("bc_id"));
					bean.setSc_category(rs.getString("sc_category"));
					bean.setBc_category(rs.getString("bc_category"));
					list.add(bean);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			
			return list;
		}

		//大カテゴリテーブルに値を追加するメソッド
		public void insert(int id ,String name) {

			String sql = "insert into big_category(bc_id,bc_category) values (?,?)";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		

		//大カテゴリを更新（アップデート）するメソッド
		public void update(int id ,int new_id ,String name) {

			String sql = "update big_category set bc_id=?,bc_category=? where bc_id=?";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, new_id);
				ps.setString(2, name);
				ps.setInt(3, id);
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		

		//大カテゴリを削除するメソッド
		public  void delete(int id) {

			String sql = "delete from big_category where bc_id=?";

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
