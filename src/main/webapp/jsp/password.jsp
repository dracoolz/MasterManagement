<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<% int no = Integer.parseInt(request.getParameter("no")); %>
<title><%="パスワード"+ no%></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<% String passb = "";
String pass1 = "";
String pass2 = "";
try {
	passb = (String)request.getAttribute("passb");
	pass1 = (String)request.getAttribute("pass1");
	pass2 = (String)request.getAttribute("pass2");
	if(passb.length() == 0) {
		passb = "";
	}
	if(pass1.length() == 0) {
		pass1 = "";
	}
	if(pass2.length() == 0) {
		pass2 = "";
	}
} catch (Exception e) {
	passb = "";
	pass1 = "";
	pass2 = "";
}%>

<script>
	function blank() {
		var no = <%= no %>;
		if(no == 2) {
			document.getElementById("bp").value = "";
		}
		document.getElementById("np1").value = "";
		document.getElementById("np2").value = "";
	}
</script>

<body>
	<div align="center">
		<strong>
			<% if(no == 1) {%>
				パスワードを忘れた場合<br>
				パスワードリセット</strong><br><br>
				<form action= "./password?no=1" method="post">
				<table>
			<% } else {%>
				パスワード変更</strong>
				<div align="right">
					<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
					<a href="./first">ログアウト</a>
				</div>
				<form action= "./password?no=2" method="post">
				<table>
						<tr>
							<td>以前のパスワード :</td>
							<td><input type="password" size="20" maxlength="100" id="bp" name="passb" value="<%=passb%>"></td>
						</tr>
			<% } %>
					<tr>
						<td>新しいパスワード：</td>
						<td><input type="password" size="20" maxlength="100" id="np1" name="pass1" value="<%=pass1%>"></td>
					</tr>
					<tr>
						<td>新しいパスワード(確認)：</td>
						<td><input type="password" size="20" maxlength="100" id="np2" name="pass2" value="<%=pass2%>"></td>
					</tr>
				</table>
		<% try {
				if(request.getAttribute("err") != null) { %>
					<p id="err"><%=request.getAttribute("err")%></p>
		<% } }catch (Exception e2){
		}%>
		<br>
		<table>
			<tr>
				<td><input type="submit" name="submit" value="確定"></td>
				<td><input type="button" name="reset" value="リセット" onclick="blank()"></td>
				<% if(no == 2) { %>
					<td><input type="submit" name="submit" formaction="./login" value="メインメニューに戻る"></td>
				<% } %>
			</tr>
		</table>
		</form>
	</div>
</body>
</html>