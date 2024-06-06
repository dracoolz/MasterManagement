<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="bean.SalesBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品別売上詳細</title>
<% ArrayList<SalesBean> list = (ArrayList<SalesBean>) request.getAttribute("productlist"); %>
<% if (list == null) {
     list = new ArrayList<SalesBean>();
   }
	SalesBean firstBean = null;
    if (!list.isEmpty()) {
        firstBean = list.get(0);
   } %>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/productListDetail.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
        google.charts.load('current', { 'packages': ['corechart', 'bar'] });
        google.charts.setOnLoadCallback(drawCharts);

        function drawCharts() {
            drawBarChart();
            drawPieChart();
        }

        function drawBarChart() {
        	var data = google.visualization.arrayToDataTable([
        	    ['Year', '売上'],
        	    <% 
        	    for (int i = 0; i < 13; i++) { 
        	        if (i < list.size()) { 
        	    %>
        	            ['・', <%= list.get(i).getSale_amount() %>],
        	        <% } else { %>
        	            ['・', 0],
        	        <% } %>
        	    <% } %>
        	]);

            var options = {
                chart: {
                    title: "売上金額の推移",
                    titleTextStyle: {
                        color: 'black'
                    }
                }
            };

            var chart = new google.charts.Bar(document.getElementById('barChart'));

            chart.draw(data, google.charts.Bar.convertOptions(options));
        }

        function drawPieChart() {
            var data = google.visualization.arrayToDataTable([
                ['地区', '売上'],
                <% for (SalesBean bean : list) { %>
                	[, <%= bean.getSale_amount() %>],
                <% } %>
            ]);

            var options = {
                title: '地区別売上',
                titleTextStyle: {
                    color: 'black'
                }
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
            chart.draw(data, options);
        }
    </script>
</head>
<body>
	<div align="center">
		<strong id="topPage">商品別売上詳細</strong>
		<div align="right">
			<p>
				<%= "ようこそ、" + session.getAttribute("username") + "さん" %>
			</p>
			<a href="/first">ログアウト</a>
		</div>
		<div class="top-button">
			<form method="post" action="./detail?no=2">
                <input type="hidden" name="idValue" value="<%= session.getAttribute("idValue") %>">
                <button type="submit" name="switcher" value="year">年表示</button>
                <button type="submit" name="switcher" value="month">月表示</button>
            </form>
			<button type="button" onclick="location.href='manageControl?no=5'">戻る</button>
		</div>
		<!-- table -->
		<div class="table">
			<table>
				<tr>
					<th>商品ID▽</th>
					<th>商品名▽</th>
					<th>小カテゴリ▽</th>
					<th>販売単価▽</th>
					<th>仕入単価▽</th>
					<th>販売数▽</th>
					<th>売上高▽</th>
					<th>粗利▽</th>
					<th>先年度比</th>
				</tr>
				<% for (SalesBean bean : list) { %>
				<tr align="center">
					<td><%= bean.getPro_id() %></td>
					<td><%= bean.getPi_name() %></td>
					<td><%= bean.getCategory() %></td>
					<td><%= bean.getSale_price() %>円</td>
					<td><%= bean.getStock_price() %>円</td>
					<td><%= bean.getSale_amount() %></td>
					<td><%= bean.getNet_profit() %>円</td>
					<td><%= bean.getProfit() %>円</td>
					<td><%= bean.getComparison() %></td>
				</tr>
				<% } %>
			</table>
		</div>
		<div class="chart-1">
			<div id="barChart" style="width: 400px; height: 300px;"></div>
			<div>
				<table>
                    <%
                        String sessionMonth = (String) session.getAttribute("month");
                        DateTimeFormatter formatter;
                        boolean isMonthly = "month".equals(sessionMonth);
                        
                        if (isMonthly) {
                            formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
                        } else {
                            formatter = DateTimeFormatter.ofPattern("yyyy年");
                        }
                    %>
					<thead>
						<tr>
						<% if (isMonthly) { %>
							<th>月</th>
						<% } else { %>
							<th>年</th>
						<% } %>
							<th>売上</th>
						</tr>
					</thead>
					<tbody>
						<%
						LocalDate currentDate = LocalDate.now();
						%>
						<%
						if ("year".equals(request.getParameter("switcher"))) {
							// Loop for 10 years
							for (int i = 0; i < 11; i++) {
								LocalDate date = currentDate.minusYears(i);
								String formattedDate = date.format(formatter);

								Optional<SalesBean> salesForYear = list.stream()
								.filter(bean -> bean.getDate().getYear() == date.getYear())
								.findFirst();

								// Check if there's a SalesBean object for the current year
								if (salesForYear.isPresent()) {
							SalesBean salesBean = salesForYear.get();
						%>
						<tr>
							<td><%=formattedDate%></td>
							<td><%=salesBean.getSale_amount()%></td>
						</tr>
						<%
						} else {
						%>
						<tr>
							<td><%=formattedDate%></td>
							<td>0</td>
						</tr>
						<%
						}
						}
						} else { // Default to monthly display
						// Loop for 13 months
						for (int i = 0; i < 13; i++) {
						LocalDate date = currentDate.minusMonths(i);
						String formattedDate = date.format(formatter);

						Optional<SalesBean> salesForMonth = list.stream()
								.filter(bean -> bean.getDate().getYear() == date.getYear() &&
								bean.getDate().getMonth() == date.getMonth())
								.findFirst();

						// Check if there's a SalesBean object for the current month
						if (salesForMonth.isPresent()) {
						SalesBean salesBean = salesForMonth.get();
						%>
						<tr>
							<td><%=formattedDate%></td>
							<td><%=salesBean.getSale_amount()%></td>
						</tr>
						<%
						} else {
						%>
						<tr>
							<td><%=formattedDate%></td>
							<td>0</td>
						</tr>
						<%
						}
						}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
		<div class="chart-2">
			<div>
				<p>
				<h3>地区別売上</h3>
				ランキング
				</p>
				<table>
					<thead>
						<tr>
							<th>地区</th>
							<th>売上</th>
						</tr>
					</thead>
					<tbody>
						<% for (SalesBean bean : list) { %>
						<tr>
							<td><%= bean.getDistrict() %></td>
							<td><%= bean.getSale_amount() %></td>
						</tr>
						<% } %>
					</tbody>
				</table>
			</div>
			<div id="piechart" style="width: 500px; height: 300px;"></div>
		</div>
		<div class="footer_button">
			<button type="button" onclick="window.location.href='#topPage'">トップページ</button>
			<button type="button" onclick="location.href='manageControl?no=5'">戻る</button>
		</div>
	</div>
</body>
</html>
