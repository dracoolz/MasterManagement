package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.SmallCategoryBean;

public class SmallCategoryDao extends DBAccess{
	
	
	//小カテゴリ一覧を表示するメソッド
		public ArrayList<SmallCategoryBean> selectAll() {

			ArrayList<SmallCategoryBean> list = new ArrayList<SmallCategoryBean>();

			String sql = "select * from small_category;";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);

				 ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					SmallCategoryBean bean = new SmallCategoryBean();
					bean.setSc_id(rs.getInt("sc_id"));
					bean.setBc_id(rs.getInt("bc_id"));
					bean.setSc_category(rs.getString("sc_category"));
					list.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
		}
		
		
		//小カテゴリテーブルで条件をつけて検索するメソッド
		public SmallCategoryBean selectIf(String name) {

			SmallCategoryBean bean = new SmallCategoryBean();

			String sql = "select * from small_category where sc_category=?";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setString(1, name);

				ResultSet rs = ps.executeQuery();

				bean.setSc_id(rs.getInt("sc_id"));
				bean.setBc_id(rs.getInt("bc_id"));
				bean.setSc_category(rs.getString("sc_category"));
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return bean;
		}
		

		//小カテゴリテーブルに値を追加するメソッド
		public void insert(int sc_id ,int bc_id ,String name) {

			String sql = "insert into small_category(sc_id,bc_id,sc_category) values (?,?,?)";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, sc_id);
				ps.setInt(2, bc_id);
				ps.setString(3, name);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		

		//小カテゴリを更新（アップデート）するメソッド
		public void update(int sc_id ,int bc_id ,int new_sc_id ,String name) {

			String sql = "update small_category set sc_id=?,bc_id=?,sc_category=? where sc_id=?";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, new_sc_id);
				ps.setInt(2, bc_id);
				ps.setString(3, name);
				ps.setInt(4, sc_id);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}
		

		//小カテゴリを削除するメソッド
		public  void delete(int id) {

			String sql = "delete from small_category where sc_id=?";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, id);

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}

}
