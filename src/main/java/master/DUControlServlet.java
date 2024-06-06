package master;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductBean;
import bean.UserBean;
import dao.ProductDao;
import dao.UserDao;

public class DUControlServlet extends HttpServlet {

	static final long serialVersionUID = 1L;


	public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
			doPost( request, response );
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		String forward=null;
		String str = request.getParameter("submit");
		String mod = request.getParameter("mod");
		
		if(request.getParameter("type").equals("user")) {
			
			if(request.getParameter("submit").equals("登録")){
				UserDao dao = new UserDao();
				session.setAttribute("id", dao.selectMax());
				session.setAttribute("name","");
				session.setAttribute("furigana", "");
				session.setAttribute("email", "");
				session.setAttribute("pass", "");
				session.setAttribute("role", 0);
				
				forward="/jsp/userMod.jsp?submit=登録";
			 } else if(request.getParameter("submit").equals("変更")){
				int id = Integer.parseInt(request.getParameter("id"));
				UserDao dao = new UserDao();
				UserBean bean = dao.select(id);
				session.setAttribute("id", id);
				session.setAttribute("name",bean.getEmp_name());
				session.setAttribute("furigana", bean.getFurigana());
				session.setAttribute("email", bean.getEmp_email());
				session.setAttribute("role", bean.getRole());
				
				forward="/jsp/userMod.jsp?submit=変更";
				
			 } else if(request.getParameter("submit").equals("削除")){
				int id = Integer.parseInt(request.getParameter("id"));
				UserDao dao = new UserDao();
				UserBean bean = dao.select(id);
				session.setAttribute("id", id);
				session.setAttribute("name", bean.getEmp_name());
				session.setAttribute("furigana", bean.getFurigana());
				session.setAttribute("email", bean.getEmp_email());
				session.setAttribute("role", bean.getRole());
				
				forward="/jsp/userConf.jsp?submit=削除";
			 } else if(str.equals("検索")){
					
					UserDao dao = new UserDao();
					ArrayList<UserBean> list = new ArrayList<UserBean>();
					
					list = dao.selectPart(request.getParameter("searchWord"));
					request.setAttribute("list", list);
					
					forward="/jsp/user.jsp";
			 } else if(request.getParameter("submit").equals("戻る")){
				 if(request.getParameter("status").equals("登録")) {
					 forward="/jsp/userMod.jsp?submit=登録";
				 } else {
					 forward="/jsp/userMod.jsp?submit=変更"; 
				 }
			}
		}		
		
		
		if(request.getParameter("type").equals("category")) {
			if(request.getParameter("categoryType").equals("bc")) {
			if(str.equals("登録")){

				forward="/jsp/categoryMod.jsp?submit=登録";

			 }
			
			if(str.equals("変更")){

				session.setAttribute("bc_id", request.getParameter("id"));
				session.setAttribute("bc_category", request.getParameter("name"));
				forward="/jsp/categoryMod.jsp?submit=変更&type=bc";

			 }

			if(str.equals("削除")){
				
				session.setAttribute("bc_id", request.getParameter("id"));
				session.setAttribute("bc_category", request.getParameter("name"));
				forward="/jsp/categoryConf.jsp?submit=削除";

			 }
			

			if(str.equals("戻る")){
				if(mod.equals("登録")) {
					forward="/jsp/categoryMod.jsp?submit=登録";
				}else if(mod.equals("変更")) {
					forward="/jsp/categoryMod.jsp?submit=変更&type=bc";
				}else if(mod.equals("削除")) {
					forward="/jsp/categoryMod.jsp?submit=削除";
				}
			}
		}
		}
		
