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
	String cancelComment = (String) session.getAttribute("cancelComment");
	
	//jump url
	String url = "";
	
	
	//追加処理
	//変更処理
	
	//キャンセル処理
	if(("newCancel").equals(code)) {
		@SuppressWarnings("unchecked")
		ArrayList<OrderSlipBean> cancelList = (ArrayList<OrderSlipBean>) session.getAttribute("cancelList");
		
		//order_listの更新
		OrderListDao orderListDao = new OrderListDao();
		orderListDao.updateCancelData(orderId,cancelComment);
		//order_slipの更新
		OrderSlipDao orderSlipDao = new OrderSlipDao();
		orderSlipDao.updateCancelQty(cancelList);
		
		//order_slipの取得
		//list_logの追加 二次開発
		//slip_logの追加 二次開発
		
		//set
		req.setAttribute("msg", "キャンセル処理完了しました");
		url = "orderList";
		
		//session reset
		session.setAttribute("orderSlip", null);
		session.setAttribute("cancelList", null);
		session.setAttribute("cancelComment", null);
		session.setAttribute("orderId", null);
		session.setAttribute("customerName", null);
		session.setAttribute("orderDate", null);
		
		
	//返品処理
	}else if(("newRefund").equals(code)){
		
	}
	
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}