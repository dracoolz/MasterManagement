<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<% String id = "";
String pass = "";
try {
	id = (String)request.getAttribute("id");
	pass = (String)request.getAttribute("pass");
	if(id.length() == 0) {
		id = "";
	}
	if(pass.length() == 0) {
		pass = "";
	}
} catch (Exception e) {
	id = "";
	pass = "";
}%>

<script>
	function blank() {
		document.getElementById("id").value = "";
		document.getElementById("pass").value = "";
	}
</script>

<body>
	<div align="center">
		<strong>ログイン画面</strong>
		<br><br>
		<form method="post" action="./login">
			<table>
				<tr>
					<td>社員番号 ： </td>
					<td><input type="text" size="20" id="id" name="id" maxlength="20" value=<%=id%>></td>
				</tr>
				<tr>
					<td>パスワード：</td>
					<td><input type="password" size="20" id="pass" maxlength="100" name="pass" value=<%=pass%>></td>
				</tr>
			</table><br>
			<a href="./login">パスワードを忘れた場合</a><br><br>
			<% if(request.getAttribute("errmsg1") != null) { %>
				<p id="err"><%=request.getAttribute("errmsg1")%></p>
			<% }
			if(request.getAttribute("errmsg2") != null) { %>
			<p id="err"><%=request.getAttribute("errmsg2")%></p>
			<% } %>
			<input type="submit" name="submit" value="ログイン">
			<input type="button" name="reset" value="リセット" onclick="blank()">
		</form>
	</div>
</body>
</html>
</body>
</html>