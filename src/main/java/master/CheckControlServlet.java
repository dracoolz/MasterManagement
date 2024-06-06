package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BigCategoryDao;
import dao.ProductDao;
import dao.SmallCategoryDao;
import dao.UserDao;

public class CheckControlServlet extends HttpServlet {

	static final long serialVersionUID = 1L;


	public void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost( request, response );
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//セッションの取得
		HttpSession session = request.getSession(true);
		
		String forward=null;
		
		if(request.getParameter("type").equals("user")) {
			if(request.getParameter("submit").equals("登録")){
				String name = (String)session.getAttribute("name");
				String furigana = (String)session.getAttribute("furigana");
				String email = (String)session.getAttribute("email");
				String pass = (String)session.getAttribute("pass");
				int kubun = (int)session.getAttribute("kubun");
				
				UserDao dao= new UserDao();
				dao.insert(name,furigana,email,pass,kubun);
				forward="/jsp/userComp.jsp?submit=登録&status=unchange";
			}
			if(request.getParameter("submit").equals("変更")){
				int id = (int)session.getAttribute("id");
				String name = (String)session.getAttribute("name");
				String furigana = (String)session.getAttribute("furigana");
				String email = (String)session.getAttribute("email");
				int kubun = (int)session.getAttribute("kubun");
				
				UserDao dao= new UserDao();
				dao.update(id,name,furigana,email,kubun);
				
				forward="/jsp/userComp.jsp?submit=変更&status=unchange";
				if(id == (int)session.getAttribute("userid")) {
					session.setAttribute("userid", id);
					session.setAttribute("username", name);
					session.setAttribute("userrole", kubun);
					
					if(kubun == 1) {
						forward="/jsp/userComp.jsp?submit=変更&status=change";
					}
				}
			}
			if(request.getParameter("submit").equals("削除")){
				int id = (int)session.getAttribute("id");
				
				UserDao dao= new UserDao();
				dao.delete(id);
				forward="/jsp/userComp.jsp?submit=削除&status=unchange";
			}
		}
		
		
		if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("bc")) {
			request.setAttribute("categoryType", "bc");

			if(request.getParameter("submit").equals("登録")){

				int id = Integer.parseInt((String)session.getAttribute("bc_id"));
				String name = (String)session.getAttribute("bc_category");

				BigCategoryDao dao= new BigCategoryDao();
				dao.insert(id,name);
				forward="/jsp/categoryComp.jsp?submit=登録&category=bc";

			}else if(request.getParameter("submit").equals("変更")){

				int id = Integer.parseInt((String)session.getAttribute("bc_id"));
				String new_name = (String)session.getAttribute("new_bc_category");

				BigCategoryDao dao= new BigCategoryDao();
				dao.update(id,new_name);
				forward="/jsp/categoryComp.jsp?submit=変更&category=bc";

			}else if(request.getParameter("submit").equals("削除")){
				System.out.println(session.getAttribute("bc_id"));
				int id = Integer.parseInt((String)session.getAttribute("bc_id"));

				BigCategoryDao dao= new BigCategoryDao();
				dao.delete(id);
				forward="/jsp/categoryComp.jsp?submit=削除&category=bc";

			}

		}else if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("sc")) {
			request.setAttribute("categoryType", "sc");

			if(request.getParameter("submit").equals("登録")){

				int sc_id = Integer.parseInt((String)session.getAttribute("sc_id"));
				int bc_id = Integer.parseInt((String)session.getAttribute("bc_id"));
				String name = (String)session.getAttribute("sc_category");

				SmallCategoryDao dao= new SmallCategoryDao();
				dao.insert(sc_id,bc_id,name);
				forward="/jsp/categoryComp.jsp?submit=登録&category=sc";

			}else if(request.getParameter("submit").equals("変更")){

				int sc_id = Integer.parseInt((String)session.getAttribute("sc_id"));
				int bc_id = Integer.parseInt((String)session.getAttribute("bc_id"));
				String name = (String)session.getAttribute("sc_category");

				SmallCategoryDao dao= new SmallCategoryDao();
				dao.update(sc_id,bc_id,name);
				forward="/jsp/categoryComp.jsp?submit=変更&category=sc";

			}else if(request.getParameter("submit").equals("削除")){

				int id = Integer.parseInt((String)session.getAttribute("sc_id"));

				SmallCategoryDao dao= new SmallCategoryDao();
				dao.delete(id);
				forward="/jsp/categoryComp.jsp?submit=削除&category=sc";

			}

		}


				if(request.getParameter("type").equals("product")) {
			
			if(request.getParameter("submit").equals("登録")){
				
				String pro_id = (String)session.getAttribute("pro_id");
				int sp_id = Integer.parseInt((String)session.getAttribute("sp_id"));
				String pi_id = (String)session.getAttribute("pi_id");
				String pi_name = (String)session.getAttribute("pi_name");
				int bc_id_1 = 0;
				int sc_id_1 = 0;
				int bc_id_2 = 0;
				int sc_id_2 = 0;
				int bc_id_3 = 0;
				int sc_id_3 = 0;
				
				if(session.getAttribute("bc_id_1") != null && session.getAttribute("bc_id_1") != "") {
					bc_id_1 = Integer.parseInt((String)session.getAttribute("bc_id_1"));
				}
				if(session.getAttribute("sc_id_1") != null && session.getAttribute("sc_id_1") != "") {
					sc_id_1 = Integer.parseInt((String)session.getAttribute("sc_id_1"));
				}
				if(session.getAttribute("bc_id_2") != null && session.getAttribute("bc_id_2") != "") {
					bc_id_2 = Integer.parseInt((String)session.getAttribute("bc_id_2"));
				}
				if(session.getAttribute("sc_id_2") != null && session.getAttribute("sc_id_2") != "") {
					sc_id_2 = Integer.parseInt((String)session.getAttribute("sc_id_2"));
				}
				if(session.getAttribute("bc_id_3") != null && session.getAttribute("bc_id_3") != "") {
					bc_id_3 = Integer.parseInt((String)session.getAttribute("bc_id_3"));
				}
				if(session.getAttribute("sc_id_3") != null && session.getAttribute("sc_id_3") != "") {
					sc_id_3 = Integer.parseInt((String)session.getAttribute("sc_id_3"));
				}
				String shop_name = (String)session.getAttribute("shop_name");
				String descr = (String)session.getAttribute("descr");
				String detail = (String)session.getAttribute("detail");
				String jan_code = (String)session.getAttribute("jan_code");
				String branch_no = (String)session.getAttribute("branch_no");
				String itemization = (String)session.getAttribute("itemization");
				String ref_type = (String)session.getAttribute("ref_type");
				int retail_price = 0;
				if(session.getAttribute("retail_price") != null && session.getAttribute("retail_price") != "") {
					retail_price = Integer.parseInt((String)session.getAttribute("retail_price"));
				}
				int wholesale = Integer.parseInt((String)(session.getAttribute("wholesale")));
				int set_quantity = Integer.parseInt((String)(session.getAttribute("set_quantity")));
				int tax_rate_class = Integer.parseInt((String)(session.getAttribute("tax_rate_class")));
				String shipping_term = (String)session.getAttribute("shipping_term");
				String image_1 = (String)session.getAttribute("image_1");
				String image_2 = (String)session.getAttribute("image_2");
				String image_3 = (String)session.getAttribute("image_3");
				String image_4 = (String)session.getAttribute("image_4");
				String image_5 = (String)session.getAttribute("image_5");
				String image_6 = (String)session.getAttribute("image_6");
				String image_7 = (String)session.getAttribute("image_7");
				String image_8 = (String)session.getAttribute("image_8");
				String image_9 = (String)session.getAttribute("image_9");
				String image_10 = (String)session.getAttribute("image_10");
				char[] image_permission = ((String)session.getAttribute("image_permission")).toCharArray();
				char[] sell_permission = ((String)session.getAttribute("sell_permission")).toCharArray();
				char[] auction_permission = ((String)session.getAttribute("auction_permission")).toCharArray();
				char[] direct_permission = ((String)session.getAttribute("direct_permission")).toCharArray();
				char[] out_of_stock = ((String)session.getAttribute("out_of_stock")).toCharArray();
				
				ProductDao dao= new ProductDao();
				dao.insert(pro_id,sp_id,pi_id,pi_name,bc_id_1,sc_id_1,bc_id_2,sc_id_2,bc_id_3,sc_id_3,shop_name,descr,detail,jan_code,branch_no,itemization,ref_type,retail_price,wholesale,set_quantity,tax_rate_class,shipping_term,image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10,image_permission[0],sell_permission[0],auction_permission[0],direct_permission[0],out_of_stock[0]);
				forward="/jsp/productComp.jsp?submit=登録";
				
			}
			
			if(request.getParameter("submit").equals("変更")){
				
				String old_pro_id = (String)session.getAttribute("old_pro_id");
				String old_pi_id = (String)session.getAttribute("old_pi_id");
				String pro_id = (String)session.getAttribute("pro_id");
				int sp_id = Integer.parseInt((String)session.getAttribute("sp_id"));
				String pi_id = (String)session.getAttribute("pi_id");
				String pi_name = (String)session.getAttribute("pi_name");
				int bc_id_1 = 0;
				int sc_id_1 = 0;
				int bc_id_2 = 0;
				int sc_id_2 = 0;
				int bc_id_3 = 0;
				int sc_id_3 = 0;
				
				if(session.getAttribute("bc_id_1") != null && session.getAttribute("bc_id_1") != "") {
					bc_id_1 = Integer.parseInt((String)session.getAttribute("bc_id_1"));
				}
				if(session.getAttribute("sc_id_1") != null && session.getAttribute("sc_id_1") != "") {
					sc_id_1 = Integer.parseInt((String)session.getAttribute("sc_id_1"));
				}
				if(session.getAttribute("bc_id_2") != null && session.getAttribute("bc_id_2") != "") {
					bc_id_2 = Integer.parseInt((String)session.getAttribute("bc_id_2"));
				}
				if(session.getAttribute("sc_id_2") != null && session.getAttribute("sc_id_2") != "") {
					sc_id_2 = Integer.parseInt((String)session.getAttribute("sc_id_2"));
				}
				if(session.getAttribute("bc_id_3") != null && session.getAttribute("bc_id_3") != "") {
					bc_id_3 = Integer.parseInt((String)session.getAttribute("bc_id_3"));
				}
				if(session.getAttribute("sc_id_3") != null && session.getAttribute("sc_id_3") != "") {
					sc_id_3 = Integer.parseInt((String)session.getAttribute("sc_id_3"));
				}
				String shop_name = (String)session.getAttribute("shop_name");
				String descr = (String)session.getAttribute("descr");
				String detail = (String)session.getAttribute("detail");
				String jan_code = (String)session.getAttribute("jan_code");
				String branch_no = (String)session.getAttribute("branch_no");
				String itemization = (String)session.getAttribute("itemization");
				String ref_type = (String)session.getAttribute("ref_type");
				int retail_price = 0;
				if(session.getAttribute("retail_price") != null && session.getAttribute("retail_price") != "") {
					retail_price = Integer.parseInt((String)session.getAttribute("retail_price"));
				}
				int wholesale = Integer.parseInt((String)(session.getAttribute("wholesale")));
				int set_quantity = Integer.parseInt((String)(session.getAttribute("set_quantity")));
				int tax_rate_class = Integer.parseInt((String)(session.getAttribute("tax_rate_class")));
				String shipping_term = (String)session.getAttribute("shipping_term");
				String image_1 = (String)session.getAttribute("image_1");
				String image_2 = (String)session.getAttribute("image_2");
				String image_3 = (String)session.getAttribute("image_3");
				String image_4 = (String)session.getAttribute("image_4");
				String image_5 = (String)session.getAttribute("image_5");
				String image_6 = (String)session.getAttribute("image_6");
				String image_7 = (String)session.getAttribute("image_7");
				String image_8 = (String)session.getAttribute("image_8");
				String image_9 = (String)session.getAttribute("image_9");
				String image_10 = (String)session.getAttribute("image_10");
				char[] image_permission = ((String)session.getAttribute("image_permission")).toCharArray();
				char[] sell_permission = ((String)session.getAttribute("sell_permission")).toCharArray();
				char[] auction_permission = ((String)session.getAttribute("auction_permission")).toCharArray();
				char[] direct_permission = ((String)session.getAttribute("direct_permission")).toCharArray();
				char[] out_of_stock = ((String)session.getAttribute("out_of_stock")).toCharArray();
				
				ProductDao dao= new ProductDao();
				dao.update(old_pro_id, pro_id, pi_id, sp_id, sc_id_1, sc_id_2, sc_id_3, null, 0, old_pi_id, pi_name, 0, 0, 0, shop_name, descr, detail, jan_code, branch_no, itemization, ref_type, retail_price, wholesale, set_quantity, tax_rate_class, shipping_term, image_1, image_2, image_3, image_4, image_5, image_6, image_7, image_8, image_9, image_10, image_permission[0], sell_permission[0], auction_permission[0], direct_permission[0], out_of_stock[0]);
				forward="/jsp/productComp.jsp?submit=変更";
				
			}
			
			if(request.getParameter("submit").equals("削除")){
				
				String pro_id = (String)session.getAttribute("pro_id");
				String pi_id = (String)session.getAttribute("pi_id");
				String images_id = (String)session.getAttribute("images_id");
				
				ProductDao dao= new ProductDao();
				dao.delete(pro_id,pi_id,images_id);
				forward="/jsp/productComp.jsp?submit=削除";
				
			}
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}
}
