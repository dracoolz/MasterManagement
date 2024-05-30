package master;

import java.io.IOException;
<<<<<<< HEAD
=======
import java.util.ArrayList;
>>>>>>> origin/main

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======
import bean.BigCategoryBean;
import bean.ProductBean;
import bean.SmallCategoryBean;
import bean.UserBean;
import dao.BigCategoryDao;
import dao.ProductDao;
import dao.SmallCategoryDao;
import dao.UserDao;

>>>>>>> origin/main
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
<<<<<<< HEAD
			case 3:
				rd = request.getRequestDispatcher("./list?no=1");
				break;
			case 4:
				rd = request.getRequestDispatcher("./list?no=2");
				break;
		}
		
		
=======
			case 0:
				UserDao dao = new UserDao();
				ArrayList<UserBean> uarr = new ArrayList<UserBean>();
				uarr = dao.selectAll();
				request.setAttribute("list", uarr);
				rd = request.getRequestDispatcher("/jsp/user.jsp");
				break;
			case 1:
				BigCategoryDao bdao = new BigCategoryDao();
				ArrayList<BigCategoryBean> barr = new ArrayList<BigCategoryBean>();
				barr = bdao.selectAll();
				request.setAttribute("blist", barr);
				
				SmallCategoryDao sdao = new SmallCategoryDao();
				ArrayList<SmallCategoryBean> sarr = new ArrayList<SmallCategoryBean>();
				sarr = sdao.selectAll();
				request.setAttribute("slist", sarr);
				
				rd = request.getRequestDispatcher("./jsp/category.jsp");
				break;
			case 2:
				ProductDao pdao = new ProductDao();
				ArrayList<ProductBean> parr = new ArrayList<ProductBean>();
				parr = pdao.selectAll();
				request.setAttribute("list", parr);
				rd = request.getRequestDispatcher("./jsp/product.jsp");
				break;
			case 3:
				rd = request.getRequestDispatcher("/list?no=1");
				break;
			case 4:
				rd = request.getRequestDispatcher("/list?no=2");
				break;
			case 5:
				rd = request.getRequestDispatcher("/totalmenu");
				break;
		}
>>>>>>> origin/main
		rd.forward(request, response);
	}
}
