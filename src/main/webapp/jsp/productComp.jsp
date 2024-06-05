<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" src="productComp.css">
</head>

<body>
	<% String str = request.getParameter("submit"); %>
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
		<strong><%="商品情報の"+str%></strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="./first">ログアウト</a>
		</div>
		<p><%=str+"しました"%></p>
		<form action= "./manageControl?no=3" method="post">
			<p><input type ="submit"  name="submit" value="次へ"></p>
		</form>
	</div>
</body>
</html>