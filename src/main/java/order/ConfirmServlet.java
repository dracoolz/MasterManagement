package order;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//mojicode
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html;charset=UTF-8");
	
	//get
	String code = req.getParameter("code");
	String[] cancelQtyList = req.getParameterValues("cancelQty");
	String[] productIdList = req.getParameterValues("productId");
	//int[] orderQty = Integer.parseInt(req.getParameterValues("orderQty"));
	
	
	for(String qty:cancelQtyList) {
		System.out.println(qty);
	}
	for(String id:productIdList) {
		System.out.println(id);
	}
	
	//jumpurl
	String url = "/jsp/confirm.jsp";
	
	//エラーチェック
	
	//全部キャンセル from order_cancel.jsp
	if(("newFullCancel").equals(code)) {
	}
	
	//キャンセル　from order_cancel.jsp
	if(("newCancel").equals(code)) {
		
	}
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}