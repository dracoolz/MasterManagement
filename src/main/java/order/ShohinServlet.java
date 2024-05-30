package sogo;



import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShohinServlet extends HttpServlet {

	static final long serialVersionUID = 1L;



	public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
			doPost( request, response );
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

		String forward=null;
		//文字コードの設定
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		//セッションの取得
        HttpSession session = request.getSession(true);

		//kakunin.jspから登録ボタンが押された際の処理
		if(request.getParameter("submit").equals("登録")){

			String id = (String)session.getAttribute("id");
			String name =(String)session.getAttribute("name");
			int kakaku = Integer.parseInt((String) session.getAttribute("kakaku"));

			//DAOをインスタンス化
			ShohinDAO dao= new ShohinDAO();
			int rs =dao.insert(id, name, kakaku);
			System.out.println(rs);
			request.setAttribute("compmsg", "登録が完了しました");
			forward="/web/mod.jsp?no=4";
		}

		//kakunin.jspから登録ボタンが押された際の処理
		if(request.getParameter("submit").equals("変更")){

			String id = (String)session.getAttribute("id");
			String name =(String)session.getAttribute("name");
			int kakaku = Integer.parseInt((String) session.getAttribute("kakaku"));

			//DAOをインスタンス化
			ShohinDAO dao= new ShohinDAO();
			int rs =dao.update(name, kakaku ,id);
			System.out.println(rs);
			request.setAttribute("compmsg", "変更されました");
			forward="/web/mod.jsp?no=4";


		}

		//kakunin.jspから削除ボタンが押された際の処理
		if(request.getParameter("submit").equals("削除")){

			String id = (String)session.getAttribute("id");

			//DAOをインスタンス化
			ShohinDAO dao= new ShohinDAO();
			int rs =dao.delete(id);
			System.out.println(rs);
			request.setAttribute("compmsg","削除されました");
			request.setAttribute("list",dao.selectAll());
			forward="/web/ichiran.jsp?no=2";
		}


		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);

    }


}
