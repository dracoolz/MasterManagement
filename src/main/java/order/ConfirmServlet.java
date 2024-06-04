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
		System.out.println("orderId:"+session.getAttribute("orderId")); //test

		//jumpurl
		String url = "/jsp/confirm.jsp";

		ErrCheck errChecker = new ErrCheck();

		//キャンセル処理order_cancel.jspから
		if (("cancel").equals(code)) {
			String[] cancelQtyList = req.getParameterValues("cancelQty");
			String cancelComment = req.getParameter("cancelComment");

			//入力チェック
			boolean isAllEntered = true;
			for (String cancelQty : cancelQtyList) {
				System.out.println(cancelQty);
				isAllEntered = errChecker.IsEnteredCheck(cancelQty);
				System.out.println(isAllEntered);
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
				System.out.println("no field!");
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

		//jump
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}
}