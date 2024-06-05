<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.SalesBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品売上一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/productList.css">
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/js/jquery.tablesorter.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.tablesorter/2.31.0/css/theme.default.min.css">
<script>
    function incrementMonth() {
        const monthSelect = document.getElementById("month");
        let currentMonth = parseInt(monthSelect.value);
        if (currentMonth < 12) {
            monthSelect.value = currentMonth + 1;
        } else {
            monthSelect.value = 1;
            incrementYear();
        }
    }

    function decrementMonth() {
        const monthSelect = document.getElementById("month");
        let currentMonth = parseInt(monthSelect.value);
        if (currentMonth > 1) {
            monthSelect.value = currentMonth - 1;
        } else {
            monthSelect.value = 12;
            decrementYear();
        }
    }

    function incrementYear() {
        const yearSelect = document.getElementById("year");
        let currentYear = parseInt(yearSelect.value);
        yearSelect.value = currentYear + 1;
    }

    function decrementYear() {
        const yearSelect = document.getElementById("year");
        let currentYear = parseInt(yearSelect.value);
        yearSelect.value = currentYear - 1;
    }

    $(document).ready(function() {
        $("#productlist_table").tablesorter({
            headers: {
                9: { sorter: false }
            }
        });
    });
</script>
</head>
<body>
	<% ArrayList<SalesBean> list = (ArrayList<SalesBean>) request.getAttribute("productlist"); 
	int pageSize = 100; // Number of items per page
    int totalPages = (int) Math.ceil((double) list.size() / pageSize);
    String pageParam = request.getParameter("page");
    int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
    int startIdx = (currentPage - 1) * pageSize;
    int endIdx = Math.min(startIdx + pageSize, list.size());
    List<SalesBean> currentPageList = list.subList(startIdx, endIdx);
	%>
	<div align="center">
		<strong id="topPage">商品別売上一覧</strong>
		<div align="right">
			<p>
				<%="ようこそ、" + session.getAttribute("username") + "さん"%>
			</p>
			<a href="./first">ログアウト</a>
		</div>
		<a href="javascript:decrementMonth();">
			<div class="arrow-left"></div>
		</a>
		<!-- date -->
		<select name="year" id="year">
			<option value="2024">2024</option>
			<option value="2023">2023</option>
			<option value="2022">2022</option>
			<option value="2021">2021</option>
		</select> <select name="month" id="month">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="5">5</option>
			<option value="6">6</option>
			<option value="7">7</option>
			<option value="8">8</option>
			<option value="9">9</option>
			<option value="10">10</option>
			<option value="11">11</option>
			<option value="12">12</option>
		</select> <a href="javascript:incrementMonth();">
			<div class="arrow-right"></div>
		</a> <br>
		<div class="form-container">
			<div class="form-group">
				<label for="product_name">商品名：</label> <input type="text"
					id="product_name" size="20">
			</div>
			<div class="form-group">
				<label for="big">大カテゴリ：</label> <select name="big" id="big">
					<option value="">選択してください</option>
					<option value="big_3">big_3</option>
					<option value="big_2">big_2</option>
					<option value="big_1">big_1</option>
				</select>
			</div>
			<div class="form-group">
				<label for="small">小カテゴリ：</label> <select name="small" id="small">
					<option value="">選択してください</option>
					<option value="small_3">small_3</option>
					<option value="small_2">small_2</option>
					<option value="small_1">small_1</option>
				</select>
			</div>
		</div>
		<button type="button">検索</button>
		<!-- table -->
		<div class="table">
			<form method="post" action="./detail?no=2">
				<table border="1" style="border-collapse: collapse" class="tablesorter" id="productlist_table">
					<thead>
						<tr align="center">
							<th>商品ID</th>
							<th>商品名</th>
							<th>カテゴリ</th>
							<th>販売単価</th>
							<th>仕入単価</th>
							<th>販売数</th>
							<th>売上高</th>
							<th>粗利</th>
							<th>先年度比(%)</th>
							<th></th>
						</tr>
					</thead>
					<% for (int i = 0; i < list.size(); i++) { %>
					<tr align="center">
						<td><%= list.get(i).getPro_id() %></td>
						<td style="word-break: break-all;"><%= list.get(i).getPi_name() %></td>
						<td><%= list.get(i).getCategory() %></td>
						<td><%= list.get(i).getSale_price() %>円</td>
						<td><%= list.get(i).getStock_price() %>円</td>
						<td><%= list.get(i).getSale_amount() %></td>
						<td><%= list.get(i).getNet_profit() %>円</td>
						<td><%= list.get(i).getProfit() %>円</td>
						<td><%= list.get(i).getComparison() %></td>
						<td>
							<button type="submit" name="idValue"
								value="<%= list.get(i).getPro_id() %>">詳細</button>
						</td>
					</tr>
					<% } %>
				</table>
			</form>
		</div>
		<%-- Pagination --%>
		<%
		if (totalPages > 1) {
		%>
		<div class="pagination">
			<%
			if (currentPage > 1) {
			%>
			<a href="?page=1">First</a> <a href="?page=<%=currentPage - 1%>">Previous</a>
			<%
			}
			%>
			<%
			for (int i = 1; i <= totalPages; i++) {
			%>
			<%
			if (i == currentPage) {
			%>
			<span class="current-page"><%=i%></span>
			<%
			} else {
			%>
			<a href="?page=<%=i%>"><%=i%></a>
			<%
			}
			%>
			<%
			}
			%>
			<%
			if (currentPage < totalPages) {
			%>
			<a href="?page=<%=currentPage + 1%>">Next</a> <a
				href="?page=<%=totalPages%>">Last</a>
			<%
			}
			%>
		</div>
		<%
		}
		%>
		<div class="footer_button">
			<button type="button" onclick="window.location.href='#topPage'">トップページ</button>
			<button type="button" onclick="location.href='./master?no=1'">戻る</button>
		</div>
	</div>
</body>
</html>
