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
		
		int no = Integer.parseInt(req.getParameter("no"));
		
		if(no == 1) {
			
			//for companyList
	        // DAOをインスタンス化する
	        SalesDao dao = new SalesDao();
	        // リクエストにDAOで取得したユーザ情報をセットする
	        req.setAttribute("companylist", dao.selectCusSales());
	        
	        RequestDispatcher rd = req.getRequestDispatcher("/jsp/companyList.jsp");
	        rd.forward(req, res);
			
	    } else {
	 
	    	//for productList
			// DAOをインスタンス化する
			SalesDao dao = new SalesDao();
			// リクエストにDAOで取得したユーザ情報をセットする
			req.setAttribute("productlist", dao.selectProSales());
	
			RequestDispatcher rd = req.getRequestDispatcher("/jsp/productList.jsp");
			rd.forward(req, res);
	
	    	
	    }
	}
}
