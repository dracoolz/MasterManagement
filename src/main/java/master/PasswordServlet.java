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

public class PasswordServlet extends HttpServlet {

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
		
		int no = Integer.parseInt((String)request.getParameter("no"));
		int userid = (int) session.getAttribute("userid");
		
		String pass1 = (String)request.getParameter("pass1");
		String pass2 = (String)request.getParameter("pass2");
		String passb = "";
		
		Errcheck err = new Errcheck();
		String errmsg1 = null;
		String errmsg2 = null;
		
		// パスワードリセット
		if(no == 1) {
			String[] input = {pass1,pass2};
			String[] names = {"新しいパスワード","新しいパスワード(確認)"};
			
			errmsg1 = err.inputCheck(input,input,names,names);
			
			if(errmsg1 == null) {
				UserDao dao = new UserDao();
				errmsg2 = err.sameCheck(pass1, pass2);
				if(errmsg1 == null && errmsg2 == null) {
					dao.updatePass(userid,pass1);
					rd = request.getRequestDispatcher("/jsp/reset.jsp?state=リセット");
				}
			}
		//パスワード変更
		} else {
			passb = (String)request.getParameter("passb");
			
			String[] input = {passb,pass1,pass2};
			String[] names = {"以前のパスワード","新しいパスワード","新しいパスワード(確認)"};
			
			errmsg1 = err.inputCheck(input,input,names,names);
			
			if(errmsg1 == null) {
				UserDao dao = new UserDao();
				UserBean bean = dao.select(userid);
				String correct = bean.getPassword();
				errmsg1 = err.correctCheck(passb, correct);
				errmsg2 = err.sameCheck(pass1, pass2);
				System.out.println(pass1+":"+correct);
				if(errmsg1 == null && errmsg2 == null) {
					dao.updatePass(userid,pass1);
					rd = request.getRequestDispatcher("/jsp/reset.jsp?state=変更");
				} else if(errmsg1 != null) {
					errmsg1 = "以前の"+errmsg1;
				}
			}
		}
		if(errmsg1 != null || errmsg2 != null) {
			request.setAttribute("errmsg1", errmsg1);
			request.setAttribute("errmsg2", errmsg2);
			request.setAttribute("passb", passb);
			request.setAttribute("pass1", pass1);
			request.setAttribute("pass2", pass2);
			rd = request.getRequestDispatcher("/jsp/password.jsp?"+no);
		}
		rd.forward(request, response);
	}
}
