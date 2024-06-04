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

public class DBUpdateServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//mojicode
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html;charset=UTF-8");
	
	//session
	HttpSession session = req.getSession();
	
	//get
	String code = (String)session.getAttribute("code");
	int orderId = (int) session.getAttribute("orderId");
	
	//jump url
	String url = "";
	
	
	//追加処理
	//変更処理
	
	//キャンセル処理
	if(("cancel").equals(code)) {
		@SuppressWarnings("unchecked")
		ArrayList<OrderSlipBean> cancelSlip = (ArrayList<OrderSlipBean>) session.getAttribute("cancelSlip");
		String cancelComment = (String) session.getAttribute("cancelComment");
		
		//order_listの更新
		OrderListDao orderListDao = new OrderListDao();
		orderListDao.updateCancelData(orderId,cancelComment);
		//order_slipの更新
		OrderSlipDao cancelSlipDao = new OrderSlipDao();
		cancelSlipDao.updateCancelQty(cancelSlip);
		
		
		//order_slipの取得
		//list_logの追加 二次開発
		//slip_logの追加 二次開発
		
		//set
		req.setAttribute("msssage", "キャンセル処理完了しました");
		url = "orderList";
		req.setAttribute("no", "6");
		//req.setAttribute("status","cancel_only");
		
		//session reset
		session.setAttribute("code", null);
		session.setAttribute("cancelSlip", null);
		session.setAttribute("cancelComment", null);
		session.setAttribute("orderId", null);
		session.setAttribute("customerName", null);
		session.setAttribute("orderDate", null);
		
		
	//返品処理
	}else if(("refund").equals(code)){
		@SuppressWarnings("unchecked")
		ArrayList<OrderSlipBean> refundSlip = (ArrayList<OrderSlipBean>) session.getAttribute("refundSlip");
		String refundComment = (String) session.getAttribute("refundComment");
		
		//order_listの更新
		OrderListDao orderListDao = new OrderListDao();
		orderListDao.updateRefundData(orderId,refundComment);
		//order_slipの更新
		OrderSlipDao cancelSlipDao = new OrderSlipDao();
		cancelSlipDao.updateRefundQty(refundSlip);
		
		
		//order_slipの取得
		//list_logの追加 二次開発
		//slip_logの追加 二次開発
		
		//set
		req.setAttribute("message", "キャンセル処理完了しました");
		url = "orderList";
		
		//session reset
		session.setAttribute("code", null);
		session.setAttribute("refundSlip", null);
		session.setAttribute("refundComment", null);
		session.setAttribute("orderId", null);
		session.setAttribute("customerName", null);
		session.setAttribute("orderDate", null);
	}
	
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}