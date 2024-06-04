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
import dao.CustomerDao;
import dao.ProductDao;

public class OrderAddServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//mojicode
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html;charset=UTF-8");
	
	//session
	HttpSession session = req.getSession();
	
	//get_parameter
	String customerFlag = req.getParameter("customerFlag");
	String productFlag = req.getParameter("productFlag");
	String customerId = req.getParameter("customerId");
	String errMsg = (String) req.getAttribute("errMsg");
	
	@SuppressWarnings("unchecked")
	ArrayList<OrderSlipBean> addSlip = (ArrayList<OrderSlipBean>) req.getAttribute("addSlip");
	
	//何も追加されていなかったら
	if(addSlip == null) {
	}
	
	//jump_url
	String url = "/jsp/orderAdd.jsp";
	
	//顧客関係処理
	if(("削除").equals(customerFlag)) {
		//sessionの取引先IDの値を消す
		session.setAttribute("customerName", null);
		
	}else if(("追加").equals(customerFlag)) {
		ErrCheck errChecker = new ErrCheck();
		boolean result = errChecker.IsEnteredCheck(customerId);
		
		if(!result) {
			errMsg = errChecker.getE017();
			req.setAttribute("errMsg", errMsg);
			session.setAttribute("customerName", null);
		}else {
			CustomerDao cusDao = new CustomerDao();
			String customerName = cusDao.selectCustomerId(Integer.parseInt(customerId));
			session.setAttribute("customerName", customerName);
		}
	}
	
	//商品関係処理
	if(("削除").equals(productFlag)) {
		//productListから商品情報を削除 productId
		String delProId = req.getParameter("delProId");
		int i = 0;
		for(OrderSlipBean item:addSlip) {
			//削除する商品だったら
			if(item.getProductId().equals(delProId)) {
				addSlip.remove(i);
			}
			i++;
		}
		
	}else if(("追加").equals(productFlag)) {
		//productListに商品情報を追加
		ProductDao proDao = new ProductDao();
		
	}
	
	
	session.setAttribute("productList",addSlip);
	
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}