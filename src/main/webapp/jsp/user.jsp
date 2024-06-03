<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.UserBean"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/user.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/js/jquery.tablesorter.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/css/theme.default.min.css">
</head>

<body>
<script src="../js/tableSorter.js"></script>
<%ArrayList<UserBean> userList =(ArrayList<UserBean>)request.getAttribute("list"); %>
<form action ="./DUControl?type=user" method="post">
	<div align="center">
		<div align="left">
			<p>ユーザ管理</p>
		</div>
        <strong>ユーザ情報の一覧</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <div align="left" id="search">
	        <table>
		        <tr>
			        <td>🔍<input type="text" name="searchWord"></td>
			        <td><input type="submit" name="submit" value="検索"></td> 
		        </tr>
	        </table>
        </div>
       	<div align="right">
       		<table>
       			<tr>
	       			<td><input type ="submit"  name="submit" value="登録"></td>
	       			<td><button type="button"
								onclick="location.href='./master?no=1'">戻る</td>
       			</tr>
       		</table>
       	</div>
       	<div>
       		<table border="1" style="border-collapse: collapse" class="tablesorter" id="user_table">
       		<thead>
       			<tr>
	       			<td>社員番号</td>
	       			<td>名前</td>
	       			<td>ふりがな</td>
	       			<td>メールアドレス</td>
	       			<td>区分</td>
	       			<td colspan="2"></td>
       			</tr>
       		</thead>
       		<% for(int i=0; i < userList.size(); i++) { %>
       			<tr>
	       			<td><%=userList.get(i).getEmp_id() %></td>
	       			<td><%=userList.get(i).getEmp_name() %></td>
	       			<td><%=userList.get(i).getFurigana() %></td>
	       			<td><%=userList.get(i).getEmp_email() %></td>
	       			<td><%=userList.get(i).getRole() %></td>
	       			<td><input type ="submit"  name="submit" value="変更"></td>
	       			<td><input type ="submit"  name="submit" value="削除"></td>
       			</tr>
       		<% } %>
       		</table>
       	</div>
       	<font color="red">
			<%if(request.getAttribute("errmsg1")!=null){ %>
				<%= request.getAttribute("errmsg1")%><br>
			<% } %>
			<%if(request.getAttribute("errmsg2")!=null){ %>
				<%= request.getAttribute("errmsg2")%>
			<% } %>
		</font>
		<div>
</form>
		<table>
			<tr>
				<td><a class="pagetop" href="#"><div class="pagetop__arrow"></div></a></td>
				<td><button type="button" onclick="location.href='./master?no=1'">戻る</button></td>
			</tr>
		</table>
		</div>
    </div>
</body>
</html>