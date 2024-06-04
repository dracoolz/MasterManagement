package order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderSlipBean;
import dao.OrderListDao;
import dao.OrderSlipDao;

public class OrderCancelServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//mojicode
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html;charset=UTF-8");
	
	//session
	HttpSession session = req.getSession();
		
	//url
	String url = "/jsp/orderCancel.jsp";
	
	//get request parameter from OrderListServlet
	String errMsg = (String) req.getAttribute("errMsg");

	@SuppressWarnings("unchecked")
	ArrayList<OrderSlipBean> cancelSlip = (ArrayList<OrderSlipBean>) session.getAttribute("cancelSlip");
	//DBから1回も取得していなかったら
	if(cancelSlip == null) {
		int orderId = Integer.parseInt(req.getParameter("orderId"));
		String orderDate = req.getParameter("orderDate");
		String customerName = req.getParameter("customerName");
		// 受注詳細からデータ取得
		OrderSlipDao dao2 = new OrderSlipDao();
		cancelSlip = dao2.selectSlipForCancelAndRefund(orderId);
			
		//受注一覧からキャンセル理由取得
		OrderListDao dao3 = new OrderListDao();
		String cancelComment = dao3.selectCancelComment(orderId);
			
		//set
		session.setAttribute("orderId",orderId);
		session.setAttribute("customerName", customerName);
		session.setAttribute("orderDate", orderDate);
		session.setAttribute("cancelSlip", cancelSlip);
		session.setAttribute("cancelComment", cancelComment);
	}
	req.setAttribute("errMsg", errMsg);
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}