package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ProductBean;

public class ProductDao extends DBAccess{

	//商品情報一覧を表示するメソッド
			public ArrayList<ProductBean> selectAll() {

				ArrayList<ProductBean> list = new ArrayList<ProductBean>();

				String sql = "select p.pro_id, p.sp_id, p.pi_id, pi.pi_name, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, sup.shop_name, pd.descr, pd.detail, pi.jan_code, pi.branch_no, p.itemization, pi.ref_type, pi.retail_price, p.wholesale, p.set_quantity, pi.tax_rate_class, pi.shipping_term, pimg.image_1, pimg.image_2, pimg.image_3, pimg.image_4, pimg.image_5, pimg.image_6, pimg.image_7, pimg.image_8, pimg.image_9, pimg.image_10, pi.image_permission, pi.sell_permission, pi.auction_permission, pi.direct_permission, pi.out_of_stock \n"
						+ "from product as p, product_info as pi, product_description as pd, product_images as pimg, supllier as sup, big_category as bc, small_category as sc, category as c \n"
						+ "where p.pi_id = pi.pi_id and p.pd_id = pd.pd_id and p.images_id = pimg.images_id and p.sp_id = sup.sp_id and pi.category_id = c.category_id and c.sc_id = sc.sc_id and sc.bc_id = bc.bd_id;";

				try {
					connect();
					// ステートメントの作成
					PreparedStatement ps = getConnection().prepareStatement(sql);

					 ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						ProductBean bean = new ProductBean();
						bean.setPro_id(rs.getString("pro_id"));
						bean.setSp_id(rs.getInt("sp_id"));
						bean.setPi_id(rs.getString("pi_id"));
						bean.setPd_id(rs.getString("pd_id"));
						bean.setImages_id(rs.getString("images_id"));
						bean.setItemization(rs.getString("itemization"));
						bean.setWholesale(rs.getInt("wholesale"));
						bean.setSet_quantity(rs.getInt("set_quantity"));
						bean.setPi_name(rs.getString("pi_name"));
						bean.setCategory_id(rs.getInt("category_id"));
						bean.setBc_category_1(rs.getString("bc_category_1"));
						bean.setSc_category_1(rs.getString("sc_category_1"));
						bean.setBc_category_2(rs.getString("bc_category_2"));
						bean.setSc_category_2(rs.getString("sc_category_2"));
						bean.setBc_category_3(rs.getString("bc_category_3"));
						bean.setSc_category_3(rs.getString("sc_category_3"));
						bean.setJan_cade(rs.getString("jan_cade"));
						bean.setBranch_no(rs.getInt("branch_no"));
						bean.setRef_type(rs.getString("ref_type"));
						bean.setRetail_price(rs.getInt("retail_price"));
						bean.setTax_rate_class(rs.getInt("tax_rate_class"));
						bean.setShipping_term(rs.getString("shipping_term"));
						bean.setImage_permission(rs.getString("image_permission"));
						bean.setSell_permission(rs.getString("sell_permission"));
						bean.setAuction_permission(rs.getString("auction_permission"));
						bean.setDirect_permission(rs.getString("direct_permission"));
						bean.setOut_of_stock(rs.getString("out_of_stock"));
						bean.setSc_id(rs.getInt("sc_id"));
						bean.setDescr(rs.getString("descr"));
						bean.setDetail(rs.getString("detail"));
						bean.setImages_1(rs.getString("images_1"));
						bean.setImages_2(rs.getString("images_2"));
						bean.setImages_3(rs.getString("images_3"));
						bean.setImages_4(rs.getString("images_4"));
						bean.setImages_5(rs.getString("images_5"));
						bean.setImages_6(rs.getString("images_6"));
						bean.setImages_7(rs.getString("images_7"));
						bean.setImages_8(rs.getString("images_8"));
						bean.setImages_9(rs.getString("images_9"));
						bean.setImages_10(rs.getString("images_10"));
						bean.setShop_name(rs.getString("shop_name"));
						list.add(bean);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconnect();
				}
				return list;
			}
			
			
			//商品名で条件をつけて商品情報を検索するメソッド
			public ArrayList<ProductBean> selectName(String name) {

				ArrayList<ProductBean> list = new ArrayList<ProductBean>();

				String sql = "select p.pro_id, p.sp_id, p.pi_id, pi.pi_name, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, sup.shop_name, pd.descr, pd.detail, pi.jan_code, pi.branch_no, p.itemization, pi.ref_type, pi.retail_price, p.wholesale, p.set_quantity, pi.tax_rate_class, pi.shipping_term, pimg.image_1, pimg.image_2, pimg.image_3, pimg.image_4, pimg.image_5, pimg.image_6, pimg.image_7, pimg.image_8, pimg.image_9, pimg.image_10, pi.image_permission, pi.sell_permission, pi.auction_permission, pi.direct_permission, pi.out_of_stock \n"
						+ "from product as p, product_info as pi, product_description as pd, product_images as pimg, supllier as sup, big_category as bc, small_category as sc, category as c \n"
						+ "where p.pi_id = pi.pi_id and p.pd_id = pd.pd_id and p.images_id = pimg.images_id and p.sp_id = sup.sp_id and pi.category_id = c.category_id and c.sc_id = sc.sc_id and sc.bc_id = bc.bd_id and pi.pi_name = ?;";

				try {
					connect();
					// ステートメントの作成
					PreparedStatement ps = getConnection().prepareStatement(sql);
					ps.setString(1, name);

					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						ProductBean bean = new ProductBean();
						bean.setPro_id(rs.getString("pro_id"));
						bean.setSp_id(rs.getInt("sp_id"));
						bean.setPi_id(rs.getString("pi_id"));
						bean.setPd_id(rs.getString("pd_id"));
						bean.setImages_id(rs.getString("images_id"));
						bean.setItemization(rs.getString("itemization"));
						bean.setWholesale(rs.getInt("wholesale"));
						bean.setSet_quantity(rs.getInt("set_quantity"));
						bean.setPi_name(rs.getString("pi_name"));
						bean.setCategory_id(rs.getInt("category_id"));
						bean.setBc_category_1(rs.getString("bc_category_1"));
						bean.setSc_category_1(rs.getString("sc_category_1"));
						bean.setBc_category_2(rs.getString("bc_category_2"));
						bean.setSc_category_2(rs.getString("sc_category_2"));
						bean.setBc_category_3(rs.getString("bc_category_3"));
						bean.setSc_category_3(rs.getString("sc_category_3"));
						bean.setJan_cade(rs.getString("jan_cade"));
						bean.setBranch_no(rs.getInt("branch_no"));
						bean.setRef_type(rs.getString("ref_type"));
						bean.setRetail_price(rs.getInt("retail_price"));
						bean.setTax_rate_class(rs.getInt("tax_rate_class"));
						bean.setShipping_term(rs.getString("shipping_term"));
						bean.setImage_permission(rs.getString("image_permission"));
						bean.setSell_permission(rs.getString("sell_permission"));
						bean.setAuction_permission(rs.getString("auction_permission"));
						bean.setDirect_permission(rs.getString("direct_permission"));
						bean.setOut_of_stock(rs.getString("out_of_stock"));
						bean.setSc_id(rs.getInt("sc_id"));
						bean.setDescr(rs.getString("descr"));
						bean.setDetail(rs.getString("detail"));
						bean.setImages_1(rs.getString("images_1"));
						bean.setImages_2(rs.getString("images_2"));
						bean.setImages_3(rs.getString("images_3"));
						bean.setImages_4(rs.getString("images_4"));
						bean.setImages_5(rs.getString("images_5"));
						bean.setImages_6(rs.getString("images_6"));
						bean.setImages_7(rs.getString("images_7"));
						bean.setImages_8(rs.getString("images_8"));
						bean.setImages_9(rs.getString("images_9"));
						bean.setImages_10(rs.getString("images_10"));
						bean.setShop_name(rs.getString("shop_name"));
						list.add(bean);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconnect();
				}
				return list;
			}
			

