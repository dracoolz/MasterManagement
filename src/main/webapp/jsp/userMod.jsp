<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" src="userMod.css">
</head>

<body>
	
<% if(request.getParameter("submit").equals("登録")){ %>
<form action ="Master/control?type=user" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
        <strong>ユーザ情報の登録</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <table>
			<tr>
				<td align="right">社員番号：</td>
				<td><%=session.getAttribute("id") %></td>
			</tr>
			<tr></tr>
			<tr>
				<td align="right">名前：</td>
				<td><input type="text" name ="name" value="<%=session.getAttribute("name") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">名前(ふりがな)：</td>
				<td><input type="text" name ="furigana" value="<%=session.getAttribute("furigana") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">メールアドレス：</td>
				<td><input type="text" name ="email" value="<%=session.getAttribute("email") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">パスワード：</td>
				<td><input type="text" name ="pass" value="<%=session.getAttribute("pass") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">区分：</td>
				<td><select name ="kubun">
				<option value="0">社員</option>
				<option value="1">アルバイト</option>
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
			<%if(request.getAttribute("errmsg4")!=null){ %>
				<%= request.getAttribute("errmsg4")%>
			<% } %>
			<%if(request.getAttribute("errmsg5")!=null){ %>
				<%= request.getAttribute("errmsg5")%><br>
			<% } %>
			<%if(request.getAttribute("errmsg6")!=null){ %>
				<%= request.getAttribute("errmsg6")%>
			<% } %>
			<%if(request.getAttribute("errmsg7")!=null){ %>
				<%= request.getAttribute("errmsg7")%>
			<% } %>
		</font>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="登録"></td>
				<td><input type ="reset" value="リセット"></td>
				<td><input type ="submit" name="submit" value="戻る"></td>

			</tr>
		</table>
    </div>
</form>
<% } %>
	
<% if(request.getParameter("submit").equals("変更")){ %>
<form action ="Master/control?type=user" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
        <strong>ユーザ情報の変更</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <table>
			<tr>
				<td align="right">社員番号：</td>
				<td><%=session.getAttribute("id") %></td>
			</tr>
			<tr></tr>
			<tr>
				<td align="right">名前：</td>
				<td><input type="text" name ="name" value="<%=session.getAttribute("name") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">名前(ふりがな)：</td>
				<td><input type="text" name ="furigana" value="<%=session.getAttribute("furigana") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">メールアドレス：</td>
				<td><input type="text" name ="email" value="<%=session.getAttribute("email") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">パスワード：</td>
				<td><input type="text" name ="pass" value="<%=session.getAttribute("pass") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
			</tr>
			<tr>
				<td align="right">区分：</td>
				<td><select name ="kubun">
				<option value="0">社員</option>
				<option value="1">アルバイト</option>
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
			<%if(request.getAttribute("errmsg4")!=null){ %>
				<%= request.getAttribute("errmsg4")%>
			<% } %>
			<%if(request.getAttribute("errmsg5")!=null){ %>
				<%= request.getAttribute("errmsg5")%><br>
			<% } %>
			<%if(request.getAttribute("errmsg6")!=null){ %>
				<%= request.getAttribute("errmsg6")%>
			<% } %>
			<%if(request.getAttribute("errmsg7")!=null){ %>
				<%= request.getAttribute("errmsg7")%>
			<% } %>
		</font>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value="変更"></td>
				<td><input type ="reset" value="リセット"></td>
				<td><input type ="submit" name="submit" value="戻る"></td>
			</tr>
		</table>
    </div>
</form>
<% } %>
</body>
</html>