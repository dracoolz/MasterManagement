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
<% ArrayList<SalesBean> list = (ArrayList<SalesBean>) request.getAttribute("productlist");
   if (list == null) {
       list = new ArrayList<SalesBean>();
   }
   SalesBean firstBean = null;
   if (!list.isEmpty()) {
       firstBean = list.get(0);
   } %>

<%
// Initialize a map to hold aggregated sales by district
				Map<String, Integer> districtSales = new LinkedHashMap<>();

				// Aggregate sales by district from the list
				for (SalesBean bean : list) {
					String district = bean.getDistrict();
					int saleAmount = bean.getSale_amount();
					if (districtSales.containsKey(district)) {
						// Add current sale amount to the existing amount for the district
						districtSales.put(district, districtSales.get(district) + saleAmount);
					} else {
						// Initialize this district in the map with the current sale amount
						districtSales.put(district, saleAmount);
					}
				}
%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/productListDetail.css">
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawCharts);

    function drawCharts() {
        drawBarChart();
        drawPieChart();
    }

    function drawBarChart() {
        var data = google.visualization.arrayToDataTable([
            ['Year-Month', '売上'],
            <% 
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM"); // Assuming monthly format

            if ("year".equals(request.getParameter("switcher"))) {
                formatter = DateTimeFormatter.ofPattern("yyyy"); // Change formatter for yearly
                for (int i = 0; i < 11; i++) { // Adjust for 10 years if yearly
                    LocalDate date = currentDate.minusYears(i);
                    Optional<SalesBean> salesForYear = list.stream()
                        .filter(bean -> bean.getDate() != null && bean.getDate().getYear() == date.getYear())
                        .findFirst();
                    int saleAmount = salesForYear.map(SalesBean::getSale_amount).orElse(0);
            %>
                    ['<%= date.format(formatter) %>', <%= saleAmount %>],
            <% 
                }
            } else {
                for (int i = 0; i < 13; i++) { // Adjust for 13 months if monthly
                    LocalDate date = currentDate.minusMonths(i);
                    Optional<SalesBean> salesForMonth = list.stream()
                        .filter(bean -> bean.getDate() != null && bean.getDate().getYear() == date.getYear() && bean.getDate().getMonthValue() == date.getMonthValue())
                        .findFirst();
                    int saleAmount = salesForMonth.map(SalesBean::getSale_amount).orElse(0);
            %>
                    ['<%= date.format(formatter) %>', <%= saleAmount %>],
            <% 
                }
            }
            %>
        ]);


        var options = {
            chart: {
                title: '売上金額の推移',
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
            <% // Iterate over the map to display the aggregated results
                for (Map.Entry<String, Integer> entry : districtSales.entrySet()) {
            %>
                [, <%= entry.getValue() %>],
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
				<input type="hidden" name="idValue"
					value="<%= session.getAttribute("idValue") %>">
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
						<%
						// Iterate over the districtSales map to display the aggregated results
						for (Map.Entry<String, Integer> entry : districtSales.entrySet()) {
						%>
						<tr>
							<td><%=entry.getKey()%></td>
							<td><%=entry.getValue()%></td>
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
