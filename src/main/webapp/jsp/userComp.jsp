<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" src="userComp.css">
</head>

<% String str =  request.getParameter("submit");%>
<body>
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
		<strong><%="ユーザ情報の"+str%></strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="/first">ログアウト</a>
		</div>
		<p><%=str+"しました"%></p>
		
		<%if(request.getParameter("status").equals("change")) {%>
			<form action= "./login" method="post">
				<p><input type ="submit"  name="submit" value="次へ"></p>
			</form>
		<% } else { %>
			<form action= "./manageControl?no=1" method="post">
				<p><input type ="submit"  name="submit" value="次へ"></p>
			</form>
		<% } %>
	</div>
</body>
</html>