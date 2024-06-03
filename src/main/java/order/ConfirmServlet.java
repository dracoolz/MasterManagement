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

public class ConfirmServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	//mojicode
	req.setCharacterEncoding("UTF-8");
	res.setContentType("text/html;charset=UTF-8");
	
	//session
	HttpSession session = req.getSession();
	
	//session initialize
	session.setAttribute("cancelList", null);
	
	//get session para
	
	//get para
	String code = req.getParameter("code");
	String pageFlag = req.getParameter("pageFlag");
	
	//jumpurl
	String url = "/jsp/confirm.jsp";
	
	//キャンセル処理order_cancel.jspから
	if(("newCancel").equals(code)) {
		String[] orderSlipIdList = req.getParameterValues("orderSlipId");
		String[] productIdList = req.getParameterValues("productId");
		String[] productNameList = req.getParameterValues("productName");
		String[] cancelQtyList = req.getParameterValues("cancelQty");
		String[] orderQtyList = req.getParameterValues("orderQty");
		String cancelComment = req.getParameter("cancelComment");
		
		//戻るボタン用に現在キャンセル数を格納
		@SuppressWarnings("unchecked")
		ArrayList<OrderSlipBean> orderSlip = (ArrayList<OrderSlipBean>) session.getAttribute("orderSlip");
		int n=0;
		for(OrderSlipBean item:orderSlip) {
			item.setCancelQty(Integer.parseInt(cancelQtyList[n]));
			System.out.println("setCancelQty to orderSlip session"+item.getCancelQty());
			n++;
		}
		session.setAttribute("orderSlip", orderSlip);
		
		//すべてキャンセル
		if(("受注内容全てをキャンセルする").equals(pageFlag)) {
			@SuppressWarnings("unchecked")
			ArrayList<OrderSlipBean> cancelList = (ArrayList<OrderSlipBean>) session.getAttribute("orderSlip");
			for(OrderSlipBean cancelItem:cancelList) {
				cancelItem.setCancelQty(cancelItem.getOrderQty() - cancelItem.getRefundQty());
			}
			
			//session set
			session.setAttribute("cancelList", cancelList);
			session.setAttribute("cancelComment", cancelComment);
		}
		//部分キャンセル
		if(("確認").equals(pageFlag)) {
			ArrayList<OrderSlipBean> cancelList = new ArrayList<OrderSlipBean>();
			for(int i=0;i<orderSlipIdList.length;i++) {
				if(!cancelQtyList[i].equals("0")) {
					OrderSlipBean bean = new OrderSlipBean();
					bean.setOrderSlipId(Integer.parseInt(orderSlipIdList[i]));
					bean.setOrderId((int)session.getAttribute("orderId"));
					bean.setProductId(productIdList[i]);
					bean.setProductName(productNameList[i]);
					bean.setOrderQty(Integer.parseInt(orderQtyList[i]));
					bean.setCancelQty(Integer.parseInt(cancelQtyList[i]));
					cancelList.add(bean);
				}
			}
			
			//キャンセル商品一つ以上ある場合
			ErrCheck errChecker = new ErrCheck();
			if(errChecker.addProductCheck(cancelList)) {
				//session set
				session.setAttribute("cancelList", cancelList);
				
			//キャンセル商品が何もない　戻る　エラー
			}else {
				url="orderCancel";
				req.setAttribute("errMsg", errChecker.getE011());
			}
			
		}
		session.setAttribute("code", code);
		session.setAttribute("cancelComment", cancelComment);
		//session reset
		//session.setAttribute("orderSlip", null);
		
	}
	
	//jump
	RequestDispatcher rd = req.getRequestDispatcher(url);
	rd.forward(req, res);
	}
}