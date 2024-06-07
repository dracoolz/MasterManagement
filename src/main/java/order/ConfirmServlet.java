package order;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderSlipBean;
import dao.ProductDao;

public class ConfirmServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//mojicode
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");

		//session
		HttpSession session = req.getSession();

		//get para
		String code = req.getParameter("code");
		String pageFlag = req.getParameter("pageFlag");

		//jumpurl
		String url = "/jsp/confirm.jsp";

		ErrCheck errChecker = new ErrCheck();

		
		//追加 orderAdd.jsp
		if(("add").equals(code)) {
			String[] orderQtyList = req.getParameterValues("orderQty");
			@SuppressWarnings("unchecked")
			ArrayList<OrderSlipBean> addSlip = (ArrayList<OrderSlipBean>) session.getAttribute("addSlip");
			
			String customerErrMsg = null;
			String productErrMsg = null;
			
			//取引先名が入力されているかチェック
			String customerName = (String) session.getAttribute("addCustomerName");
			if(customerName == null) {
				customerErrMsg = errChecker.getE022();
			}
			
			//商品があるかチェック
			if(orderQtyList == null) {
				productErrMsg = errChecker.getE018();
			}else {
				//入力チェック
				boolean isAllEntered = true;
				for (String orderQty : orderQtyList) {
					isAllEntered = errChecker.IsEnteredCheck(orderQty);
					if (!isAllEntered) {
						break;
					}
				}
				//入力漏れあり
				if(!isAllEntered) {
					productErrMsg = errChecker.getE010();
				}
			}
			
			//エラーなしなら確認画面へ
			if(customerErrMsg == null && productErrMsg == null) {
				// 現在日時を取得
		        LocalDateTime nowDate = LocalDateTime.now();
		        // 形式調整 mysql DATE
		        DateTimeFormatter dtf1 =DateTimeFormatter.ofPattern("yyyy-MM-dd");
		        String now = dtf1.format(nowDate); 
		        //注文数反映
		    	int j=0;
		    	for(OrderSlipBean item:addSlip) {
		    		item.setOrderQty(Integer.parseInt(orderQtyList[j]));
		    		j++;
		    	}
		        //商品データ集計
		        ProductDao proDao = new ProductDao();
		        ArrayList<OrderSlipBean> list = proDao.selectProductAddInfo(addSlip);
				int n=0;
				for(OrderSlipBean item:list) {
					addSlip.get(n).setSalePrice(item.getSalePrice());
					addSlip.get(n).setUnitCost(item.getUnitCost());
					addSlip.get(n).setSaleAmount(item.getSaleAmount());
					addSlip.get(n).setGrossProfit(item.getGrossProfit());
					n++;
				}
		        //set session
				session.setAttribute("addSlip",addSlip);
		        session.setAttribute("orderDate", now);
		        session.setAttribute("code",code);
				req.setAttribute("message", "追加しますか?");
			}else {
				req.setAttribute("customerErrMsg", customerErrMsg);
				req.setAttribute("productErrMsg", productErrMsg);
				url = "orderAdd";
			}		
		}
		
		
		//キャンセル処理order_cancel.jspから
		if (("cancel").equals(code)) {
			String[] cancelQtyList = req.getParameterValues("cancelQty");
			String cancelComment = req.getParameter("cancelComment");

			//入力チェック
			boolean isAllEntered = true;
			for (String cancelQty : cancelQtyList) {
				isAllEntered = errChecker.IsEnteredCheck(cancelQty);
				if (!isAllEntered) {
					break;
				}
			}

			//キャンセル数入力漏れなし
			if (isAllEntered) {
				@SuppressWarnings("unchecked")
				ArrayList<OrderSlipBean> cancelSlip = (ArrayList<OrderSlipBean>) session.getAttribute("cancelSlip");

				//すべてキャンセル
				if (("受注内容全てをキャンセルする").equals(pageFlag)) {
					for (OrderSlipBean item : cancelSlip) {
						item.setCancelQty(item.getOrderQty() - item.getRefundQty());
					}

					//session set
					session.setAttribute("cancelSlip", cancelSlip);
					//set req
					req.setAttribute("message", "すべての商品をキャンセルしますか？");

				//部分キャンセル
				} else if (("確認").equals(pageFlag)) {
					int i = 0;
					for (OrderSlipBean item : cancelSlip) {
						item.setCancelQty(Integer.parseInt(cancelQtyList[i]));
						i++;
					}
					
					req.setAttribute("message", "キャンセルしますか？");
					//session set
					session.setAttribute("cancelSlip", cancelSlip);
				}
				session.setAttribute("code", code);
				session.setAttribute("cancelComment", cancelComment);

			//キャンセル数未入力が存在
			} else {
				url = "orderCancel";
				req.setAttribute("errMsg", errChecker.getE011());
			}
		}

		//返品処理order_refund.jspから
		if (("refund").equals(code)) {
			String[] refundQtyList = req.getParameterValues("refundQty");
			String refundComment = req.getParameter("refundComment");

			//入力チェック
			boolean isAllEntered = true;
			for (String refundQty : refundQtyList) {
				System.out.println(refundQty);
				isAllEntered = errChecker.IsEnteredCheck(refundQty);
				System.out.println(isAllEntered);
				if (!isAllEntered) {
					break;
				}
			}

			//返品数入力漏れなし
			if (isAllEntered) {
				@SuppressWarnings("unchecked")
				ArrayList<OrderSlipBean> refundSlip = (ArrayList<OrderSlipBean>) session.getAttribute("refundSlip");

				//すべて返品
				if (("受注内容全てを返品する").equals(pageFlag)) {
					for (OrderSlipBean item : refundSlip) {
						item.setRefundQty(item.getOrderQty() - item.getCancelQty());
					}

					//session set
					session.setAttribute("refundSlip", refundSlip);
					//set req
					req.setAttribute("message", "すべての商品を返品しますか？");

				//部分返品
				} else if (("確認").equals(pageFlag)) {
					int i = 0;
					for (OrderSlipBean item : refundSlip) {
						item.setRefundQty(Integer.parseInt(refundQtyList[i]));
						i++;
					}

					req.setAttribute("message", "返品しますか？");
					//session set
					session.setAttribute("refundSlip", refundSlip);
				}
				session.setAttribute("code", code);
				session.setAttribute("refundComment", refundComment);
				
			//返品数入力漏れあり
			} else {
				url = "orderRefund";
				req.setAttribute("errMsg", errChecker.getE012());
			}
		}
		
		
		
		
		
		
		
		//////////////////変更
		
		
		if (("change").equals(code)) {
			String[] orderQtyList = req.getParameterValues("orderQty");

			// 入力チェック
			boolean isAllEntered = true;
			for (String orderQty : orderQtyList) {
				isAllEntered = errChecker.IsEnteredCheck(orderQty);
				if (!isAllEntered) {
					break;
				}
			}

			// 注文数入力漏れなし
			if (isAllEntered) {
				@SuppressWarnings("unchecked")
				ArrayList<OrderSlipBean> changeSlip = (ArrayList<OrderSlipBean>) session.getAttribute("changeSlip");

				int i = 0;
				for (OrderSlipBean item : changeSlip) {
					item.setOrderQty(Integer.parseInt(orderQtyList[i]));
					i++;
				}
				
				req.setAttribute("message", "注文内容を変更しますか？");
				// session set
				session.setAttribute("changeSlip", changeSlip);
				session.setAttribute("code", code);
			} else {
				url = "orderChange";
				req.setAttribute("errMsg", errChecker.getE011());
			}
		}

		//jump
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}
}