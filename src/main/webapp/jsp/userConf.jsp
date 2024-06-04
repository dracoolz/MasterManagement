<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" src="userConf.css">
</head>

<body>
<% String str =  request.getParameter("submit");%>
<form action ="./checkControl?type=user" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
		<strong>ユーザ情報の登録</strong>
		<div align="right">
			<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
			<a href="/first">ログアウト</a>
		</div>
		<p><%="以下の内容を"+str+"します。よろしいですか？"%></p>
		<table>
			<tr>
				<td align="right">社員番号：</td>
				<td align="left"><%=session.getAttribute("id") %></td>
			</tr>
			<tr>
				<td align="right">名前：</td>
				<td align="left"><%=session.getAttribute("name") %></td>
			</tr>
			<tr>
				<td align="right">名前(ふりがな)：</td>
				<td align="left"><%=session.getAttribute("furigana") %></td>
			</tr>
			<tr>
				<td align="right">メールアドレス：</td>
				<td align="left"><%=session.getAttribute("email") %></td>
			</tr>
			<%if(str.equals("登録")) { %>
				<tr>
					<td align="right">パスワード：</td>
					<td align="left"><%=session.getAttribute("pass") %></td>
				</tr>
			<% } %>
			<tr>
				<td align="right">区分：</td>
				<td align="left">
					<%int role = (int)session.getAttribute("kubun");%>
						<%if(role == 0) { %>
							正社員
						<%} else { %>
							アルバイト
						<%} %>
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value=<%=str%>></td>
				<%if(str.equals("削除")) {%>
					<td><input type ="submit" name="submit" formaction="./manageControl?no=1" value="戻る"></td>
				<%}else {%>
					<td><input type ="submit" name="submit" formaction="./DUControl?type=user" value="戻る"></td>
				<%} %>
			</tr>
		</table>
	</div>
</form>
</body>
</html>