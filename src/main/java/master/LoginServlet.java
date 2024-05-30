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
		HttpSession session = request.getSession(true);
		RequestDispatcher rd = null;
		
		try {
			String name = (String)request.getParameter("submit");
			if(name.equals("ログイン") || name.equals("メインメニューに戻る")) {
				try {
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
						UserDao dao = new UserDao();
						UserBean bean = dao.select(iid);
						String correct = bean.getPassword();
						if(e.correctCheck(pw, correct) == false) {
							err = "社員番号またはパスワードが違います";
						} else {
							session.setAttribute("username", bean.getEmp_name());
							session.setAttribute("userrole", bean.getRole());
							rd = request.getRequestDispatcher("/jsp/main.jsp");
							rd.forward(request, response);
						}
					}
					request.setAttribute("id", id);
					request.setAttribute("pass", pw);
					request.setAttribute("err", err);
					rd = request.getRequestDispatcher("/jsp/login.jsp");
				} catch (Exception e) {
					rd = request.getRequestDispatcher("/jsp/main.jsp");
				}
			}
		} catch (Exception e2) {
			e2.printStackTrace();
			rd = request.getRequestDispatcher("/jsp/forget.jsp");
		}
		rd.forward(request, response);
	}
}
