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
	String errMsg = (String) req.getAttribute("errMsg");
	
	int orderId = 2;
	String customerName = "馬場";
	String orderDate = "2024-01-23";
	
	//初回キャンセルか追加キャンセルか判定
	boolean isFirst = true;
	ListLogDao dao1 = new ListLogDao();
	if(dao1.checkListLogExists(orderId) > 0) {
		isFirst=false;
	}
			
	//初回キャンセル時
	if(isFirst) {
		//confirm.jspの戻るボタンで帰ってきた場合
		//毎回DBに接続し入力中のキャンセル数上書を防ぐ
		@SuppressWarnings("unchecked")
		ArrayList<OrderSlipBean> slips = (ArrayList<OrderSlipBean>) session.getAttribute("orderSlip");
		if(slips == null) {
			// 受注詳細からデータ取得
			OrderSlipDao dao2 = new OrderSlipDao();
			slips = dao2.selectSlipForCancelAndRefund(orderId);
			//set
			session.setAttribute("orderId",orderId);
			session.setAttribute("customerName", customerName);
			session.setAttribute("orderDate", orderDate);
			session.setAttribute("orderSlip", slips);
		}
		req.setAttribute("errMsg", errMsg);
		
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