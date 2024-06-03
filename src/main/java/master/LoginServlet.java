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
					String[] input = {id,pw};
					String[] names = {"社員番号","パスワード"};
					
					if((err = e.inputCheck(input,input,names,names)) == null) {
						if((err = e.numberCheck(id,"社員番号")) == null) {
							int iid = Integer.parseInt(id);
							UserDao dao = new UserDao();
							UserBean bean = dao.select(iid);
							String correct = bean.getPassword();
							if(e.correctCheck(pw, correct) != null) {
								err = "社員番号または"+e.correctCheck(pw, correct);
							} else {
								session.setAttribute("userid", bean.getEmp_id());
								session.setAttribute("username", bean.getEmp_name());
								session.setAttribute("userrole", bean.getRole());
								rd = request.getRequestDispatcher("/jsp/main.jsp");
							}
						}
					}
					if(rd == null) {
						request.setAttribute("id", id);
						request.setAttribute("pass", pw);
						request.setAttribute("err", err);
						rd = request.getRequestDispatcher("/jsp/login.jsp");
					}
				} catch (Exception e) {
					rd = request.getRequestDispatcher("/jsp/main.jsp");
				}
			}
		} catch (Exception e2) {
			rd = request.getRequestDispatcher("/jsp/forget.jsp");
		}
		rd.forward(request, response);
	}
}
