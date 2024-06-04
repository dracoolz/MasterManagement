<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>メインメニュー</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>

<body>
	<div align="center">
		<strong>メインメニュー</strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="./first">ログアウト</a>
		</div>
	</div>
	<div align="center">
		<% if (((int)(session.getAttribute("userrole")) == 0)) { %>
			<a href="./master?no=1">マスタ管理</a><br><br>
		<% } %>
		<a href="./master?no=2">受注管理</a><br><br>
		<a href="./master?no=3">パスワード変更</a>
	</div>
</body>
</html>