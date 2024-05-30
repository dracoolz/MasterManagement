package master;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

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
				UserDao dao = new UserDao();
				String to = dao.select(iid).getEmp_email();
				System.out.println("宛先："+to);
		        // メールサーバーのホスト名
		        String host = "smtp.gmail.com";
		        // ポート番号（SSLを使用する場合）
		        int port = 8080;
		        // 送信元メールアドレスとパスワード
		        String from = "gksi.kenshu.202405@gmail.com";
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
		        session.setDebug(true);
		        System.out.println("props ok");
		        // セッションの取得
		        System.out.println("ike");
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
		            System.out.println("send");
		            Transport.send(message);
		            System.out.println("sent");
		            // 送信成功時の処理
		            response.getWriter().println("メールを送信しました。");
		        } catch (MessagingException mex) {
		            // 送信失敗時の処理
		            mex.printStackTrace();
		            response.getWriter().println("メールの送信に失敗しました。");
		    }
				err = "登録されているメールアドレスにリンクを送信しました";	
			}
		}
		request.setAttribute("id", id);
		request.setAttribute("err", err);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/forget.jsp");
		rd.forward(request, response);		
	}
}
