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

			String sql = "select * from small_category as sc,big_category as bc where sc.bc_id=bc.bc_id;";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);

				 ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					SmallCategoryBean bean = new SmallCategoryBean();
					bean.setSc_id(rs.getInt("sc.sc_id"));
					bean.setBc_id(rs.getInt("sc.bc_id"));
					bean.setSc_category(rs.getString("sc.sc_category"));
					bean.setBc_category(rs.getString("bc.bc_category"));
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
		
		
		public SmallCategoryBean selectSc(int id) {

			SmallCategoryBean bean = new SmallCategoryBean();

			String sql = "select bc.bc_id,bc.bc_category,sc.sc_id,sc.sc_category from big_category as bc,small_category as sc where bc.bc_id=sc.bc_id and sc_id=?";

			try {
				connect();
				// ステートメントの作成
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setInt(1, id);

				ResultSet rs = ps.executeQuery();

				bean.setSc_id(rs.getInt("sc_id"));
				bean.setBc_id(rs.getInt("bc_id"));
				bean.setSc_category(rs.getString("sc_category"));
				bean.setBc_category(rs.getString("bc_category"));
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return bean;
		}
		
		public ArrayList<SmallCategoryBean> selectBc(int id) {

			ArrayList<SmallCategoryBean> list = new ArrayList<SmallCategoryBean>();

			String sql = "select * from small_category where bc_id=?;";

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
					list.add(bean);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return list;
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
				ps.executeUpdate();

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
				ps.executeUpdate();

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
				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
		}

}
