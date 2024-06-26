<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="bean.SalesBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先別売上一覧</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/companyList.css">
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
        $("#company_table").tablesorter({
            headers: {
                4: { sorter: false }
            }
        });
    });
</script>
</head>
<body>
    <% ArrayList<SalesBean> list = (ArrayList<SalesBean>) request.getAttribute("companylist");
    int pageSize = 100; // Number of items per page
    int totalPages = (int) Math.ceil((double) list.size() / pageSize);
    String pageParam = request.getParameter("page");
    int currentPage = (pageParam != null && !pageParam.isEmpty()) ? Integer.parseInt(pageParam) : 1;
    int startIdx = (currentPage - 1) * pageSize;
    int endIdx = Math.min(startIdx + pageSize, list.size());
    List<SalesBean> currentPageList = list.subList(startIdx, endIdx);
    %>
	<div align="center">
		<strong id="topPage">取引先別売上一覧</strong>
		<div align="right">
			<p>
				<%="ようこそ、"+session.getAttribute("username")+"さん" %>
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
		</a> <br> <br>
		<form method="post" action="./list">
			<div class="form-container">
				<div class="form-group">
					<label for="customer_name">取引先名：</label> <input type="text"
						name="customer_name" id="customer_name" size="20">
				</div>
				<div class="form-group">
					<label for="contact_name">担当者：</label> <input type="text"
						name="contact_name" id="contact_name" size="20">
				</div>
				<div class="form-group">
					<label for="district">県：</label> <input type="text" name="district"
						id="district" size="20">
				</div>
			</div>
			<button type="submit" value="検索">検索</button>
		</form>
		<div class="table">
			<table border="1" style="border-collapse: collapse" class="tablesorter" id="company_table">
				<thead>
					<tr align="center">
						<th>取引先ID</th>
						<th>取引先名</th>
						<th>売上(万円)</th>
						<th>先年度比(%)</th>
						<th></th>
					</tr>
				</thead>
				<% for (int i = 0; i < list.size(); i++) { %>
				<tr align="center">
					<td><%= list.get(i).getCus_id() %></td>
					<td><%= list.get(i).getCus_name() %></td>
					<td><%= list.get(i).getSale_amount() %></td>
					<td><%= list.get(i).getGross_profit() %></td>
					<td>
						<form method="post" action="./detail?no=1">
							<button type="submit" name="idValue" value="<%= list.get(i).getCus_id() %>">詳細</button>
						</form>
					</td>
				</tr>
				<% } %>
			</table>
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
