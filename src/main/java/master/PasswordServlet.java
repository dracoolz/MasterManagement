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
		String err = null;
		Errcheck e = new Errcheck();
		
		String pw1 = (String)request.getParameter("pass1");
		String pw2 = (String)request.getParameter("pass2");
		String pwb = "";
		
		if(no == 2) {
			pwb = (String)request.getParameter("passb");
			if((err =  e.injectionCheck(pwb,"以前のパスワード")) == null) {
				err = e.inputCheck(pwb,"以前のパスワード");
			}
		}
		if(err == null) {
			String[] input = {pw1,pw2};
			String[] names = {"新しいパスワード","新しいパスワード(確認)"};
			
			if((err =  e.injectionCheck(input,names)) == null) {
				if((err = e.inputCheck(input,input,names,names)) == null) {
					UserDao dao = new UserDao();
					if(no == 2) {
						UserBean bean = dao.select(userid);
						String correct = bean.getPassword();
						if(e.correctCheck(pwb, correct) != null) {
							err = "以前の"+e.correctCheck(pwb, correct);
						}
					}
					if(err == null && e.sameCheck(pw1, pw2) == null) {
						dao.updatePass(userid,pw1);
						if(no == 1) {
							rd = request.getRequestDispatcher("/jsp/reset.jsp?state=リセット");
						} else {
							rd = request.getRequestDispatcher("/jsp/reset.jsp?state=変更");
						}
						rd.forward(request, response);
					} else if(err == null){
						err = e.sameCheck(pw1, pw2);
					}
				}
			}
		}
		request.setAttribute("err", err);
		request.setAttribute("passb", pwb);
		request.setAttribute("pass1", pw1);
		request.setAttribute("pass2", pw2);
		rd = request.getRequestDispatcher("/jsp/password.jsp?"+no);
		rd.forward(request, response);
	}
}
