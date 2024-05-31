package order
;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class OrderAddServlet extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/order_add.jsp");
	    rd.forward(request, response);
	    
	    doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字コードの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset= UTF-8");

		HttpSession session = request.getSession(true);

		if (request.getParameter("submit").equals("登録")) {
			
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			int kakaku = Integer.parseInt((String) request.getParameter("kakaku"));
			session.setAttribute("id", id);
			session.setAttribute("name", name);
			session.setAttribute("kakaku", kakaku);

			RequestDispatcher rd = request.getRequestDispatcher("/jsp/kakunin.jsp");
			rd.forward(request, response);