<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>受注追加</title>
</head>
<body>
	<!-- title -->
	<h1 align="center">受注追加</h1

	<div align="right">
		<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
		<a href="/first">ログアウト</a>
	</div>
	
	<form action="" method="post">
		<input type="textbox" name="customer_id" placeholder="取引先ID検索">
	</form>
	
	
	<!-- mojicode siteisitemiru -->
	<%--request.setCharacterEncoding("UTF-8"); --%>
	<%--response.setContentType("text/html;charset=UTF-8"); --%>

	<!-- getAttribute from servlet?-->
	<%-- ArrayList<HumanBean> beanList = (ArrayList<HumanBean>) request.getAttribute("users");--%>
	
	<!-- getParameter from html or jsp 's form?-->
	<%-- String str = request.getParameter(""); --%>

	<!-- form link -->
	
</body>
</html>