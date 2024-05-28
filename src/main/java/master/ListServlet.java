package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SalesDao;

public class ListServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//for productList
//		// DAOをインスタンス化する
//		SalesDao dao = new SalesDao();
//		// リクエストにDAOで取得したユーザ情報をセットする
//		req.setAttribute("productlist", dao.selectProSales());
//
//		RequestDispatcher rd = req.getRequestDispatcher("/jsp/productList.jsp");
//		rd.forward(req, res);
		
		
		
		// Check if the "送信" button is pressed
	    if (req.getParameter("submit") != null && req.getParameter("submit").equals("検索")) {
	        String customerName = req.getParameter("customer_name");
	        String contactName = req.getParameter("contact_name");
	        String district = req.getParameter("district");
	        
	        System.out.println("CustomerName : " + customerName);
	        System.out.println("contactName : " + contactName);
	        System.out.println("district : " + district);

	        if (customerName != null) {
	            // DAOをインスタンス化する
	            SalesDao dao = new SalesDao();
	            // リクエストにDAOで取得したユーザ情報をセットする
	            req.setAttribute("companylist", dao.selectCusName(customerName));

	            RequestDispatcher rd = req.getRequestDispatcher("/jsp/companyList.jsp");
	            rd.forward(req, res);

	        } else if (contactName != null) {
	            // DAOをインスタンス化する
	            SalesDao dao = new SalesDao();
	            // リクエストにDAOで取得したユーザ情報をセットする
	            req.setAttribute("companylist", dao.selectContact(contactName));

	            RequestDispatcher rd = req.getRequestDispatcher("/jsp/companyList.jsp");
	            rd.forward(req, res);

	        } else if (district != null) {
	            // DAOをインスタンス化する
	            SalesDao dao = new SalesDao();
	            // リクエストにDAOで取得したユーザ情報をセットする
	            req.setAttribute("companylist", dao.selectDistrict(district));

	            RequestDispatcher rd = req.getRequestDispatcher("/jsp/companyList.jsp");
	            rd.forward(req, res);
	        }

	    } else {
	        // Handle default case
	        // DAOをインスタンス化する
	        SalesDao dao = new SalesDao();
	        // リクエストにDAOで取得したユーザ情報をセットする
	        req.setAttribute("companylist", dao.selectCusSales());
	        
	        String customerName = req.getParameter("customer_name");
	        String contactName = req.getParameter("contact_name");
	        String district = req.getParameter("district");
	        
	        System.out.println("CustomerName : " + customerName);
	        System.out.println("contactName : " + contactName);
	        System.out.println("district : " + district);


	        RequestDispatcher rd = req.getRequestDispatcher("/jsp/companyList.jsp");
	        rd.forward(req, res);
	    }
	}
}
