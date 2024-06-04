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
				forward="/jsp/userComp.jsp?submit=登録";
			}
			if(request.getParameter("submit").equals("変更")){
				int id = (int)session.getAttribute("id");
				String name = (String)session.getAttribute("name");
				String furigana = (String)session.getAttribute("furigana");
				String email = (String)session.getAttribute("email");
				int kubun = (int)session.getAttribute("kubun");
				
				UserDao dao= new UserDao();
				dao.update(id,name,furigana,email,kubun);
				forward="/jsp/userComp.jsp?submit=変更";
			}
			if(request.getParameter("submit").equals("削除")){
				int id = (int)session.getAttribute("id");
				
				UserDao dao= new UserDao();
				dao.delete(id);
				forward="/jsp/userComp.jsp?submit=削除";
			}
		}
		
		
		if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("bc")) {
			request.setAttribute("categoryType", "bc");

			if(request.getParameter("submit").equals("登録")){

				int id = Integer.parseInt(request.getParameter("bc_id"));
				String name = request.getParameter("bc_category");

				BigCategoryDao dao= new BigCategoryDao();
				dao.insert(id,name);
				forward="/jsp/categoryComp.jsp?submit=登録";

			}

			if(request.getParameter("submit").equals("変更")){

				int id = Integer.parseInt(request.getParameter("bc_id"));
				int new_id = Integer.parseInt(request.getParameter("new_bc_id"));
				String name = request.getParameter("bc_category");

				BigCategoryDao dao= new BigCategoryDao();
				dao.update(id,new_id,name);
				forward="/jsp/categoryComp.jsp?submit=変更";

			}

			if(request.getParameter("submit").equals("削除")){

				int id = Integer.parseInt(request.getParameter("bc_id"));

				BigCategoryDao dao= new BigCategoryDao();
				dao.delete(id);
				forward="/jsp/categoryComp.jsp?submit=削除";

			}

		}

		if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("sc")) {
			request.setAttribute("categoryType", "sc");

			if(request.getParameter("submit").equals("登録")){

				int sc_id = Integer.parseInt(request.getParameter("sc_id"));
				int bc_id = Integer.parseInt(request.getParameter("bc_id"));
				String name = request.getParameter("sc_category");

				SmallCategoryDao dao= new SmallCategoryDao();
				dao.insert(sc_id,bc_id,name);
				forward="/jsp/categoryComp.jsp?submit=登録";

			}

			if(request.getParameter("submit").equals("変更")){

				int sc_id = Integer.parseInt(request.getParameter("sc_id"));
				int bc_id = Integer.parseInt(request.getParameter("bc_id"));
				int new_sc_id = Integer.parseInt(request.getParameter("new_sc_id"));
				String name = request.getParameter("sc_category");

				SmallCategoryDao dao= new SmallCategoryDao();
				dao.update(sc_id,bc_id,new_sc_id,name);
				forward="/jsp/categoryComp.jsp?submit=変更";

			}

			if(request.getParameter("submit").equals("削除")){

				int id = Integer.parseInt(request.getParameter("sc_id"));

				SmallCategoryDao dao= new SmallCategoryDao();
				dao.delete(id);
				forward="/jsp/categoryComp.jsp?submit=削除";

			}

		}


		if(request.getParameter("type").equals("product")) {


			if(request.getParameter("submit").equals("登録")){

				String pro_id = request.getParameter("pro_id");
				int sp_id = Integer.parseInt(request.getParameter("sp_id"));
				String pi_id = request.getParameter("pi_id");
				String pi_name = request.getParameter("pi_name");
				int bc_id_1 = Integer.parseInt(request.getParameter("bc_id_1"));
				int sc_id_1 = Integer.parseInt(request.getParameter("sc_id_1"));
				int bc_id_2 = Integer.parseInt(request.getParameter("bc_id_2"));
				int sc_id_2 = Integer.parseInt(request.getParameter("sc_id_2"));
				int bc_id_3 = Integer.parseInt(request.getParameter("bc_id_3"));
				int sc_id_3 = Integer.parseInt(request.getParameter("sc_id_3"));
				String shop_name = request.getParameter("shop_name");
				String descr = request.getParameter("descr");
				String detail = request.getParameter("detail");
				String jan_code = request.getParameter("jan_code");
				int branch_no = Integer.parseInt(request.getParameter("branch_no"));
				String itemization = request.getParameter("itemization");
				String ref_type = request.getParameter("ref_type");
				int retail_price = Integer.parseInt(request.getParameter("retail_price"));
				int wholesale = Integer.parseInt(request.getParameter("wholesale"));
				int set_quantity = Integer.parseInt(request.getParameter("set_quantity"));
				int tax_rate_class = Integer.parseInt(request.getParameter("tax_rate_class"));
				String shipping_term = request.getParameter("shipping_term");
				String image_1 = request.getParameter("image_1");
				String image_2 = request.getParameter("image_2");
				String image_3 = request.getParameter("image_3");
				String image_4 = request.getParameter("image_4");
				String image_5 = request.getParameter("image_5");
				String image_6 = request.getParameter("image_6");
				String image_7 = request.getParameter("image_7");
				String image_8 = request.getParameter("image_8");
				String image_9 = request.getParameter("image_9");
				String image_10 = request.getParameter("image_10");
				char[] image_permission = request.getParameter("image_permission").toCharArray();
				char[] sell_permission = request.getParameter("sell_permission").toCharArray();
				char[] auction_permission = request.getParameter("auction_permission").toCharArray();
				char[] direct_permission = request.getParameter("direct_permission").toCharArray();
				char[] out_of_stock = request.getParameter("out_of_stock").toCharArray();


				ProductDao dao= new ProductDao();
				dao.insert(pro_id,sp_id,pi_id,pi_name,bc_id_1,sc_id_1,bc_id_2,sc_id_2,bc_id_3,sc_id_3,shop_name,descr,detail,jan_code,branch_no,itemization,ref_type,retail_price,wholesale,set_quantity,tax_rate_class,shipping_term,image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10,image_permission[0],sell_permission[0],auction_permission[0],direct_permission[0],out_of_stock[0]);
				forward="/jsp/productComp.jsp?submit=登録";

			}

			if(request.getParameter("submit").equals("変更")){

				String pro_id = request.getParameter("pro_id");
				String new_pro_id = request.getParameter("new_pro_id");
				int sp_id = Integer.parseInt(request.getParameter("sp_id"));
				int new_sp_id = Integer.parseInt(request.getParameter("new_sp_id"));
				String pi_id = request.getParameter("pi_id");
				String new_pi_id = request.getParameter("new_pi_id");
				String pi_name = request.getParameter("pi_name");
				int sc_id_1 = Integer.parseInt(request.getParameter("sc_id_1"));
				int sc_id_2 = Integer.parseInt(request.getParameter("sc_id_2"));
				int sc_id_3 = Integer.parseInt(request.getParameter("sc_id_3"));
				int new_sc_id_1 = Integer.parseInt(request.getParameter("new_sc_id_1"));
				int new_sc_id_2 = Integer.parseInt(request.getParameter("new_sc_id_2"));
				int new_sc_id_3 = Integer.parseInt(request.getParameter("new_sc_id_3"));
				String shop_name = request.getParameter("shop_name");
				String descr = request.getParameter("descr");
				String detail = request.getParameter("detail");
				String jan_code = request.getParameter("jan_code");
				int branch_no = Integer.parseInt(request.getParameter("branch_no"));
				String itemization = request.getParameter("itemization");
				String ref_type = request.getParameter("ref_type");
				int retail_price = Integer.parseInt(request.getParameter("retail_price"));
				int wholesale = Integer.parseInt(request.getParameter("wholesale"));
				int set_quantity = Integer.parseInt(request.getParameter("set_quantity"));
				int tax_rate_class = Integer.parseInt(request.getParameter("tax_rate_class"));
				String shipping_term = request.getParameter("shipping_term");
				String image_1 = request.getParameter("image_1");
				String image_2 = request.getParameter("image_2");
				String image_3 = request.getParameter("image_3");
				String image_4 = request.getParameter("image_4");
				String image_5 = request.getParameter("image_5");
				String image_6 = request.getParameter("image_6");
				String image_7 = request.getParameter("image_7");
				String image_8 = request.getParameter("image_8");
				String image_9 = request.getParameter("image_9");
				String image_10 = request.getParameter("image_10");
				char[] image_permission = request.getParameter("image_permission").toCharArray();
				char[] sell_permission = request.getParameter("sell_permission").toCharArray();
				char[] auction_permission = request.getParameter("auction_permission").toCharArray();
				char[] direct_permission = request.getParameter("direct_permission").toCharArray();
				char[] out_of_stock = request.getParameter("out_of_stock").toCharArray();


				ProductDao dao= new ProductDao();
				dao.update(pro_id,pi_id,sp_id,sc_id_1,sc_id_2,sc_id_3,new_pro_id,new_sp_id,new_pi_id,pi_name,new_sc_id_1,new_sc_id_2,new_sc_id_3,shop_name,descr,detail,jan_code,branch_no,itemization,ref_type,retail_price,wholesale,set_quantity,tax_rate_class,shipping_term,image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10,image_permission[0],sell_permission[0],auction_permission[0],direct_permission[0],out_of_stock[0]);
				forward="/jsp/productComp.jsp?submit=変更";

			}


			if(request.getParameter("submit").equals("削除")){

				String pro_id = request.getParameter("pro_id");
				String pi_id = request.getParameter("pi_id");

				ProductDao dao= new ProductDao();
				dao.delete(pro_id,pi_id);
				forward="/jsp/productComp.jsp?submit=削除";

			}


		}

		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);

	}

}
