<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>返品処理</title>
</head>
<body>
	<!-- title -->
	<h1 align="center">返品処理</h1>
	
	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	
	
	<!-- mojicode siteisitemiru -->
	<%request.setCharacterEncoding("UTF-8"); %>
	<%response.setContentType("text/html;charset=UTF-8"); %>

	<!-- getAttribute from servlet?-->
	<% ArrayList<HumanBean> beanList = (ArrayList<HumanBean>) request.getAttribute("users");%>
	
	<!-- getParameter from html or jsp 's form?-->
	<% String str = request.getParameter(""); %>

	<!-- form link -->
	<form action="/NockWeb/control" method="post">
	</form>
</body>
</html>