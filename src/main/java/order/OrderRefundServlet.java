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
import dao.OrderListDao;
import dao.OrderSlipDao;

public class OrderRefundServlet extends HttpServlet {
	
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
	String url = "/jsp/order_refund.jsp";
	
	//get request parameter from OrderListServlet
	//int orderId = Integer.parseInt(req.getParameter("orderId"));
	//String orderDate = req.getParameter("orderDate");
	//String customerName = req.getParameter("customerName");
	String errMsg = (String) req.getAttribute("errMsg");
	
	int orderId = 2;
	String customerName = "馬場";
	String orderDate = "2024-01-23";
	
	//初回返品か追加返品か判定
	boolean isFirst = true;
	ListLogDao dao1 = new ListLogDao();
	if(dao1.checkListLogExists(orderId) > 0) {
		isFirst=false;
	}
			
	//初回返品時
	if(isFirst) {
		//confirm.jspの戻るボタンで帰ってきた場合
		//毎回DBに接続し入力中の返品数上書を防ぐ
		@SuppressWarnings("unchecked")
		ArrayList<OrderSlipBean> refundSlip = (ArrayList<OrderSlipBean>) session.getAttribute("refundSlip");
		//DBから1回も取得していなかったら
		if(refundSlip == null) {
			// 受注詳細からデータ取得
			OrderSlipDao dao2 = new OrderSlipDao();
			refundSlip = dao2.selectSlipForCancelAndRefund(orderId);
			
			//受注一覧から返品理由取得
			OrderListDao dao3 = new OrderListDao();
			String refundComment = dao3.selectRefundComment(orderId);
			
			//set
			session.setAttribute("orderId",orderId);
			session.setAttribute("customerName", customerName);
			session.setAttribute("orderDate", orderDate);
			session.setAttribute("refundSlip", refundSlip);
			session.setAttribute("refundComment", refundComment);
		}
		req.setAttribute("errMsg", errMsg);
		
	//追加キャンセル時　受注内容変更のキャンセル変更へ
	}else {
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