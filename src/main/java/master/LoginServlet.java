package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import dao.UserDao;

public class LoginServlet extends HttpServlet {

	public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
			doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

		//文字コードの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		
		String id = (String)request.getParameter("id");
		String pw = (String)request.getParameter("pass");
		String err = null;
		Errcheck e = new Errcheck();
		
		if(e.inputCheck(id,pw) == 1) {
			err = "社員番号が入力されていません";
		} else if(e.inputCheck(id, pw) == 2){
			err = "パスワードが入力されていません";
		} else if(e.numberCheck(id) == false) {
			err = "社員番号が数字で入力されていません";
		} else {
			int iid = Integer.parseInt(id);
			String correct;
			UserDao dao = new UserDao();
			UserBean bean = dao.select(iid);
			correct = bean.getPassword();
			if(e.correctCheck(pw, correct) == false) {
				err = "社員番号またはパスワードが違います";
			} else {
				HttpSession session = request.getSession(true);
				session.setAttribute("username", bean.getEmp_name());
				session.setAttribute("userrole", bean.getRole());
				RequestDispatcher rd = request.getRequestDispatcher("/jsp/main.jsp");
				rd.forward(request, response);
			}
		}
		request.setAttribute("err", err);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/login.jsp");
		rd.forward(request, response);
	}
}
