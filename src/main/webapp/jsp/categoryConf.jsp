<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" src="categoryConf.css">
</head>

<body>
<% String str = request.getParameter("submit"); %>
<% if(request.getParameter("categoryType").equals("bc")){ %>
	<% if(str.equals("登録")){ %>
	<form action ="./checkControl?type=category&maker=bc" method="post">
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>大カテゴリ情報の登録</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>以下の内容を登録します。よろしいですか？</p>
	        <table>
				<tr>
					<td align="right">大カテゴリID：</td>
					<td align="left"><%=session.getAttribute("bc_id") %></td>
				</tr>
				<tr>
					<td align="right">大カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("bc_category") %></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><input type ="submit" name="submit" value="登録"></td>
	
				<%if(str.equals("削除")) {%>
					<td><input type ="submit" name="submit" formaction="./manageControl?no=2" value="戻る"></td>
				<%}else {%>
					<td><input type ="submit" name="submit" formaction="./DUControl?type=category&categoryType=bc&mod=登録" value="戻る"></td>
				<%} %>
			</tr>
			</table>
	    </div>
	 </form>
	<% } %>
		
	<% if(str.equals("変更")){ %>
	<form action ="./checkControl?type=category&maker=bc" method="post">
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>大カテゴリ情報の変更</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>以下の内容に変更します。よろしいですか？</p>
	        <table>
				<tr>
					<td align="right">大カテゴリID：</td>
					<td align="left"><%=session.getAttribute("bc_id") %></td>
				</tr>
				<tr>
					<td align="right">大カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("new_bc_category") %></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><input type ="submit" name="submit" value="変更"></td>
				<%if(str.equals("削除")) {%>
					<td><input type ="submit" name="submit" formaction="./manageControl?no=2" value="戻る"></td>
				<%}else {%>
					<td><input type ="submit" name="submit" formaction="./DUControl?type=category&categoryType=bc&mod=変更"" value="戻る"></td>
				<%} %>
			</tr>
			</table>
	    </div>
	 </form>
	<% } %>
		
	<% if(str.equals("削除")){ %>
	<form action ="./checkControl?type=category&maker=bc" method="post">
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>大カテゴリ情報の削除</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>以下の内容を削除します。よろしいですか？</p>
	        <table>
				<tr>
					<td align="right">大カテゴリID：</td>
					<td align="left"><%=session.getAttribute("bc_id") %></td>
				</tr>
				<tr>
					<td align="right">大カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("bc_category") %></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><input type ="submit" name="submit" value="削除"></td>
				<%if(str.equals("削除")) {%>
					<td><input type ="submit" name="submit" formaction="./manageControl?no=2" value="戻る"></td>
				<%}else {%>
					<td><input type ="submit" name="submit" formaction="./DUControl?type=category&categoryType=bc&mod=削除" value="戻る"></td>
				<%} %>
			</tr>
			</table>
	    </div>
	</form>
	<% } %>
<%} %>

<% if(request.getParameter("categoryType").equals("sc")){ %>
	<% if(str.equals("登録")){ %>
	<form action ="./checkControl?type=category&maker=sc" method="post">
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>小カテゴリ情報の登録</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>以下の内容を登録します。よろしいですか？</p>
	        <table>
				<tr>
					<td align="right">小カテゴリID：</td>
					<td align="left"><%=session.getAttribute("sc_id") %></td>
				</tr>
				<tr>
					<td align="right">大カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("bc_category") %></td>
				</tr>
				<tr>
					<td align="right">小カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("sc_category") %></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><input type ="submit" name="submit" value="登録"></td>
				<%if(str.equals("削除")) {%>
					<td><input type ="submit" name="submit" formaction="./manageControl?no=2" value="戻る"></td>
				<%}else {%>
					<td><input type ="submit" name="submit" formaction="./DUControl?type=category&categoryType=sc&mod=登録"" value="戻る"></td>
				<%} %>
			</tr>
			</table>
	    </div>
	</form>
	<% } %>
		
	<% if(str.equals("変更")){ %>
	<form action ="./checkControl?type=category&maker=sc" method="post">
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>小カテゴリ情報の変更</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>以下の内容に変更します。よろしいですか？</p>
	        <table>
				<tr>
					<td align="right">小カテゴリID：</td>
					<td align="left"><%=session.getAttribute("sc_id") %></td>
				</tr>
				<tr>
					<td align="right">大カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("bc_category") %></td>
				</tr>
				<tr>
					<td align="right">小カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("sc_category") %></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><input type ="submit" name="submit" value="変更"></td>
				<%if(str.equals("削除")) {%>
					<td><input type ="submit" name="submit" formaction="./manageControl?no=2" value="戻る"></td>
				<%}else {%>
					<td><input type ="submit" name="submit" formaction="./DUControl?type=category&categoryType=sc&mod=変更" value="戻る"></td>
				<%} %>
			</tr>
			</table>
	    </div>
	</form>
	<% } %>
		
	<% if(str.equals("削除")){ %>
	<form action ="./checkControl?type=category&maker=sc" method="post">
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
	        <strong>小カテゴリ情報の削除</strong>
	        <div align="right">
	            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
	            <a href="/first">ログアウト</a>
	        </div>
	        <p>以下の内容を削除します。よろしいですか？</p>
	        <table>
				<tr>
					<td align="right">小カテゴリID：</td>
					<td align="left"><%=session.getAttribute("sc_id") %></td>
				</tr>
				<tr>
					<td align="right">小カテゴリ名：</td>
					<td align="left"><%=session.getAttribute("sc_category") %></td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
					<td><input type ="submit" name="submit" value="削除"></td>
				<%if(str.equals("削除")) {%>
					<td><input type ="submit" name="submit" formaction="./manageControl?no=2" value="戻る"></td>
				<%}else {%>
					<td><input type ="submit" name="submit" formaction="./DUControl?type=category&categoryType=sc&mod=削除" value="戻る"></td>
				<%} %>
			</tr>
			</table>
	    </div>
	</form>
	<% } %>
<%} %>

</body>
</html>