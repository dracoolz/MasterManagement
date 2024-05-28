<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<% String state = request.getParameter("state"); %>
<title><%="パスワード"+state%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
	<div align="center">
		<strong style="font-size: 30px;">
			<% if(state.equals("リセット")) {%>
				パスワードを忘れた場合<br>
				パスワードリセット</storong><br><br>
				<form action= "./password_conf.jsp?state=リセット" method="post">
				<table>
			<% } else {%>
				パスワード変更</strong>
				<div align="right">
					<p>ようこそ、*****さん</p>
					<a href="./login.jsp">ログアウト</a>
				</div>
				<form action= "./password_conf.jsp?state=変更" method="post">
				<table>
						<tr>
							<td>以前のパスワード :</td>
							<td><input type="password" size="20" name="beforepass"></td>
						</tr>
			<% } %>
					<tr>
						<td>新しいパスワード：</td>
						<td><input type="password" size="20" name="afterpass1"></td>
					</tr>
					<tr>
						<td>新しいパスワード(確認)：</td>
						<td><input type="password" size="20" name="afterpass2"></td>
					</tr>
				</table>
		<br>
		<table>
			<tr>
				<td><input type="submit" name="submit" value="確定"></td>
				<td><input type="reset" name="reset" value="リセット"></td>
				</form>
				<td>
					<% if(state.equals("リセット")) { %>
						<input type="button" name="back" onclick="location.href='./forget.jsp'" value="戻る">
					<% } else { %>
						<input type="button" name="back" onclick="location.href='./main.jsp'" value="戻る">
					<% } %>
				</td>
			</tr>
		</table>
	</div>
	</form>
</body>
</html>