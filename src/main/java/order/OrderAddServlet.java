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
	//session.setAttribute("addSlip", null);
	
	//get_parameter
	String customerFlag = req.getParameter("customerFlag");
	String productFlag = req.getParameter("productFlag");
	String selCustomerId = req.getParameter("selCustomerId");
	String selProductId = req.getParameter("selProductId");
	String delProductId = req.getParameter("delProductId");
	String[] orderQtyList = req.getParameterValues("orderQty");
	//String errMsg = (String) req.getAttribute("errMsg");
	//req.setAttribute("errMsg", errMsg);
	String productErrMsg = (String)req.getAttribute("productErrMsg");
	req.setAttribute("productErrMsg", productErrMsg);
	String customerErrMsg = (String)req.getAttribute("customerErrMsg");
	req.setAttribute("customerErrMsg", customerErrMsg);
	
	String message = (String) req.getAttribute("message");
	req.setAttribute("message", message);
	//String customerErrMsg = req.getParameter("customerErrMsg");
	//String productErrMsg = (String) req.getAttribute("productErrMsg");
	
	@SuppressWarnings("unchecked")
	ArrayList<OrderSlipBean> addSlip = (ArrayList<OrderSlipBean>) session.getAttribute("addSlip");
	
	//初期化 何も追加されていなかったら
	if(addSlip == null) {
		addSlip = new ArrayList<OrderSlipBean>();
		session.setAttribute("addSlip", addSlip);
	}
	
	
	//注文数の引継ぎ 
	if(addSlip.size() > 0 && orderQtyList != null) {
		int n=0;
		for(OrderSlipBean item:addSlip) {
			item.setOrderQty(Integer.parseInt(orderQtyList[n]));
			n++;
		}
		session.setAttribute("addSlip", addSlip);
	}
	
	
	//jump_url
	String url = "/jsp/orderAdd.jsp";
	
	ErrCheck errChecker = new ErrCheck();
	boolean result = true;
	
	//顧客関係処理
	if(("削除").equals(customerFlag)) {
		//sessionの取引先IDの値を消す
		session.setAttribute("addCustomerId",null);
		session.setAttribute("addCustomerName", null);
		
	}else if(("追加").equals(customerFlag)) {
		
		result = errChecker.IsEnteredCheck(selCustomerId);
		
		//入力漏れあり
		if(!result) {
			req.setAttribute("customerErrMsg", errChecker.getE019());
			session.setAttribute("addCustomerId",null);
			session.setAttribute("addCustomerName", null);
		}else {
			
			result = errChecker.numberCheck(selCustomerId);
			//半角数値じゃなかったら
			if(!result) {
				req.setAttribute("customerErrMsg", errChecker.getE023());
				session.setAttribute("addCustomerId",null);
				session.setAttribute("addCustomerName", null);
			//半角数値だったら
			}else {
				CustomerDao cusDao = new CustomerDao();
				String customerName = cusDao.selectCustomerName(Integer.parseInt(selCustomerId));
				//顧客が見つからなかった
				if(customerName == null) {
					req.setAttribute("customerErrMsg", errChecker.getE017());
					session.setAttribute("addCustomerId",null);
					session.setAttribute("addCustomerName", null);
					
				//顧客が見つかった
				}else {
					session.setAttribute("addCustomerId", selCustomerId);
					session.setAttribute("addCustomerName", customerName);
				}
			}
		}
	}
	
	//商品関係処理
	if(("削除").equals(productFlag)) {
		//productListから商品情報を削除 productId
		//int i = 0;
		for(int i=0; i<addSlip.size(); i++) {
			//削除する商品だったら
			if(addSlip.get(i).getProductId().equals(delProductId)) {
				addSlip.remove(i);
			}
		}
		
	}else if(("追加").equals(productFlag)) {
		
		result = errChecker.IsEnteredCheck(selProductId);
		//入力漏れあり
		if(!result) {
			req.setAttribute("productErrMsg", errChecker.getE020());
		//入力漏れなし
		}else {
			ProductDao proDao = new ProductDao();
			String productName = proDao.selectProductName(selProductId);
			//商品が見つからなかったら
			if(productName == null) {
				req.setAttribute("productErrMsg", errChecker.getE017());
			//商品が見つかったら
			}else {
				result = errChecker.productDuplicateCheck(selProductId, addSlip);
				//同じ商品が追加されていたら
				if(!result) {
					req.setAttribute("productErrMsg", errChecker.getE021());
				//同じ商品はなかったら　allOK!
				}else {
					//addSlip sessionに追加
					OrderSlipBean bean = new OrderSlipBean();
					bean.setProductId(selProductId);
					bean.setProductName(productName);
					bean.setOrderQty(1);
					addSlip.add(bean);
					
					//金額集計は最後に追加した商品が反映されないから
					//ConfirmServletで
					
					session.setAttribute("addSlip",addSlip);
				}
			}
		}
	}
	
	
	
	
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}