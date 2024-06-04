package master;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.SmallCategoryBean;
import bean.UserBean;
import dao.BigCategoryDao;
import dao.SmallCategoryDao;
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
				session.setAttribute("name",bean.getEmp_name());
				session.setAttribute("furigana", bean.getFurigana());
				session.setAttribute("email", bean.getEmp_email());
				session.setAttribute("role", bean.getRole());
				
				forward="/jsp/userConf.jsp?submit=削除";
			 } else if(request.getParameter("submit").equals("検索")){
				
				UserDao dao = new UserDao();
				ArrayList<UserBean> list = new ArrayList<UserBean>();
				
				list = dao.selectPart(request.getParameter("searchWord"));
				request.setAttribute("list", list);
				
				forward="/jsp/user.jsp";
			 }else if(request.getParameter("submit").equals("戻る")){
				forward="/jsp/userMod.jsp?submit=登録";
			}
	    }
		
		
		
		if(request.getParameter("type").equals("category")) {
			if(request.getParameter("submit").equals("登録")){

				forward="/jsp/categoryMod.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				forward="/jsp/categoryMod.jsp?submit=変更";

			 }

			if(request.getParameter("submit").equals("削除")){

				forward="/jsp/categoryMod.jsp?submit=削除";

			 }
			
			if(request.getParameter("submit").equals("検索")){
				
				SmallCategoryDao scdao = new SmallCategoryDao();
				BigCategoryDao bcdao = new BigCategoryDao();
				SmallCategoryBean scbean = new SmallCategoryBean();
				ArrayList<SmallCategoryBean> bclist = new ArrayList<SmallCategoryBean>();
				
				if(request.getParameter("sc")==null) {
					bclist = bcdao.select(Integer.parseInt(request.getParameter("bc")));
					request.setAttribute("bclist", bclist);
				}else {
					scbean = scdao.selectSc(Integer.parseInt(request.getParameter("sc")));
					request.setAttribute("scbean", scbean);
				}
				
				forward="/jsp/category.jsp";

			 }

			if(request.getParameter("submit").equals("戻る")){
				forward="./master";
			}
		}
		
		if(request.getParameter("type").equals("product")) {
			if(request.getParameter("submit").equals("登録")){

				forward="/jsp/productMod.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				forward="/jsp/productMod.jsp?submit=変更";

			 }

			if(request.getParameter("submit").equals("削除")){

				forward="/jsp/productMod.jsp?submit=削除";

			 }

			if(request.getParameter("submit").equals("戻る")){
				forward="./master";
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		
	}
	
}
