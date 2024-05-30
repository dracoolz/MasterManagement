package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterServlet extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

		//文字コードの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		RequestDispatcher rd = null;
		if(no == 0) {
			rd = request.getRequestDispatcher("/jsp/master.jsp");
		} else if(no == 1) {
			rd = request.getRequestDispatcher("/totalmenu");
		} else if(no == 2) {
			rd = request.getRequestDispatcher("/jsp/password.jsp?state=change");
		}
		rd.forward(request, response);
	}
}
