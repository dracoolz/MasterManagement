<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<% String state = request.getParameter("state"); %>
<title><%="パスワード"+state%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body>
	<div align="center">
		<strong>
			<%="パスワード"+state%>
		</strong>
			<% if(state.equals("リセット")) {%>
				<form action= "./first" method="post">
			<% } else {%>
				<div align="right">
					<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
					<a href="./first">ログアウト</a>
				</div>
				<form action= "./login" method="post">
		<% } %>
			<p><%="パスワードを"+state+"しました"%></p>
			
		<% if(state.equals("リセット")) { %>
			<input type="submit" name="submit" value="ログイン画面へ">
		<% } else { %>
			<input type="submit" name="submit" value="メインメニューに戻る">
		<% } %>
	</div>
	</form>

</body>
</html>