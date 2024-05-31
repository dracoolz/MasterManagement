<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pakege.class" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- mojicode siteisitemiru -->
	<%--request.setCharacterEncoding("UTF-8"); --%>
	<%--response.setContentType("text/html;charset=UTF-8"); --%>

	<!-- getAttribute from servlet?-->
	<% ArrayList<HumanBean> beanList = (ArrayList<HumanBean>) request.getAttribute("users");%>
	
	<!-- getParameter from html or jsp 's form?-->
	<% String str = request.getParameter(""); %>

	<!-- form link -->
	<form action="/NockWeb/control" method="post">
	</form>
</body>
</html>