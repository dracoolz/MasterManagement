package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String forward=null;
		
		if(request.getParameter("type").equals("user")) {
			
			if(request.getParameter("submit").equals("登録")){

				forward="/jsp/userMod.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				forward="/jsp/userMod.jsp?submit=変更";

			 }

			if(request.getParameter("submit").equals("削除")){

				forward="/jsp/userMod.jsp?submit=削除";

			 }

			if(request.getParameter("submit").equals("戻る")){
				forward="Master/master";
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

			if(request.getParameter("submit").equals("戻る")){
				forward="Master/master";
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
				forward="Master/master";
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		
	}
	
}
