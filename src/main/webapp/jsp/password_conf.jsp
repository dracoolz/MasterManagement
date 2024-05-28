<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<% String state = request.getParameter("state"); %>
<title><%="パスワード"+state%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<div align="center">
		<strong style="font-size: 30px;">
			<%="パスワード"+state%>
		</strong>
			<% if(state.equals("リセット")) {%>
				<form action= "./login.jsp" method="post">
			<% } else {%>
				<div align="right">
					<p>ようこそ、*****さん</p>
					<a href="./login.jsp">ログアウト</a>
				</div>
				<form action= "./main.jsp" method="post">
		<% } %>
			<p><%="パスワードを"+state+"しました"%></p>
			
		<% if(state.equals("リセット")) { %>
			<input type="button" name="back" onclick="location.href='./login.jsp'" value="ログイン画面へ">
		<% } else { %>
			<input type="button" name="back" onclick="location.href='./main.jsp'" value="メインメニューへ">
		<% } %>
	</div>
	</form>

</body>
</html>