			if(request.getParameter("type").equals("category")) {
				if(request.getParameter("categoryType").equals("sc")) {
				if(str.equals("登録")){

					forward="/jsp/categoryMod.jsp?submit=登録";

				 }
				
				if(str.equals("変更")){

					session.setAttribute("sc_id", request.getParameter("sc_id"));
					session.setAttribute("sc_category", request.getParameter("sc_name"));
					forward="/jsp/categoryMod.jsp?submit=変更&type=sc";

				 }

				if(str.equals("削除")){

					session.setAttribute("sc_id", request.getParameter("sc_id"));
					session.setAttribute("sc_category", request.getParameter("sc_name"));
					forward="/jsp/categoryConf.jsp?submit=削除";

				 }
				

				if(str.equals("戻る")){
					if(mod.equals("登録")) {
						forward="/jsp/categoryMod.jsp?submit=登録";
					}else if(mod.equals("変更")) {
						forward="/jsp/categoryMod.jsp?submit=変更&type=sc";
					}else if(mod.equals("削除")) {
						forward="/jsp/categoryMod.jsp?submit=削除";
					}
				}
			}
		}
		
		if(request.getParameter("type").equals("product")) {
			if(request.getParameter("submit").equals("登録")){
				session.setAttribute("pro_id","");
				session.setAttribute("sp_id","");
				session.setAttribute("pi_id","");
				session.setAttribute("pi_name","");
				session.setAttribute("sc_id_1","");
				session.setAttribute("sc_id_2","");
				session.setAttribute("sc_id_3","");
				session.setAttribute("shop_name","");
				session.setAttribute("descr","");
				session.setAttribute("detail","");
				session.setAttribute("jan_code","");
				session.setAttribute("branch_no","");
				session.setAttribute("itemization","");
				session.setAttribute("ref_type","");
				session.setAttribute("retail_price","");
				session.setAttribute("wholesale","");
				session.setAttribute("set_quantity","");
				session.setAttribute("tax_rate_class","");
				session.setAttribute("shipping_term","");
				session.setAttribute("image_1","");
				session.setAttribute("image_2","");
				session.setAttribute("image_3","");
				session.setAttribute("image_4","");
				session.setAttribute("image_5","");
				session.setAttribute("image_6","");
				session.setAttribute("image_7","");
				session.setAttribute("image_8","");
				session.setAttribute("image_9","");
				session.setAttribute("image_10","");
				session.setAttribute("image_permission","");
				session.setAttribute("sell_permission","");
				session.setAttribute("auction_permission","");
				session.setAttribute("direct_permission","");
				session.setAttribute("out_of_stock","");
				
				forward="/jsp/productMod.jsp?submit=登録";
				
			} else if(request.getParameter("submit").equals("変更")){
				String id = request.getParameter("id");
				ProductDao dao = new ProductDao();
				ProductBean bean = dao.select(id);
				
				session.setAttribute("old_pro_id", bean.getPro_id());
				session.setAttribute("pro_id",bean.getPro_id());
				session.setAttribute("sp_id",bean.getSp_id());
				session.setAttribute("pi_id",bean.getPi_id());
				session.setAttribute("old_pi_id",bean.getPi_id());
				session.setAttribute("pi_name",bean.getPi_name());
				session.setAttribute("sc_id_1",bean.getSc_category_1());
				session.setAttribute("sc_id_2",bean.getSc_category_2());
				session.setAttribute("sc_id_3",bean.getSc_category_3());
				session.setAttribute("shop_name",bean.getShop_name());
				session.setAttribute("descr",bean.getDescr());
				session.setAttribute("detail",bean.getDetail());
				session.setAttribute("jan_code",bean.getJan_code());
				session.setAttribute("branch_no",bean.getBranch_no());
				session.setAttribute("itemization",bean.getItemization());
				session.setAttribute("ref_type",bean.getRef_type());
				session.setAttribute("retail_price",bean.getRetail_price());
				session.setAttribute("wholesale",bean.getWholesale());
				session.setAttribute("set_quantity",bean.getSet_quantity());
				session.setAttribute("tax_rate_class",bean.getTax_rate_class());
				session.setAttribute("shipping_term",bean.getShipping_term());
				session.setAttribute("image_1",bean.getImage_1());
				session.setAttribute("image_2",bean.getImage_2());
				session.setAttribute("image_3",bean.getImage_3());
				session.setAttribute("image_4",bean.getImage_4());
				session.setAttribute("image_5",bean.getImage_5());
				session.setAttribute("image_6",bean.getImage_6());
				session.setAttribute("image_7",bean.getImage_7());
				session.setAttribute("image_8",bean.getImage_8());
				session.setAttribute("image_9",bean.getImage_9());
				session.setAttribute("image_10",bean.getImage_10());
				session.setAttribute("image_permission",bean.getImage_permission());
				session.setAttribute("sell_permission",bean.getSell_permission());
				session.setAttribute("auction_permission",bean.getAuction_permission());
				session.setAttribute("direct_permission",bean.getDirect_permission());
				session.setAttribute("out_of_stock",bean.getOut_of_stock());
				 
				forward="/jsp/productMod.jsp?submit=変更";
			} else if(request.getParameter("submit").equals("削除")){
				String id = request.getParameter("id");
				ProductDao dao = new ProductDao();
				ProductBean bean = dao.select(id);
					 
				session.setAttribute("pro_id",bean.getPro_id());
				session.setAttribute("sp_id",bean.getSp_id());
				session.setAttribute("images_id",bean.getImages_id());
				session.setAttribute("pi_id",bean.getPi_id());
				session.setAttribute("pi_name",bean.getPi_name());
				nullChange("sc_1",bean.getSc_category_1(),request);
				nullChange("sc_2",bean.getSc_category_2(),request);
				nullChange("sc_3",bean.getSc_category_3(),request);
				nullChange("bc_1",bean.getBc_category_1(),request);
				nullChange("bc_2",bean.getBc_category_2(),request);
				nullChange("bc_3",bean.getBc_category_3(),request);
				session.setAttribute("shop_name",bean.getShop_name());
				nullChange("descr",bean.getDescr(),request);
				nullChange("detail",bean.getDetail(),request);
				session.setAttribute("jan_code",bean.getJan_code());
				session.setAttribute("branch_no",bean.getBranch_no());
				session.setAttribute("itemization",bean.getItemization());
				session.setAttribute("ref_type",bean.getRef_type());
				session.setAttribute("retail_price",bean.getRetail_price());
				session.setAttribute("wholesale",bean.getWholesale());
				session.setAttribute("set_quantity",bean.getSet_quantity());
				session.setAttribute("tax_rate_class",bean.getTax_rate_class());
				session.setAttribute("shipping_term",bean.getShipping_term());
				session.setAttribute("image_1",bean.getImage_1());
				session.setAttribute("image_2",bean.getImage_2());
				session.setAttribute("image_3",bean.getImage_3());
				session.setAttribute("image_4",bean.getImage_4());
				session.setAttribute("image_5",bean.getImage_5());
				session.setAttribute("image_6",bean.getImage_6());
				session.setAttribute("image_7",bean.getImage_7());
				session.setAttribute("image_8",bean.getImage_8());
				session.setAttribute("image_9",bean.getImage_9());
				session.setAttribute("image_10",bean.getImage_10());
				session.setAttribute("image_permission",bean.getImage_permission());
				session.setAttribute("sell_permission",bean.getSell_permission());
				session.setAttribute("auction_permission",bean.getAuction_permission());
				session.setAttribute("direct_permission",bean.getDirect_permission());
				session.setAttribute("out_of_stock",bean.getOut_of_stock());
				forward="/jsp/productConf.jsp?submit=削除";
			} else if(request.getParameter("submit").equals("戻る")){
				if(request.getParameter("status").equals("登録")) {
					forward="/jsp/productMod.jsp?submit=登録";
				} else {
					forward="/jsp/productMod.jsp?submit=変更"; 
				}
			}
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}
	
	public void nullChange(String str1, String str2, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if(str2 == null) {
			session.setAttribute(str1,"");
		} else {
			session.setAttribute(str1,str2);
		}
	}
}
