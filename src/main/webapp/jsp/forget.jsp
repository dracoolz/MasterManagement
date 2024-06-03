<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>パスワードリセット</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<% String id = "";
try {
	id = (String)request.getAttribute("id");
	if(id.length() == 0) {
		id = "";
	}
} catch (Exception e) {
	id = "";
}%>

<script>
	function blank() {
		document.getElementById("id").value = "";
	}
</script>

<body>
	<div align="center">
		<strong>パスワードを忘れた場合</strong>
	</div>
	<form action="./mail" method="post">
		<div align="center">
			<p>社員番号を入力してください</p>
			<p>登録されているメールアドレスにリンクを送信します</p>
			<table>
				<tr>
					<td>社員番号 :</td>
					<td><input type="text" size="20" id="id" name="id" maxlength="20" value=<%=id%>></td>
				</tr>
			</table>
			<br>
			<table>
				<% try {
					if(request.getAttribute("err") != null) { %>
						<p id="err"><%=request.getAttribute("err")%></p>
				<% } }catch (Exception e2){
				}%>
				<tr>
					<td><input type="submit" name="submit" value="メールを送信"></td>
					<td><input type="button" name="reset" value="リセット" onclick="blank()"></td>
	</form>
					<td><input type="button" name="back"
						onclick="location.href='./first'" value="戻る"></td>
				<tr>
			</table>
		</div>
</body>
</html>