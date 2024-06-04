package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(true);
		
		String id = (String)request.getParameter("id");
		String err = null;
		Errcheck e = new Errcheck();
		
		if((err = e.inputCheck(id,"社員番号")) == null) {
			if((err = e.numberCheck(id,"社員番号")) == null) {
			 	int iid = Integer.parseInt(id);
				if((err = e.idExistCheck(iid,"user")) == null) {
					/* メールの送信
					UserDao dao = new UserDao();
					String to = dao.select(iid).getEmp_email();
					String from = "gksi.kenshu.202405@gmail.com";
					// メールサーバーのホスト名
					String host = "smtp.gmail.com";
					// ポート番号
					int port = 587;
					// 送信元メールアドレスのパスワード
					String password = "Pa$$w0rd12345";
					
					// メールプロパティの設定
					Properties props = new Properties();
					props.put("mail.smtp.socketFactory.port", port);
					props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
					props.put("mail.smtp.socketFactory.fallback", "false");
					props.put("mail.smtp.ssl.enable", "true");
					props.put("mail.smtp.ssl.trust", host);
					Session session = Session.getInstance(props, new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(from, password);
						}
					});
					try {
						// メッセージの作成
						System.out.println("start");
						MimeMessage message = new MimeMessage(session);
						message.setFrom(new InternetAddress(from));
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
						message.setSubject("パスワードリセット");
						message.setText("以下のリンクからパスワードをリセットしてください\n"
											+ "/jsp/password.jsp");
						// メールの送信
						Transport.send(message);
						// 送信成功時の処理
						response.getWriter().println("メールを送信しました。");	
					} catch (MessagingException mex) {
						// 送信失敗時の処理
						mex.printStackTrace();
						response.getWriter().println("メールの送信に失敗しました。");
					}
					err = "登録されているメールアドレスにリンクを送信しました";	
					*/
					
					session.setAttribute("userid",iid);
					RequestDispatcher rd = request.getRequestDispatcher("/jsp/password.jsp?no=1");
					rd.forward(request, response);
				}
			}
		}
		request.setAttribute("id", id);
		request.setAttribute("err", err);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/forget.jsp");
		rd.forward(request, response);		
	}
}
