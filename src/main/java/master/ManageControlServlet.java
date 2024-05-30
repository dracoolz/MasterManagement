package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManageControlServlet extends HttpServlet {

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
		switch(no) {
			case 3:
				rd = request.getRequestDispatcher("./list?no=1");
				break;
			case 4:
				rd = request.getRequestDispatcher("./list?no=2");
				break;
		}
		
		
		rd.forward(request, response);
	}
}
