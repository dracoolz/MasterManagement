<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" src="../css/main.css">
</head>
<body>
	<div align="center">
		<strong>ログイン画面</strong>
		<br><br>
		<form method="post" action="./login">
			<table>
				<tr>
					<td>社員番号 ： </td>
					<td><input type="text" size="20" name="id"></td>
				</tr>
				<tr>
					<td>パスワード：</td>
					<td><input type="password" size="100" name="pass"></td>
				</tr>
			</table><br>
			<a href="./forget.jsp">パスワードを忘れた場合</a><br><br>
			<% try {
				if(request.getAttribute("err") != null) {
				 	request.getParameter("err");
			} }catch (Exception e){
			}%>
			<input type="submit" name="submit" value="ログイン">
			<input type="reset" name="reset" value="リセット">
		</form>
	</div>
</body>
</html>
</body>
</html>