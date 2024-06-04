package order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.OrderListBean;
import dao.OrderListDao;

public class OrderListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String forward = null;
		//文字コードの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session = request.getSession();
		//session reset
		session.setAttribute("code", null);
		session.setAttribute("orderId", null);
		session.setAttribute("customerName", null);
		session.setAttribute("orderDate", null);

		
		session.setAttribute("cancelSlip", null);
		session.setAttribute("cancelComment", null);
		session.setAttribute("refundSlip", null);
		session.setAttribute("refundComment", null);
		
		String noParameter = request.getParameter("no");
		int no = 0; // デフォルト値を設定
		String noParameter2 = (String) request.getAttribute("no");
		int no2 = 0;

		if (noParameter != null && !noParameter.isEmpty()) {
			no = Integer.parseInt(noParameter);
			if (no == 2 || no == 5 || no == 6 || no == 7) {
				// no の値に応じて遷移元の値を設定
				String source;
				String button = null;
				switch (no) {
				case 2:
					source = "orderSlip";
					button = "詳細";
					break;
				case 5:
					source = "orderChange";
					button = "変更";
					break;
				case 6:
					source = "orderCancel";
					button = "キャンセル";
					break;
				case 7:
					source = "orderRefund";
					button = "返品";
					break;
				default:
					source = "default"; // それ以外の場合はデフォルト値を設定
				}
				session.setAttribute("button", button);
				session.setAttribute("source", source);

			}

			if (noParameter2 != null && !noParameter2.isEmpty()) {
				no2 = Integer.parseInt(noParameter2);
				if (no2 == 2 || no2 == 5 || no2 == 6 || no2 == 7) {
					// no の値に応じて遷移元の値を設定
					String source;
					String button = null;
					switch (no2) {
					case 2:
						source = "orderSlip";
						button = "詳細";
						break;
					case 5:
						source = "orderChange";
						button = "変更";
						break;
					case 6:
						source = "orderCancel";
						button = "キャンセル";
						break;
					case 7:
						source = "orderRefund";
						button = "返品";
						break;
					default:
						source = "default"; // それ以外の場合はデフォルト値を設定
					}
					session.setAttribute("button", button);
					session.setAttribute("source", source);
				}
			}

			// OrderDaoのインスタンスを作成
			OrderListDao order = new OrderListDao();
			// orderList()メソッドを呼び出して受注情報を取得
			ArrayList<OrderListBean> orderList = order.orderList();

			// リクエスト属性に受注情報を設定
			request.setAttribute("orderList", orderList);

		}

		if (no == 3) {
			String status = request.getParameter("status");
			session.setAttribute("status", status);//後で使うかも

			String customerId = request.getParameter("customerId");
			if (customerId == null) {
				customerId = ""; // デフォルト値を設定
			}

			String message = "";
			ArrayList<OrderListBean> orderList = new ArrayList<>();
			OrderListDao order = new OrderListDao();

			if (!customerId.equals("")) {

				try {
					int cus_id = Integer.parseInt(customerId);

					if (status != null) {

						switch (status) {
						case "all_order":
							message = "すべての注文を表示します。";
							orderList = order.getOrdersByCustomerId(cus_id);

							break;
						case "cancel_only":
							message = "キャンセルされた注文のみ表示します。";
							orderList = order.selectCancelOrderList(cus_id);
							break;
						case "refund_only":
							message = "返金された注文のみ表示します。";
							orderList = order.selectRefundOrderList(cus_id);
							break;
						default:
							message = "不明なステータスです。";
							break;
						}

					}
				} catch (NumberFormatException e) {
					// エラーログを出力するなど、適切なエラーハンドリングを行う
					e.printStackTrace();
				}
			} else {
				message = "顧客IDが空です。";
				switch (status) {
				case "all_order":
					message = "すべての注文を表示します。";
					orderList = order.orderList();

					break;
				case "cancel_only":
					message = "キャンセルされた注文のみ表示します。";
					orderList = order.cancelOrderList();
					break;
				case "refund_only":
					message = "返金された注文のみ表示します。";
					orderList = order.refundOrderList();
					break;
				default:
					message = "不明なステータスです。";
					break;
				}
			}
			request.setAttribute("orderList", orderList);
			request.setAttribute("message", message);
		}

		forward = "/jsp/orderList.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
	}

}
