package master;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
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
			
			if(str.equals("登録")){

				forward="/jsp/userMod.jsp?submit=登録";

			 }
			
			if(str.equals("変更")){

				forward="/jsp/userMod.jsp?submit=変更";

			 }

			if(str.equals("削除")){

				forward="/jsp/userConf.jsp?submit=削除";

			 }
			
			if(str.equals("検索")){
				
				UserDao dao = new UserDao();
				ArrayList<UserBean> list = new ArrayList<UserBean>();
				
				list = dao.selectPart(request.getParameter("searchWord"));
				request.setAttribute("list", list);
				
				forward="/jsp/user.jsp";
			 }

			if(str.equals("戻る")){
				if(mod.equals("登録")) {
					forward="/jsp/userMod.jsp?submit=登録";
				}else if(mod.equals("変更")) {
					forward="/jsp/userMod.jsp?submit=変更";
				}else if(mod.equals("さくじょ")) {
					forward="/jsp/userMod.jsp?submit=削除";
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
				}else if(mod.equals("さくじょ")) {
					forward="/jsp/categoryMod.jsp?submit=削除";
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
					}else if(mod.equals("さくじょ")) {
						forward="/jsp/categoryMod.jsp?submit=削除";
					}
				}
			}
		}
				
		if(request.getParameter("type").equals("product")) {
			if(str.equals("登録")){

				forward="/jsp/productMod.jsp?submit=登録";

			 }
			
			if(str.equals("変更")){

				forward="/jsp/productMod.jsp?submit=変更";

			 }

			if(str.equals("削除")){

				forward="/jsp/productConf.jsp?submit=削除";

			 }

			if(str.equals("戻る")){
				if(mod.equals("登録")) {
					forward="/jsp/productMod.jsp?submit=登録";
				}else if(mod.equals("変更")) {
					forward="/jsp/productMod.jsp?submit=変更";
				}else if(mod.equals("さくじょ")) {
					forward="/jsp/productMod.jsp?submit=削除";
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		
	}
	
	}
}
