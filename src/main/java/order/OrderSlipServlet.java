package order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.OrderSlipBean;
import dao.OrderSlipDao;

public class OrderSlipServlet extends HttpServlet {

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
		
		// 注文番号,日付、取引先名を受け取る
        
        String orderId = request.getParameter("orderId");
        String orderDate = request.getParameter("orderDate");
        String customerName = request.getParameter("customerName");

        OrderSlipDao orderSlip = new OrderSlipDao(); // この OrderDAO のインスタンスは適切に初期化されている必要があります
        int or_Id = Integer.parseInt(orderId);
        ArrayList<OrderSlipBean> orderSlipList = orderSlip.selectOrderSlip(or_Id);
        
        
      
       
        
        //詳細ｊｓｐに遷移
        request.setAttribute("orderSlipList", orderSlipList);
			forward="/jsp/orderSlip.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(forward);
			rd.forward(request, response);
		
		

    }
}
