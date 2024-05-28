<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>
	<div align="center">
		<strong style="font-size: 30px;">ログイン画面</strong>
		<br><br>
		<form method="post" action="./main.jsp">
			<table>
				<tr>
					<td>社員番号 ： </td>
					<td><input type="text" size="20" name="id"></td>
				</tr>
				<tr>
					<td>パスワード：</td>
					<td><input type="password" size="20" name="pass"></td>
				</tr>
			</table><br>
			<a href="./forget.jsp">パスワードを忘れた場合</a><br><br>
			<input type="submit" name="submit" value="ログイン">
			<input type="reset" name="reset" value="リセット">
		</form>
	</div>
</body>
</html>
</body>
</html>