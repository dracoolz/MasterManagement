package order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderSlipViewBean;
import dao.ListLogDao;
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
	String url = "/jsp/order_cancel.jsp";
	//get request parameter from OrderListServlet
	//int orderId = Integer.parseInt(req.getParameter("orderId"));
	//String orderDate = req.getParameter("orderDate");
	//String customerName = req.getParameter("customerName");
	
	int orderId = 2;
	String customerName = "馬場";
	String orderDate = "2024-01-23";
	
	//初回キャンセルか追加キャンセルか判定
	//boolean result = true;
	boolean isFirst = true;
	ListLogDao dao1 = new ListLogDao();
	if(dao1.checkListLogExists(orderId) > 0) {
		isFirst=false;
	}
			
	//初回キャンセル時
	if(isFirst) {
		// 受注詳細からデータ取得
		OrderSlipDao dao2 = new OrderSlipDao();
		ArrayList<OrderSlipViewBean> slips = dao2.selectSlipForCancelAndRefund(orderId);
		System.out.println(slips.get(0).getProductName());
		//set
		session.setAttribute("orderId",orderId);
		session.setAttribute("customerName", customerName);
		session.setAttribute("orderDate", orderDate);
		session.setAttribute("orderSlipViewList", slips);
		
	//追加キャンセル時　受注内容変更のキャンセル変更へ
	}else {
		System.out.println("not first");
		//url = "orderChange";
		//setAttribute
		req.setAttribute("orderId",orderId);
		req.setAttribute("customerName", customerName);
		req.setAttribute("orderDate", orderDate);
	}
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}