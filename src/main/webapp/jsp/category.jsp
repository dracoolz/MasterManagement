<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.BigCategoryBean"%>
<%@ page import="dao.BigCategoryDao"%>
<%@ page import="bean.SmallCategoryBean"%>
<%@ page import="dao.SmallCategoryDao"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>カテゴリ管理</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/category.css">
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/js/jquery.tablesorter.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/css/theme.default.min.css">
</head>

<body>
	<script src="./js/sc_category.js"></script>
	<script src="./js/tableSorter.js"></script>
	<% ArrayList<BigCategoryBean> bcList = (ArrayList<BigCategoryBean>)request.getAttribute("bclist"); %>
	<% ArrayList<SmallCategoryBean> scList = (ArrayList<SmallCategoryBean>)request.getAttribute("sclist"); %>
	<form action="Master/DUcontrol?type=category" method="post">
		<div align="center">
			<div align="left">
				<p>カテゴリ管理</p>
			</div>
			<strong>カテゴリ情報の一覧</strong>
			<div align="right">
				<p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
				<a href="/first">ログアウト</a>
			</div>
			<div align="right">
				<table>
					<tr>
						<td><input type="submit" name="submit" value="登録"></td>
						<td><button type="button"
								onclick="location.href='./master?no=1'">戻る</td>
					</tr>
				</table>
			</div>
			<div>
				<table>
					<tr>
						<td align="right">大カテゴリ:</td>
						<td><select id="bc" name="bc">
								<option value="">選択してください</option>
								<% for(int i=0; i < bcList.size(); i++){ %>
								<option value="<%=i+1 %>"><%=bcList.get(i).getBc_category() %></option>
								<%} %>
						</select></td>
					</tr>
					<tr>
						<td align=right"">(必須)</td>
						<td></td>
					</tr>
					<tr>
						<td align="right">小カテゴリ:</td>
						<td>
							<% for(int i=0; i<=bcList.size(); i++){ %> <select id="<%=i+1 %>"
							class="scSelect">
								<% ArrayList<SmallCategoryBean> list = new ArrayList<SmallCategoryBean>(); %>
								<% SmallCategoryDao dao = new SmallCategoryDao(); %>
								<% list = dao.selectBc(i); %>
								<% for(int j=0; j<=list.size(); j++){ %>
								<option value="<%=j+1 %>"><%=list[j].getSc_category() %></option>
								<%} %>
						</select> <%} %>
						</td>
					</tr>
					<tr>
						<td><input type="submit" name="submit" value="検索"></td>
					</tr>
				</table>
			</div>
			<div>
				<table border="1" style="border-collapse: collapse"
					class="tablesorter" id="category_table">
					<thead>
						<tr>
							<td>大カテゴリID</td>
							<td>大カテゴリ名</td>
							<td>小カテゴリID</td>
							<td>小カテゴリ名</td>
							<td colspan="2"></td>
						</tr>
					</thead>
					<% for(int i=0; i < scList.size(); i++) { %>
					<tr>
						<td><%=scList.get(i).getBc_id() %></td>
						<% BigCategoryDao dao = new BigCategoryDao(); %>
						<td><%=dao.select(scList.get(i).getBc_id()).getBc_category() %>
						</td>
						<td><%=scList.get(i).getSc_id() %></td>
						<td><%=scList.get(i).getSc_category() %></td>
						<td><input type="submit" name="submit" value="変更"></td>
						<td><input type="submit" name="submit" value="削除"></td>
					</tr>
					<%} %>
				</table>
			</div>
			<font color="red"> <%if(request.getAttribute("errmsg1")!=null){ %>
				<%= request.getAttribute("errmsg1")%><br> <% } %>
			</font>
			<div>
	</form>
	<table>
		<tr>
			<td><a class="pagetop" href="#"><div class="pagetop__arrow"></div></a></td>
			<td><button type="button"
					onclick="location.href='./master?no=1'">戻る</button></td>
		</tr>
	</table>
	</div>
	</div>
>>>>>>> origin/main
</body>
</html>