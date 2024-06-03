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
	
<% if(request.getParameter("submit").equals("登録")){ %>
<form action ="./checkControl" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
        <strong>ユーザ情報の登録</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>以下の内容を登録します。よろしいですか？</p>
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
				<td align="right">名前(ふりがな)</td>
				<td align="left"><%=session.getAttribute("furigana") %></td>
			</tr>
			<tr>
				<td align="right">メールアドレス</td>
				<td align="left"><%=session.getAttribute("email") %></td>
			</tr>
			<tr>
				<td align="right">パスワード</td>
				<td align="left"><%=session.getAttribute("pass") %></td>
			</tr>
			<tr>
				<td align="right">区分</td>
				<td align="left"><%=session.getAttribute("kubun") %></td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="登録"></td>
</form>
<form action="duControl" method="post">
				<td><input type ="submit" name="submit" value="戻る" onclick="history.back()"></td>
</form>
			</tr>
		</table>
    </div>
<% } %>
	
<% if(request.getParameter("submit").equals("変更")){ %>
<form action ="./checkControl" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
        <strong>ユーザ情報の変更</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>以下の内容を変更します。よろしいですか？</p>
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
				<td align="right">名前(ふりがな)</td>
				<td align="left"><%=session.getAttribute("furigana") %></td>
			</tr>
			<tr>
				<td align="right">メールアドレス</td>
				<td align="left"><%=session.getAttribute("email") %></td>
			</tr>
			<tr>
				<td align="right">区分</td>
				<td align="left"><%=session.getAttribute("kubun") %></td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="変更"></td>
</form>
<form action="duControl" method="post">
				<td><input type ="submit" name="submit" value="戻る" onclick="history.back()"></td>
</form>
			</tr>
		</table>
    </div>
<% } %>
	
<% if(request.getParameter("submit").equals("削除")){ %>
<form action ="./checkControl" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
        <strong>ユーザ情報の削除</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>以下の内容を削除します。よろしいですか？</p>
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
				<td align="right">名前(ふりがな)</td>
				<td align="left"><%=session.getAttribute("furigana") %></td>
			</tr>
			<tr>
				<td align="right">メールアドレス</td>
				<td align="left"><%=session.getAttribute("email") %></td>
			</tr>
			<tr>
				<td align="right">パスワード</td>
				<td align="left"><%=session.getAttribute("pass") %></td>
			</tr>
			<tr>
				<td align="right">区分</td>
				<td align="left"><%=session.getAttribute("kubun") %></td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="削除"></td>
</form>
<form action="duControl" method="post">
				<td><input type ="submit" name="submit" value="戻る" onclick="history.back()"></td>
</form>
			</tr>
		</table>
    </div>
<% } %>

</body>
</html>