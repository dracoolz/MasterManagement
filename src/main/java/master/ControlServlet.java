package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControlServlet extends HttpServlet {

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

				String name = request.getParameter("name");
				String furigana = request.getParameter("furigana");
				String email = request.getParameter("email");
				String pass = request.getParameter("pass");
				String kubun = (String)request.getParameter("kubun");
				
				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				String errmsg5=null;
				String errmsg6=null;
				String errmsg7=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(name,furigana,email,pass,kubun) && err.fullWidthCheck(furigana) && err.emailCheck(email)){
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("pass", pass);
					session.setAttribute("kubun", kubun);
					forward="/jsp/userConf.jsp?submit=登録";
				}else{
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("pass", pass);
					session.setAttribute("kubun", kubun);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					request.setAttribute("errmsg5", errmsg5);
					request.setAttribute("errmsg6", errmsg6);
					request.setAttribute("errmsg7", errmsg7);
					forward="/jsp/userMod.jsp?submit=登録";
				}

				forward="/jsp/userConf.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				String name = request.getParameter("name");
				String furigana = request.getParameter("furigana");
				String email = request.getParameter("email");
				String pass = request.getParameter("pass");
				String kubun = (String)request.getParameter("kubun");

				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				String errmsg5=null;
				String errmsg6=null;
				String errmsg7=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(name,furigana,email,pass,kubun) && err.fullWidthCheck(furigana) && err.emailCheck(email)){
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("pass", pass);
					session.setAttribute("kubun", kubun);
					forward="/jsp/userConf.jsp?submit=変更";
				}else{
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("pass", pass);
					session.setAttribute("kubun", kubun);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					request.setAttribute("errmsg5", errmsg5);
					request.setAttribute("errmsg6", errmsg6);
					request.setAttribute("errmsg7", errmsg7);
					forward="/jsp/userMod.jsp?submit=変更";
				}

				forward="/jsp/userConf.jsp?submit=変更";

			 }


			if(request.getParameter("submit").equals("戻る")){
				forward="/jsp/user";
			}
	    }
		
		
		
		if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("bc")) {
			
			
			if(request.getParameter("submit").equals("登録")){

				String id = request.getParameter("bc_id");
				String name = request.getParameter("bc_category");
				
				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(id,name) && err.idExistCheck(id)){
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					forward="/jsp/categoryConf.jsp?submit=登録";
				}else{
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=登録";
				}

				forward="/jsp/categoryConf.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				String id = request.getParameter("bc_id");
				String name = request.getParameter("bc_category");

				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(id,name) && err.idExistCheck(id)){
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					forward="/jsp/categoryConf.jsp?submit=変更";
				}else{
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=変更";
				}

				forward="/jsp/categoryConf.jsp?submit=変更";

			 }


			if(request.getParameter("submit").equals("戻る")){
				forward="/jsp/category";
			}
	    }
		
		
		if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("sc")) {
			
			
			if(request.getParameter("submit").equals("登録")){

				String sc_id = request.getParameter("sc_id");
				String bc_id = request.getParameter("bc_id");
				String name = request.getParameter("sc_category");
				
				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(sc_id,bc_id,name) && err.idExistCheck(sc_id)){
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					forward="/jsp/categoryConf.jsp?submit=登録";
				}else{
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=登録";
				}

				forward="/jsp/categoryConf.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				String sc_id = request.getParameter("sc_id");
				String bc_id = request.getParameter("bc_id");
				String name = request.getParameter("sc_category");

				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(sc_id,bc_id,name) && err.idExistCheck(sc_id)){
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					forward="/jsp/categoryConf.jsp?submit=変更";
				}else{
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=変更";
				}

				forward="/jsp/categoryConf.jsp?submit=変更";

			 }


			if(request.getParameter("submit").equals("戻る")){
				forward="/jsp/category";
			}
	    }
		
		
		if(request.getParameter("type").equals("product")) {
			
			if(request.getParameter("submit").equals("登録")){

				String pro_id = request.getParameter("pro_id");
				int sp_id = Integer.parseInt(request.getParameter("sp_id"));
				String pi_id = request.getParameter("pi_id");
				String pd_id = request.getParameter("pd_id");
				String images_id = request.getParameter("images_id");
				String itemization = request.getParameter("itemization");
				int wholesale = Integer.parseInt(request.getParameter("wholesale"));
				int set_quantity = Integer.parseInt(request.getParameter("set_quantity"));
				String pi_name = request.getParameter("pi_name");
				int category_id = Integer.parseInt(request.getParameter("category_id"));
				String jan_code = request.getParameter("jan_code");
				int branch_no = Integer.parseInt(request.getParameter("branch_no"));
				String ref_type = request.getParameter("ref_type");
				int retail_price = Integer.parseInt(request.getParameter("retail_price"));
				int tax_rate_class = Integer.parseInt(request.getParameter("tax_rate_class"));
				String shipping_term = request.getParameter("shipping_term");
				String image_permission = request.getParameter("image_permission");
				String sell_permission = request.getParameter("sell_permission");
				String auction_permission = request.getParameter("auction_permission");
				String direct_permission = request.getParameter("direct_permission");
				String out_of_stock = request.getParameter("out_of_stock");
				int sc_id = Integer.parseInt(request.getParameter("sc_id"));
				String bc_category_1 = request.getParameter("bc_category_1");
				String sc_category_1 = request.getParameter("sc_category_1");
				String bc_category_2 = request.getParameter("bc_category_2");
				String sc_category_2 = request.getParameter("sc_category_2");
				String bc_category_3 = request.getParameter("bc_category_3");
				String sc_category_3 = request.getParameter("sc_category_3");
				String descr = request.getParameter("descr");
				String detail = request.getParameter("detail");
				String images_1 = request.getParameter("images_1");
				String images_2 = request.getParameter("images_2");
				String images_3 = request.getParameter("images_3");
				String images_4 = request.getParameter("images_4");
				String images_5 = request.getParameter("images_5");
				String images_6 = request.getParameter("images_6");
				String images_7 = request.getParameter("images_7");
				String images_8 = request.getParameter("images_8");
				String images_9 = request.getParameter("images_9");
				String images_10 = request.getParameter("images_10");
				String shop_name = request.getParameter("shop_name");
				
				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(pro_id,sp_id,pi_id,pi_name,shop_name,ref_type,wholesale,set_quantity,tax_rate_class) && err.idNotExistCheck(pro_id) && err.numberCheck(pro_id,sp_id,pi_id,pi_name,shop_name,branch_no,ref_type,wholesale,set_quantity,tax_rate_class)){
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("category_id",category_id);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("images_1",images_1);
					session.setAttribute("images_2",images_2);
					session.setAttribute("shop_name",shop_name);
					forward="/jsp/productConf.jsp?submit=登録";
				}else{
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("category_id",category_id);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("images_1",images_1);
					session.setAttribute("images_2",images_2);
					session.setAttribute("shop_name",shop_name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					forward="/jsp/productMod.jsp?submit=登録";
				}

				forward="/jsp/productConf.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				String pro_id = request.getParameter("pro_id");
				int sp_id = Integer.parseInt(request.getParameter("sp_id"));
				String pi_id = request.getParameter("pi_id");
				String pd_id = request.getParameter("pd_id");
				String images_id = request.getParameter("images_id");
				String itemization = request.getParameter("itemization");
				int wholesale = Integer.parseInt(request.getParameter("wholesale"));
				int set_quantity = Integer.parseInt(request.getParameter("set_quantity"));
				String pi_name = request.getParameter("pi_name");
				int category_id = Integer.parseInt(request.getParameter("category_id"));
				String jan_code = request.getParameter("jan_code");
				int branch_no = Integer.parseInt(request.getParameter("branch_no"));
				String ref_type = request.getParameter("ref_type");
				int retail_price = Integer.parseInt(request.getParameter("retail_price"));
				int tax_rate_class = Integer.parseInt(request.getParameter("tax_rate_class"));
				String shipping_term = request.getParameter("shipping_term");
				String image_permission = request.getParameter("image_permission");
				String sell_permission = request.getParameter("sell_permission");
				String auction_permission = request.getParameter("auction_permission");
				String direct_permission = request.getParameter("direct_permission");
				String out_of_stock = request.getParameter("out_of_stock");
				int sc_id = Integer.parseInt(request.getParameter("sc_id"));
				String bc_category_1 = request.getParameter("bc_category_1");
				String sc_category_1 = request.getParameter("sc_category_1");
				String bc_category_2 = request.getParameter("bc_category_2");
				String sc_category_2 = request.getParameter("sc_category_2");
				String bc_category_3 = request.getParameter("bc_category_3");
				String sc_category_3 = request.getParameter("sc_category_3");
				String descr = request.getParameter("descr");
				String detail = request.getParameter("detail");
				String images_1 = request.getParameter("images_1");
				String images_2 = request.getParameter("images_2");
				String images_3 = request.getParameter("images_3");
				String images_4 = request.getParameter("images_4");
				String images_5 = request.getParameter("images_5");
				String images_6 = request.getParameter("images_6");
				String images_7 = request.getParameter("images_7");
				String images_8 = request.getParameter("images_8");
				String images_9 = request.getParameter("images_9");
				String images_10 = request.getParameter("images_10");
				String shop_name = request.getParameter("shop_name");
				
				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(pro_id,sp_id,pi_id,pi_name,shop_name,ref_type,wholesale,set_quantity,tax_rate_class) && err.idNotExistCheck(pro_id) && err.numberCheck(pro_id,sp_id,pi_id,pi_name,shop_name,branch_no,ref_type,wholesale,set_quantity,tax_rate_class)){
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("category_id",category_id);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("images_1",images_1);
					session.setAttribute("images_2",images_2);
					session.setAttribute("shop_name",shop_name);
					forward="/jsp/productConf.jsp?submit=変更";
				}else{
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("category_id",category_id);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("images_1",images_1);
					session.setAttribute("images_2",images_2);
					session.setAttribute("shop_name",shop_name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					forward="/jsp/productMod.jsp?submit=変更";
				}

				forward="/jsp/productConf.jsp?submit=変更";

			 }


			if(request.getParameter("submit").equals("戻る")){
				forward="/jsp/product";
			}
	    }
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		
	}
	
}
