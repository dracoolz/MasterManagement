package order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductBean;
import dao.CustomerDao;

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
	String pageFlag = req.getParameter("pageFlag");
	String customerId = req.getParameter("customerId");
	System.out.println("cus_id:"+customerId);
	
	//jump_url
	String url = "/jsp/order_add.jsp";
	
	//order_add.jsp's 削除ボタン
	if(("削除").equals(pageFlag)) {
		//sessionの取引先IDの値を消す
		session.setAttribute("customerName", null);
		
	//order_add.jsp's 追加ボタン
	}else if(("追加").equals(pageFlag)) {
		ErrCheck errChecker = new ErrCheck();
		boolean result = errChecker.IsEnteredCheck(customerId);
		
		String errMsg;
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
	
	
	ProductBean product1 = new ProductBean();
	product1.setPro_id("p-1");
	product1.setPi_name("A");
	ProductBean product2 = new ProductBean();
	product2.setPro_id("p-2");
	product2.setPi_name("B");
	
	ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
	productList.add(product1);
	productList.add(product2);
	
	session.setAttribute("productList",productList);
	
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}