			//大カテゴリ名で条件をつけて商品情報を検索するメソッド
			public ArrayList<ProductBean> selectBigCategory(String name) {

				ArrayList<ProductBean> list = new ArrayList<ProductBean>();

				String sql = "select p.pro_id, p.sp_id, p.pi_id, pi.pi_name, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, sup.shop_name, pd.descr, pd.detail, pi.jan_code, pi.branch_no, p.itemization, pi.ref_type, pi.retail_price, p.wholesale, p.set_quantity, pi.tax_rate_class, pi.shipping_term, pimg.image_1, pimg.image_2, pimg.image_3, pimg.image_4, pimg.image_5, pimg.image_6, pimg.image_7, pimg.image_8, pimg.image_9, pimg.image_10, pi.image_permission, pi.sell_permission, pi.auction_permission, pi.direct_permission, pi.out_of_stock \n"
						+ "from product as p, product_info as pi, product_description as pd, product_images as pimg, supllier as sup, big_category as bc, small_category as sc, category as c \n"
						+ "where p.pi_id = pi.pi_id and p.pd_id = pd.pd_id and p.images_id = pimg.images_id and p.sp_id = sup.sp_id and pi.category_id = c.category_id and c.sc_id = sc.sc_id and sc.bc_id = bc.bd_id and bc.bc_category = ?;";

				try {
					connect();
					// ステートメントの作成
					PreparedStatement ps = getConnection().prepareStatement(sql);
					ps.setString(1, name);

					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						ProductBean bean = new ProductBean();
						bean.setPro_id(rs.getString("pro_id"));
						bean.setSp_id(rs.getInt("sp_id"));
						bean.setPi_id(rs.getString("pi_id"));
						bean.setPd_id(rs.getString("pd_id"));
						bean.setImages_id(rs.getString("images_id"));
						bean.setItemization(rs.getString("itemization"));
						bean.setWholesale(rs.getInt("wholesale"));
						bean.setSet_quantity(rs.getInt("set_quantity"));
						bean.setPi_name(rs.getString("pi_name"));
						bean.setCategory_id(rs.getInt("category_id"));
						bean.setBc_category_1(rs.getString("bc_category_1"));
						bean.setSc_category_1(rs.getString("sc_category_1"));
						bean.setBc_category_2(rs.getString("bc_category_2"));
						bean.setSc_category_2(rs.getString("sc_category_2"));
						bean.setBc_category_3(rs.getString("bc_category_3"));
						bean.setSc_category_3(rs.getString("sc_category_3"));
						bean.setJan_cade(rs.getString("jan_cade"));
						bean.setBranch_no(rs.getInt("branch_no"));
						bean.setRef_type(rs.getString("ref_type"));
						bean.setRetail_price(rs.getInt("retail_price"));
						bean.setTax_rate_class(rs.getInt("tax_rate_class"));
						bean.setShipping_term(rs.getString("shipping_term"));
						bean.setImage_permission(rs.getString("image_permission"));
						bean.setSell_permission(rs.getString("sell_permission"));
						bean.setAuction_permission(rs.getString("auction_permission"));
						bean.setDirect_permission(rs.getString("direct_permission"));
						bean.setOut_of_stock(rs.getString("out_of_stock"));
						bean.setSc_id(rs.getInt("sc_id"));
						bean.setDescr(rs.getString("descr"));
						bean.setDetail(rs.getString("detail"));
						bean.setImages_1(rs.getString("images_1"));
						bean.setImages_2(rs.getString("images_2"));
						bean.setImages_3(rs.getString("images_3"));
						bean.setImages_4(rs.getString("images_4"));
						bean.setImages_5(rs.getString("images_5"));
						bean.setImages_6(rs.getString("images_6"));
						bean.setImages_7(rs.getString("images_7"));
						bean.setImages_8(rs.getString("images_8"));
						bean.setImages_9(rs.getString("images_9"));
						bean.setImages_10(rs.getString("images_10"));
						bean.setShop_name(rs.getString("shop_name"));
						list.add(bean);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconnect();
				}
				return list;
			}
			
			
			//小カテゴリ名で条件をつけて商品情報を検索するメソッド
			public ArrayList<ProductBean> selectSmallCategory(String name) {

				ArrayList<ProductBean> list = new ArrayList<ProductBean>();

				String sql = "select p.pro_id, p.sp_id, p.pi_id, pi.pi_name, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, bc.bc_category, sc.sc_category, sup.shop_name, pd.descr, pd.detail, pi.jan_code, pi.branch_no, p.itemization, pi.ref_type, pi.retail_price, p.wholesale, p.set_quantity, pi.tax_rate_class, pi.shipping_term, pimg.image_1, pimg.image_2, pimg.image_3, pimg.image_4, pimg.image_5, pimg.image_6, pimg.image_7, pimg.image_8, pimg.image_9, pimg.image_10, pi.image_permission, pi.sell_permission, pi.auction_permission, pi.direct_permission, pi.out_of_stock \n"
						+ "from product as p, product_info as pi, product_description as pd, product_images as pimg, supllier as sup, big_category as bc, small_category as sc, category as c \n"
						+ "where p.pi_id = pi.pi_id and p.pd_id = pd.pd_id and p.images_id = pimg.images_id and p.sp_id = sup.sp_id and pi.category_id = c.category_id and c.sc_id = sc.sc_id and sc.bc_id = bc.bd_id and sc.sc_category = ?;";

				try {
					connect();
					// ステートメントの作成
					PreparedStatement ps = getConnection().prepareStatement(sql);
					ps.setString(1, name);

					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						ProductBean bean = new ProductBean();
						bean.setPro_id(rs.getString("pro_id"));
						bean.setSp_id(rs.getInt("sp_id"));
						bean.setPi_id(rs.getString("pi_id"));
						bean.setPd_id(rs.getString("pd_id"));
						bean.setImages_id(rs.getString("images_id"));
						bean.setItemization(rs.getString("itemization"));
						bean.setWholesale(rs.getInt("wholesale"));
						bean.setSet_quantity(rs.getInt("set_quantity"));
						bean.setPi_name(rs.getString("pi_name"));
						bean.setCategory_id(rs.getInt("category_id"));
						bean.setBc_category_1(rs.getString("bc_category_1"));
						bean.setSc_category_1(rs.getString("sc_category_1"));
						bean.setBc_category_2(rs.getString("bc_category_2"));
						bean.setSc_category_2(rs.getString("sc_category_2"));
						bean.setBc_category_3(rs.getString("bc_category_3"));
						bean.setSc_category_3(rs.getString("sc_category_3"));
						bean.setJan_cade(rs.getString("jan_cade"));
						bean.setBranch_no(rs.getInt("branch_no"));
						bean.setRef_type(rs.getString("ref_type"));
						bean.setRetail_price(rs.getInt("retail_price"));
						bean.setTax_rate_class(rs.getInt("tax_rate_class"));
						bean.setShipping_term(rs.getString("shipping_term"));
						bean.setImage_permission(rs.getString("image_permission"));
						bean.setSell_permission(rs.getString("sell_permission"));
						bean.setAuction_permission(rs.getString("auction_permission"));
						bean.setDirect_permission(rs.getString("direct_permission"));
						bean.setOut_of_stock(rs.getString("out_of_stock"));
						bean.setSc_id(rs.getInt("sc_id"));
						bean.setDescr(rs.getString("descr"));
						bean.setDetail(rs.getString("detail"));
						bean.setImages_1(rs.getString("images_1"));
						bean.setImages_2(rs.getString("images_2"));
						bean.setImages_3(rs.getString("images_3"));
						bean.setImages_4(rs.getString("images_4"));
						bean.setImages_5(rs.getString("images_5"));
						bean.setImages_6(rs.getString("images_6"));
						bean.setImages_7(rs.getString("images_7"));
						bean.setImages_8(rs.getString("images_8"));
						bean.setImages_9(rs.getString("images_9"));
						bean.setImages_10(rs.getString("images_10"));
						bean.setShop_name(rs.getString("shop_name"));
						list.add(bean);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconnect();
				}
				return list;
			}
			
			
			//商品情報を追加するメソッド
			public void insert(String pro_id, int sp_id, String pi_id, String pi_name, int bc_id_1, int sc_id_1, int bc_id_2, int sc_id_2, int bc_id_3, int sc_id_3, String shop_name, String descr, String detail, String jan_code, int branch_no, String itemization, String ref_type, int retail_price, int wholesale, int set_quantity, int tax_rate_class, String shipping_term, String image_1, String image_2, String image_3, String image_4, String image_5, String image_6, String image_7, String image_8, String image_9, String image_10, char image_permission, char sell_permisson, char auction_permission, char direct_permission, char out_of_stock) {

				String sql = "insert into product(pro_id,sp_id,pi_id,pd_id,images_id,itemization,wholesale,set_quantity) values (?,?,?,?,?,?,?,?);"
							+"insert into product_info(pi_id,pi_name,category_id,jan_code,branch_no,ref_type,retail_price,tax_rate_class,shipping_term,image_permission,sell_permission,auction_permission,direct_permission,out_of_stock) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);"
							+"insert into product_description(pd_id,descr,detail) values (?,?,?);"
							+"insert into product_images(images_id,image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10) values (?,?,?,?,?,?,?,?,?,?,?);"
							+"insert into supplier(sp_id,shop_name) values (?,?);"
							+"insert into category(category_id,sc_id) values (?,?);"
							+"insert into category(category_id,sc_id) values (?,?);"
							+"insert into category(category_id,sc_id) values (?,?);";

				try {
					connect();
					// ステートメントの作成
					PreparedStatement ps = getConnection().prepareStatement(sql);
					ps.setString(1,pro_id);
					ps.setInt(2,sp_id);
					ps.setString(3,pi_id);
					ps.setString(4,pi_id);
					ps.setString(5,pi_id);
					ps.setString(6,itemization);
					ps.setInt(7,wholesale);
					ps.setInt(8,set_quantity);
					ps.setString(9,pi_id);
					ps.setString(10,pi_name);
					ps.setString(11,pi_id);
					ps.setString(12,jan_code);
					ps.setInt(13,branch_no);
					ps.setString(14,ref_type);
					ps.setInt(15,retail_price);
					ps.setInt(16,tax_rate_class);
					ps.setString(17,shipping_term);
					ps.setString(18,String.valueOf(image_permission));
					ps.setString(19,String.valueOf(sell_permisson));
					ps.setString(20,String.valueOf(auction_permission));
					ps.setString(21,String.valueOf(direct_permission));
					ps.setString(22,String.valueOf(out_of_stock));
					ps.setString(23,pi_id);
					ps.setString(24,descr);
					ps.setString(25,detail);
					ps.setString(26,pi_id);
					ps.setString(27,image_1);
					ps.setString(28,image_2);
					ps.setString(29,image_3);
					ps.setString(30,image_4);
					ps.setString(31,image_5);
					ps.setString(32,image_6);
					ps.setString(33,image_7);
					ps.setString(34,image_8);
					ps.setString(35,image_9);
					ps.setString(36,image_10);
					ps.setInt(37,sp_id);
					ps.setString(38,shop_name);
					ps.setString(39,pi_id);
					ps.setInt(40,sc_id_1);
					ps.setString(41,pi_id);
					ps.setInt(42,sc_id_2);
					ps.setString(43,pi_id);
					ps.setInt(44,sc_id_3);

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconnect();
				}
			}
			

