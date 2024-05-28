<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>商品マスタ</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<div align="center">
		<strong style="font-size: 30px;">パスワードを忘れた場合</strong>
	</div>
	<form action="./password.jsp?state=リセット" method="post">
		<div align="center">
			<p>社員番号を入力してください</p>
			<p>登録されているメールアドレスにリンクを送信します</p>
			<table>
				<tr>
					<td>社員番号 :</td>
					<td><input type="text" size="20" name="beforepass"></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><input type="submit" name="submit" value="メール送信"></td>
					<td><input type="reset" name="reset" value="リセット"></td>
	</form>
					<td><input type="button" name="back"
						onclick="location.href='./login.jsp'" value="戻る"></td>
				<tr>
			</table>
		</div>
</body>
</html>