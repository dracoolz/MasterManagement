<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" src="userMod.css">
</head>

<% String str = request.getParameter("submit");%>
<% int kubun = (int)session.getAttribute("role");
String initial0 = "";
String initial1 = "";
if(kubun == 0) {
		initial0 = "selected";
} else {
	initial1 = "selected";
} %>

<script>
	function blank() {
		var str = '<%= str %>';
		document.getElementById("name").value = "";
		document.getElementById("furigana").value = "";
		document.getElementById("email").value = "";
		if(str == "登録"){
			document.getElementById("pass").value = "";
		}
		document.getElementById("kubun").selectedIndex = 0;
	}
</script>
<body>
<form action ="./control?type=user" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
		<strong><%="ユーザ情報の"+str%></strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="/first">ログアウト</a>
		</div>
		<table>
			<tr>
				<td align="right">社員番号：</td>
				<td><%=session.getAttribute("id") %></td>
			</tr>
			<tr>
				<td align="right">名前：</td>
				<td><input type="text" name ="name" id="name" maxlength="100" value="<%=session.getAttribute("name") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">名前(ふりがな)：</td>
				<td><input type="text" name ="furigana" id="furigana" maxlength="190" value="<%=session.getAttribute("furigana") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">メールアドレス：</td>
				<td><input type="text" name ="email" id = "email" maxlength="100" value="<%=session.getAttribute("email") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<% if(str.equals("登録")){ %>
				<tr>
					<td align="right">パスワード：</td>
					<td><input type="text" name ="pass" id="pass" maxlength="100" value="<%=session.getAttribute("pass") %>"></td>
				</tr>
				<tr>
					<td align="right">(必須)</td>
				</tr>
			<% } %>
			<tr>
				<td align="right">区分：</td>
				<td><select name ="kubun" id="kubun">
				<option value="0" <%=initial0%>>正社員</option>
				<option value="1" <%=initial1%>>アルバイト</option>
				</select></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
		</table>
		<font color="red">
			<%if(request.getAttribute("errmsg1")!=null){ %>
				<%= request.getAttribute("errmsg1")%><br>
			<% } %>
			<%if(request.getAttribute("errmsg2")!=null){ %>
				<%= request.getAttribute("errmsg2")%>
			<% } %>
			<%if(request.getAttribute("errmsg3")!=null){ %>
				<%= request.getAttribute("errmsg3")%><br>
			<% } %>
		</font>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value=<%=str%>></td>
				<td><input type ="button" name="reset" value="リセット" onclick="blank()"></td>
				<td><input type ="submit" name="submit" formaction="./manageControl?no=1" value="戻る"></td>
			</tr>
		</table>
	</div>
</form>
</body>
</html>