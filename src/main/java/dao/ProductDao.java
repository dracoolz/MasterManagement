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
		
		String sql3 = "select p.pro_id,p.sp_id,p.pi_id,p.images_id,p.pd_id,pi.pi_name,sup.shop_name,pd.descr,pd.detail,pi.jan_code,p.branch_no,p.itemization,pi.ref_type,pi.retail_price,p.wholesale,p.set_quantity,pi.tax_rate_class,pi.shipping_term,pimg.image_1,pimg.image_2,pimg.image_3,pimg.image_4,pimg.image_5,pimg.image_6,pimg.image_7,pimg.image_8,pimg.image_9,pimg.image_10,p.image_permission,p.sell_permission,p.auction_permission,p.direct_send,p.out_of_stock,group_concat(c.sc_id) as category_ids from product as p inner join product_info as pi on p.pi_id = pi.pi_id inner join product_description as pd on p.pd_id = pd.pd_id inner join product_images as pimg on p.images_id = pimg.images_id inner join supplier as sup on p.sp_id = sup.sp_id left join category as c on p.pi_id = c.category_id group by p.pro_id limit 50;";

		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql3);
			ResultSet rs = ps.executeQuery();
			 
			while (rs.next()) {
				ProductBean bean = new ProductBean();
				SmallCategoryDao sdao = new SmallCategoryDao();
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSp_id(rs.getInt("sp_id"));
				bean.setPi_id(rs.getString("pi_id"));
				bean.setPd_id(rs.getString("pd_id"));
				bean.setImages_id(rs.getString("images_id"));
				bean.setItemization(rs.getString("itemization"));
				bean.setWholesale(rs.getInt("wholesale"));
				bean.setSet_quantity(rs.getInt("set_quantity"));
				bean.setPi_name(rs.getString("pi_name"));
				bean.setCategory_id(rs.getString("pi_id"));
				String categories = rs.getString("category_ids");
				
				bean.setBc_category_1("");
				bean.setSc_category_1("");
				bean.setBc_category_2("");
				bean.setSc_category_2("");
				bean.setBc_category_3("");
				bean.setSc_category_3("");
				if(categories != null) {
					String[] parts = categories.split(",");
					try {
						bean.setSc_id1(Integer.parseInt(parts[0]));
						bean.setBc_category_1(sdao.selectSc(bean.getSc_id1()).getBc_category());
						bean.setSc_category_1(sdao.selectSc(bean.getSc_id1()).getSc_category());
					} catch (Exception e){};
					try {
						bean.setSc_id2(Integer.parseInt(parts[1]));
						bean.setBc_category_2(sdao.selectSc(bean.getSc_id2()).getBc_category());
						bean.setSc_category_2(sdao.selectSc(bean.getSc_id2()).getSc_category());
					} catch (Exception e){};
					try {
						bean.setSc_id3(Integer.parseInt(parts[2]));
						bean.setBc_category_2(sdao.selectSc(bean.getSc_id3()).getBc_category());
						bean.setSc_category_2(sdao.selectSc(bean.getSc_id3()).getSc_category());
					} catch (Exception e){};
				}
				
				bean.setJan_code(rs.getString("jan_code"));
				bean.setBranch_no(rs.getString("branch_no"));
				bean.setRef_type(rs.getString("ref_type"));
				bean.setRetail_price(rs.getInt("retail_price"));
				bean.setTax_rate_class(rs.getInt("tax_rate_class"));
				bean.setShipping_term(rs.getString("shipping_term"));
				bean.setImage_permission(rs.getString("image_permission"));
				bean.setSell_permission(rs.getString("sell_permission"));
				bean.setAuction_permission(rs.getString("auction_permission"));
				bean.setDirect_permission(rs.getString("direct_send"));
				bean.setOut_of_stock(rs.getString("out_of_stock"));
				bean.setDescr(rs.getString("descr"));
				bean.setDetail(rs.getString("detail"));
				bean.setImage_1(rs.getString("image_1"));
				bean.setImage_2(rs.getString("image_2"));
				bean.setImage_3(rs.getString("image_3"));
				bean.setImage_4(rs.getString("image_4"));
				bean.setImage_5(rs.getString("image_5"));
				bean.setImage_6(rs.getString("image_6"));
				bean.setImage_7(rs.getString("image_7"));
				bean.setImage_8(rs.getString("image_8"));
				bean.setImage_9(rs.getString("image_9"));
				bean.setImage_10(rs.getString("image_10"));
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
				bean.setCategory_id(rs.getString("category_id"));
				bean.setBc_category_1(rs.getString("bc_category_1"));
				bean.setSc_category_1(rs.getString("sc_category_1"));
				bean.setBc_category_2(rs.getString("bc_category_2"));
				bean.setSc_category_2(rs.getString("sc_category_2"));
				bean.setBc_category_3(rs.getString("bc_category_3"));
				bean.setSc_category_3(rs.getString("sc_category_3"));
				bean.setJan_code(rs.getString("jan_cade"));
				bean.setBranch_no(rs.getString("branch_no"));
				bean.setRef_type(rs.getString("ref_type"));
				bean.setRetail_price(rs.getInt("retail_price"));
				bean.setTax_rate_class(rs.getInt("tax_rate_class"));
				bean.setShipping_term(rs.getString("shipping_term"));
				bean.setImage_permission(rs.getString("image_permission"));
				bean.setSell_permission(rs.getString("sell_permission"));
				bean.setAuction_permission(rs.getString("auction_permission"));
				bean.setDirect_permission(rs.getString("direct_permission"));
				bean.setOut_of_stock(rs.getString("out_of_stock"));
				bean.setSc_id1(rs.getInt("sc_id1"));
				bean.setDescr(rs.getString("descr"));
				bean.setDetail(rs.getString("detail"));
				bean.setImage_1(rs.getString("images_1"));
				bean.setImage_2(rs.getString("images_2"));
				bean.setImage_3(rs.getString("images_3"));
				bean.setImage_4(rs.getString("images_4"));
				bean.setImage_5(rs.getString("images_5"));
				bean.setImage_6(rs.getString("images_6"));
				bean.setImage_7(rs.getString("images_7"));
				bean.setImage_8(rs.getString("images_8"));
				bean.setImage_9(rs.getString("images_9"));
				bean.setImage_10(rs.getString("images_10"));
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
	public ArrayList<ProductBean> selectBc(String name) {
		
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
				bean.setCategory_id(rs.getString("category_id"));
				bean.setBc_category_1(rs.getString("bc_category_1"));
				bean.setSc_category_1(rs.getString("sc_category_1"));
				bean.setBc_category_2(rs.getString("bc_category_2"));
				bean.setSc_category_2(rs.getString("sc_category_2"));
				bean.setBc_category_3(rs.getString("bc_category_3"));
				bean.setSc_category_3(rs.getString("sc_category_3"));
				bean.setJan_code(rs.getString("jan_code"));
				bean.setBranch_no(rs.getString("branch_no"));
				bean.setRef_type(rs.getString("ref_type"));
				bean.setRetail_price(rs.getInt("retail_price"));
				bean.setTax_rate_class(rs.getInt("tax_rate_class"));
				bean.setShipping_term(rs.getString("shipping_term"));
				bean.setImage_permission(rs.getString("image_permission"));
				bean.setSell_permission(rs.getString("sell_permission"));
				bean.setAuction_permission(rs.getString("auction_permission"));
				bean.setDirect_permission(rs.getString("direct_permission"));
				bean.setOut_of_stock(rs.getString("out_of_stock"));
				bean.setSc_id1(rs.getInt("sc_id1"));
				bean.setDescr(rs.getString("descr"));
				bean.setDetail(rs.getString("detail"));
				bean.setImage_1(rs.getString("images_1"));
				bean.setImage_2(rs.getString("images_2"));
				bean.setImage_3(rs.getString("images_3"));
				bean.setImage_4(rs.getString("images_4"));
				bean.setImage_5(rs.getString("images_5"));
				bean.setImage_6(rs.getString("images_6"));
				bean.setImage_7(rs.getString("images_7"));
				bean.setImage_8(rs.getString("images_8"));
				bean.setImage_9(rs.getString("images_9"));
				bean.setImage_10(rs.getString("images_10"));
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
	public ArrayList<ProductBean> selectSc(String name) {
		
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
				bean.setCategory_id(rs.getString("category_id"));
				bean.setBc_category_1(rs.getString("bc_category_1"));
				bean.setSc_category_1(rs.getString("sc_category_1"));
				bean.setBc_category_2(rs.getString("bc_category_2"));
				bean.setSc_category_2(rs.getString("sc_category_2"));
				bean.setBc_category_3(rs.getString("bc_category_3"));
				bean.setSc_category_3(rs.getString("sc_category_3"));
				bean.setJan_code(rs.getString("jan_code"));
				bean.setBranch_no(rs.getString("branch_no"));
				bean.setRef_type(rs.getString("ref_type"));
				bean.setRetail_price(rs.getInt("retail_price"));
				bean.setTax_rate_class(rs.getInt("tax_rate_class"));
				bean.setShipping_term(rs.getString("shipping_term"));
				bean.setImage_permission(rs.getString("image_permission"));
				bean.setSell_permission(rs.getString("sell_permission"));
				bean.setAuction_permission(rs.getString("auction_permission"));
				bean.setDirect_permission(rs.getString("direct_permission"));
				bean.setOut_of_stock(rs.getString("out_of_stock"));
				bean.setSc_id1(rs.getInt("sc_id1"));
				bean.setDescr(rs.getString("descr"));
				bean.setDetail(rs.getString("detail"));
				bean.setImage_1(rs.getString("images_1"));
				bean.setImage_2(rs.getString("images_2"));
				bean.setImage_3(rs.getString("images_3"));
				bean.setImage_4(rs.getString("images_4"));
				bean.setImage_5(rs.getString("images_5"));
				bean.setImage_6(rs.getString("images_6"));
				bean.setImage_7(rs.getString("images_7"));
				bean.setImage_8(rs.getString("images_8"));
				bean.setImage_9(rs.getString("images_9"));
				bean.setImage_10(rs.getString("images_10"));
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
	
	//商品画像IDの最大値+1を返すメソッド
	public int selectMax() {
		
		ProductBean bean = new ProductBean();
		String sql = "select max(images_id) as mx from product_images;";
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				bean.setImage_id(rs.getInt("mx"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return bean.getImage_id()+1;
	}
	
	public String selectImage(String pro_id) {
		
		String bean = null;
		String sql = "select images_id from product where pro_id = ?;";
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, pro_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				bean = rs.getString("images_id");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return bean;
	}
	
	public String selectShop(String sp_id) {
		
		String bean = null;
		String sql = "select shop_name from supplier where sp_id = ?;";
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, sp_id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				bean = rs.getString("shop_name");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return bean;
	}
	
	public ProductBean select(String pro_id) {
		
		ProductBean bean = new ProductBean();
		SmallCategoryDao sdao = new SmallCategoryDao();
		String sql = "select p.pro_id,p.sp_id,p.pi_id,p.images_id,p.pd_id,pi.pi_name,sup.shop_name,pd.descr,pd.detail,pi.jan_code,p.branch_no,p.itemization,pi.ref_type,pi.retail_price,p.wholesale,p.set_quantity,pi.tax_rate_class,pi.shipping_term,pimg.image_1,pimg.image_2,pimg.image_3,pimg.image_4,pimg.image_5,pimg.image_6,pimg.image_7,pimg.image_8,pimg.image_9,pimg.image_10,p.image_permission,p.sell_permission,p.auction_permission,p.direct_send,p.out_of_stock,group_concat(c.sc_id) as category_ids from product as p inner join product_info as pi on p.pi_id = pi.pi_id inner join product_description as pd on p.pd_id = pd.pd_id inner join product_images as pimg on p.images_id = pimg.images_id inner join supplier as sup on p.sp_id = sup.sp_id left join category as c on p.pi_id = c.category_id where p.pro_id = ? group by p.pro_id;";
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, pro_id);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bean.setPro_id(rs.getString("pro_id"));
				bean.setSp_id(rs.getInt("sp_id"));
				bean.setPi_id(rs.getString("pi_id"));
				bean.setPd_id(rs.getString("pd_id"));
				bean.setImages_id(rs.getString("images_id"));
				bean.setItemization(rs.getString("itemization"));
				bean.setWholesale(rs.getInt("wholesale"));
				bean.setSet_quantity(rs.getInt("set_quantity"));
				bean.setPi_name(rs.getString("pi_name"));
				bean.setCategory_id(rs.getString("pi_id"));
				
				String categories = rs.getString("category_ids");
				if(categories != null) {
					String[] parts = categories.split(",");
					
					try {
						bean.setSc_id1(Integer.parseInt(parts[0]));
						bean.setBc_category_1(sdao.selectSc(bean.getSc_id1()).getBc_category());
						bean.setSc_category_1(sdao.selectSc(bean.getSc_id1()).getSc_category());
					} catch (Exception e){};
					try {
						bean.setSc_id2(Integer.parseInt(parts[1]));
						bean.setBc_category_2(sdao.selectSc(bean.getSc_id2()).getBc_category());
						bean.setSc_category_2(sdao.selectSc(bean.getSc_id2()).getSc_category());
					} catch (Exception e){};
					try {
						bean.setSc_id3(Integer.parseInt(parts[2]));
						bean.setBc_category_3(sdao.selectSc(bean.getSc_id3()).getBc_category());
						bean.setSc_category_3(sdao.selectSc(bean.getSc_id3()).getSc_category());
					} catch (Exception e){};
				}
				bean.setJan_code(rs.getString("jan_code"));
				bean.setBranch_no(rs.getString("branch_no"));
				bean.setRef_type(rs.getString("ref_type"));
				bean.setRetail_price(rs.getInt("retail_price"));
				bean.setTax_rate_class(rs.getInt("tax_rate_class"));
				bean.setShipping_term(rs.getString("shipping_term"));
				bean.setImage_permission(rs.getString("image_permission"));
				bean.setSell_permission(rs.getString("sell_permission"));
				bean.setAuction_permission(rs.getString("auction_permission"));
				bean.setDirect_permission(rs.getString("direct_send"));
				bean.setOut_of_stock(rs.getString("out_of_stock"));
				bean.setDescr(rs.getString("descr"));
				bean.setDetail(rs.getString("detail"));
				bean.setImage_1(rs.getString("image_1"));
				bean.setImage_2(rs.getString("image_2"));
				bean.setImage_3(rs.getString("image_3"));
				bean.setImage_4(rs.getString("image_4"));
				bean.setImage_5(rs.getString("image_5"));
				bean.setImage_6(rs.getString("image_6"));
				bean.setImage_7(rs.getString("image_7"));
				bean.setImage_8(rs.getString("image_8"));
				bean.setImage_9(rs.getString("image_9"));
				bean.setImage_10(rs.getString("image_10"));
				System.out.println(bean.getImage_10());
				bean.setShop_name(rs.getString("shop_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return bean;
	}
	
	//商品情報を追加するメソッド
	public void insert(String pro_id, int sp_id, String pi_id, String pi_name, int bc_id_1, int sc_id_1, int bc_id_2, int sc_id_2, int bc_id_3, int sc_id_3, String shop_name, String descr, String detail, String jan_code, String branch_no, String itemization, String ref_type, int retail_price, int wholesale, int set_quantity, int tax_rate_class, String shipping_term, String image_1, String image_2, String image_3, String image_4, String image_5, String image_6, String image_7, String image_8, String image_9, String image_10, char image_permission, char sell_permisson, char auction_permission, char direct_permission, char out_of_stock) {
		
		String sql1 = "insert into product(pro_id,sp_id,pi_id,pd_id,images_id,itemization,wholesale,set_quantity, branch_no, image_permission,sell_permission,auction_permission,direct_send,out_of_stock) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		String sql2 = "insert into product_info(pi_id,pi_name,category_id,jan_code,ref_type,retail_price,tax_rate_class,shipping_term) values (?,?,?,?,?,?,?,?);";
		String sql3 = "insert into product_description(pd_id,descr,detail) values (?,?,?);";
		String sql4 = "insert into product_images(images_id, image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10) values (?,?,?,?,?,?,?,?,?,?,?);";
		String sql6 = "insert into category(category_id,sc_id) values (?,?);";
		String sql7 = "insert into category(category_id,sc_id) values (?,?);";
		String sql8 = "insert into category(category_id,sc_id) values (?,?);";
		
		int images_id = selectMax();
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql2);
			ps.setString(1,pi_id);
			ps.setString(2,pi_name);
			ps.setString(3,pi_id);
			ps.setString(4,jan_code);
			ps.setString(5,ref_type);
			ps.setInt(6,retail_price);
			ps.setInt(7,tax_rate_class);
			ps.setString(8,shipping_term);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql3);
			ps.setString(1,pi_id);
			ps.setString(2,descr);
			ps.setString(3,detail);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql4);
			ps.setInt(1,images_id);
			ps.setString(2,image_1);
			ps.setString(3,image_2);
			ps.setString(4,image_3);
			ps.setString(5,image_4);
			ps.setString(6,image_5);
			ps.setString(7,image_6);
			ps.setString(8,image_7);
			ps.setString(9,image_8);
			ps.setString(10,image_9);
			ps.setString(11,image_10);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql1);
			ps.setString(1,pro_id);
			ps.setInt(2,sp_id);
			ps.setString(3,pi_id);
			ps.setString(4,pi_id);
			ps.setInt(5,images_id);
			ps.setString(6,itemization);
			ps.setInt(7,wholesale);
			ps.setInt(8,set_quantity);
			ps.setString(9,branch_no);
			ps.setString(10,String.valueOf(image_permission));
			ps.setString(11,String.valueOf(sell_permisson));
			ps.setString(12,String.valueOf(auction_permission));
			ps.setString(13,String.valueOf(direct_permission));
			ps.setString(14,String.valueOf(out_of_stock));
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql6);
			ps.setString(1,pi_id);
			ps.setInt(2,sc_id_1);
			ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			disconnect();
		}
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql7);
			ps.setString(1,pi_id);
			ps.setInt(2,sc_id_2);
			ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			disconnect();
		}
		
		try {
			connect();
			// ステートメントの作成
			PreparedStatement ps = getConnection().prepareStatement(sql8);
			ps.setString(1,pi_id);
			ps.setInt(2,sc_id_3);
			ps.executeUpdate();
		} catch (SQLException e) {
			//e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	
	//商品情報を更新（アップデート）するメソッド
	public void update(String old_pro_id, String pro_id, String pi_id, int sp_id, int sc_id_1, int sc_id_2, int sc_id_3, String new_pro_id, int new_sp_id, String old_pi_id, String pi_name, int new_sc_id_1, int new_sc_id_2, int new_sc_id_3, String shop_name, String descr, String detail, String jan_code, String branch_no, String itemization, String ref_type, int retail_price, int wholesale, int set_quantity, int tax_rate_class, String shipping_term, String image_1, String image_2, String image_3, String image_4, String image_5, String image_6, String image_7, String image_8, String image_9, String image_10, char image_permission, char sell_permisson, char auction_permission, char direct_permission, char out_of_stock) {
		delete(old_pro_id,old_pi_id, selectImage(pro_id));
		insert(pro_id, sp_id, pi_id, pi_name, 0, sc_id_1, 0, sc_id_2, 0, sc_id_3, shop_name, descr, detail, jan_code, branch_no, itemization, ref_type, retail_price, wholesale, set_quantity, tax_rate_class, shipping_term, image_1, image_2, image_3, image_4, image_5, image_6, image_7, image_8, image_9, image_10, image_permission, sell_permisson, auction_permission, direct_permission, out_of_stock);
	}
	
	public void delete(String pro_id, String pi_id, String images_id) {
		String sql_1 = "delete from product where pro_id=?;";
		String sql_2 = "select count(pi_id) from product where pi_id=?;";
		String sql_3 = "delete from product_info where pi_id=?;";
		String sql_4 = "delete from product_description where pd_id=?;";
		String sql_5 = "delete from product_images where images_id=?;";
		String sql_6 = "delete from category where category_id = ?;";
		String sql_7 = "select count(images_id) from product where images_id=?;";
		
		PreparedStatement ps = null;
		ResultSet rs = null; 
		
		System.out.println(pro_id);
		System.out.println(pi_id);
		System.out.println(images_id);
		
		try {
			connect();
			// ステートメントの作成
			
			ps = getConnection().prepareStatement(sql_1);
			
			ps.setString(1, pro_id);
			ps.executeUpdate();
			
			ps = getConnection().prepareStatement(sql_2);
			ps.setString(1, pi_id);
			rs = ps.executeQuery();
			rs.next();
			System.out.println(rs.getInt(1));
			if (rs.getInt(1) == 0) {
				ps = getConnection().prepareStatement(sql_3);
				ps.setString(1, pi_id);
				ps.executeUpdate();
				
				ps = getConnection().prepareStatement(sql_4);
				ps.setString(1, pi_id);
				ps.executeUpdate();
				
				ps = getConnection().prepareStatement(sql_6);
				ps.setString(1, pi_id);
				ps.executeUpdate();
			}
			
			ps = getConnection().prepareStatement(sql_7);
			ps.setString(1, images_id);
			rs = ps.executeQuery();
			rs.next();
			if (rs.getInt(1) == 0) {
				ps = getConnection().prepareStatement(sql_5);
				ps.setString(1, images_id);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}
}