			//商品情報を更新（アップデート）するメソッド
			public void update(String pro_id, String pi_id, int sp_id, int sc_id_1, int sc_id_2, int sc_id_3, String new_pro_id, int new_sp_id, String new_pi_id, String pi_name, int new_sc_id_1, int new_sc_id_2, int new_sc_id_3, String shop_name, String descr, String detail, String jan_code, int branch_no, String itemization, String ref_type, int retail_price, int wholesale, int set_quantity, int tax_rate_class, String shipping_term, String image_1, String image_2, String image_3, String image_4, String image_5, String image_6, String image_7, String image_8, String image_9, String image_10, char image_permission, char sell_permisson, char auction_permission, char direct_permission, char out_of_stock) {

				String sql = "update product set pro_id=?,sp_id=?,pi_id=?,pd_id=?,images_id=?,itemization=?,wholesale=?,set_quantity=? where pro_id=?;"
							+"update product_info set pi_id?,pi_name=?,category_id=?,jan_code=?,branch_no=?,ref_type=?,retail_price=?,tax_rate_class=?,shipping_term=?,image_permission=?,sell_permission=?,auction_permission=?,direct_permission=?,out_of_stock=? where pi_id=?;"
							+"update product_description set pd_id=?,descr=?,detail=? where pd_id=?;"
							+"update product_images set images_id=?,image_1=?,image_2=?,image_3=?,image_4=?,image_5=?,image_6=?,image_7=?,image_8=?,image_9=?,image_10=?) where images_id=?;"
							+"update supplier set sp_id=?,shop_name=? where sp_id=?;"
							+"update category set category_id=?,sc_id=? where category_id=? and sc_id=?;"
							+"update category set category_id=?,sc_id=? where category_id=? and sc_id=?;"
							+"update category set category_id=?,sc_id=? where category_id=? and sc_id=?;";
				

				try {
					connect();
					// ステートメントの作成
					PreparedStatement ps = getConnection().prepareStatement(sql);
					ps.setString(1,new_pro_id);
					ps.setInt(2,sp_id);
					ps.setString(3,new_pi_id);
					ps.setString(4,new_pi_id);
					ps.setString(5,new_pi_id);
					ps.setString(6,itemization);
					ps.setInt(7,wholesale);
					ps.setInt(8,set_quantity);
					ps.setString(9,pro_id);
					ps.setString(10,new_pi_id);
					ps.setString(11,pi_name);
					ps.setString(12,new_pi_id);
					ps.setString(13,jan_code);
					ps.setInt(14,branch_no);
					ps.setString(15,ref_type);
					ps.setInt(16,retail_price);
					ps.setInt(17,tax_rate_class);
					ps.setString(18,shipping_term);
					ps.setString(19,String.valueOf(image_permission));
					ps.setString(20,String.valueOf(sell_permisson));
					ps.setString(21,String.valueOf(auction_permission));
					ps.setString(22,String.valueOf(direct_permission));
					ps.setString(23,String.valueOf(out_of_stock));
					ps.setString(24,pi_id);
					ps.setString(25,new_pi_id);
					ps.setString(26,descr);
					ps.setString(27,detail);
					ps.setString(28,pi_id);
					ps.setString(29,new_pi_id);
					ps.setString(30,image_1);
					ps.setString(31,image_2);
					ps.setString(32,image_3);
					ps.setString(33,image_4);
					ps.setString(34,image_5);
					ps.setString(35,image_6);
					ps.setString(36,image_7);
					ps.setString(37,image_8);
					ps.setString(38,image_9);
					ps.setString(39,image_10);
					ps.setString(40,pi_id);
					ps.setInt(41,new_sp_id);
					ps.setString(42,shop_name);
					ps.setInt(43,sp_id);
					ps.setString(44,new_pi_id);
					ps.setInt(45,new_sc_id_1);
					ps.setString(46,pi_id);
					ps.setInt(47,sc_id_1);
					ps.setString(48,new_pi_id);
					ps.setInt(49,new_sc_id_2);
					ps.setString(50,pi_id);
					ps.setInt(51,sc_id_2);
					ps.setString(52,new_pi_id);
					ps.setInt(53,new_sc_id_3);
					ps.setString(54,pi_id);
					ps.setInt(55,sc_id_3);

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconnect();
				}
			}
			

			//商品情報を削除するメソッド
			public  void delete(String pro_id, String pi_id) {

				String sql_1 = "delete from product where pro_id=?";
				String sql_2 = "select count(pi_id) from product where pi_id=?";
				String sql_3 = "delete from product_info where pi_id=?";

				try {
					connect();
					// ステートメントの作成
					PreparedStatement ps = getConnection().prepareStatement(sql_1);
					ps.setString(1, pro_id);
					
					ps = getConnection().prepareStatement(sql_2);
					ps.setString(1, pi_id);
					ResultSet rs = ps.executeQuery();
					
					if(rs.getInt("count(pi_id)") == 0){
						ps = getConnection().prepareStatement(sql_3);
						ps.setString(1, pi_id);
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					disconnect();
				}
			}
			
			
	
}
