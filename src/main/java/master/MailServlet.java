package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MailServlet extends HttpServlet {

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
		String err = null;
		Errcheck e = new Errcheck();
		
		if(e.inputCheck(id) == 1) {
			err = "社員番号が入力されていません";
		} else if(e.numberCheck(id) == false) {
			err = "社員番号が数字で入力されていません";
		} else {
			int iid = Integer.parseInt(id);
			if(e.existId(iid) == false) {
				err = "存在しない社員番号です";
			} else {
				err = "登録されているメールアドレスにリンクを送信しました";	
			}
		}
		request.setAttribute("id", id);
		request.setAttribute("err", err);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/forget.jsp");
		rd.forward(request, response);		
	}
}
