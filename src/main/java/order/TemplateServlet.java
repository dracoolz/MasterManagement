package order;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TemplateServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//mojicode
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html;charset=UTF-8");
	
	//parameter default string?
	req.getParameter("");
	String str = req.getParameter("");
	int num = Integer.parseInt(req.getParameter(""));

	//attribute default object?
	req.getAttribute("");
	req.setAttribute("",);


	//session
	HttpSession session = req.getSession();
	session.getAttribute("");
	session.setAttribute("",);
	
	
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher("/jsp/userupdate.jsp");
	rd.forward(req, res);
	}
}