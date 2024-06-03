package master;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BigCategoryBean;
import bean.ProductBean;
import bean.SmallCategoryBean;
import bean.UserBean;
import dao.BigCategoryDao;
import dao.ProductDao;
import dao.SmallCategoryDao;
import dao.UserDao;

public class ManageControlServlet extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

		//文字コードの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		RequestDispatcher rd = null;
		switch(no) {
			case 1:
				UserDao dao = new UserDao();
				ArrayList<UserBean> uarr = new ArrayList<UserBean>();
				uarr = dao.selectAll();
				request.setAttribute("list", uarr);
				rd = request.getRequestDispatcher("/jsp/user.jsp");
				break;
			case 2:
				BigCategoryDao bdao = new BigCategoryDao();
				ArrayList<BigCategoryBean> barr = new ArrayList<BigCategoryBean>();
				barr = bdao.selectAll();
				request.setAttribute("bclist", barr);
				
				SmallCategoryDao sdao = new SmallCategoryDao();
				ArrayList<SmallCategoryBean> sarr = new ArrayList<SmallCategoryBean>();
				sarr = sdao.selectAll();
				request.setAttribute("sclist", sarr);
				
				rd = request.getRequestDispatcher("./jsp/category.jsp");
				break;
			case 3:
				ProductDao pdao = new ProductDao();
				ArrayList<ProductBean> parr = new ArrayList<ProductBean>();
				parr = pdao.selectAll();
				request.setAttribute("list", parr);
				
				BigCategoryDao bdao2 = new BigCategoryDao();
				ArrayList<BigCategoryBean> barr2 = new ArrayList<BigCategoryBean>();
				barr2 = bdao2.selectAll();
				request.setAttribute("bclist", barr2);
				rd = request.getRequestDispatcher("./jsp/product.jsp");
				break;
			case 4:
				rd = request.getRequestDispatcher("/list?no=1");
				break;
			case 5:
				rd = request.getRequestDispatcher("/list?no=2");
				break;
			case 6:
				rd = request.getRequestDispatcher("/totalMenu");
				break;
		}
		rd.forward(request, response);
	}
}
