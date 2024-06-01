package order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderListViewBean;
import dao.OrderListDao;

public class OrderListServlet extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
		
		String forward=null;
		//文字コードの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		// OrderDaoのインスタンスを作成
		OrderListDao order = new OrderListDao();
		
		
			
			 // orderList()メソッドを呼び出して受注情報を取得
	        ArrayList<OrderListViewBean> orderList = order.orderList();

	        // リクエスト属性に受注情報を設定
	        request.setAttribute("orderList", orderList);
			
			
			forward="/jsp/orderlist.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		
		

    }
}
