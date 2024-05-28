<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>メインメニュー</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" src="**.css">
</head>

<body>
	<div align="center">
		<strong>メインメニュー</strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="/first">ログアウト</a>
		</div>
	</div>
	<div align="center">
		<a href="./master.jsp">マスタ管理</a><br><br>
		<a href="">受注管理</a><br><br>
		<a href="./password.jsp?state=change">パスワード変更</a>
	</div>


</body>
</html>