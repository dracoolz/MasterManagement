<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.time.LocalDate"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<%@ page import="bean.SalesBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>取引先別売上詳細</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/companyListDetail.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
    	<%
		ArrayList<SalesBean> list = (ArrayList<SalesBean>) request.getAttribute("companylist");
		if (list == null) {
		    list = new ArrayList<SalesBean>();
		}
		SalesBean firstBean = null;
		if (!list.isEmpty()) {
		    firstBean = list.get(0);
		}
		%>

		<%
		// Use a LinkedHashMap to maintain the insertion order
		Map<String, Integer> aggregatedSales = new LinkedHashMap<>();

		// Aggregate sales by category
		for (int i = 0; i < list.size(); i++) {
			String category = list.get(i).getCategory();
			int saleAmount = list.get(i).getSale_amount();
			if (aggregatedSales.containsKey(category)) {
				// If the category already exists, add the current sale amount to the existing one
				aggregatedSales.put(category, aggregatedSales.get(category) + saleAmount);
			} else {
				// Otherwise, add the new category with the current sale amount
				aggregatedSales.put(category, saleAmount);
			}
		}
		%>
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
                int year = 2024;
                int month = 0; // Start from January
                for (int i = 1; i < 12; i++) { // Loop for 12 months of 2024
                    int currentMonth = month + i;
                    if (currentMonth > 12) { // Adjust if the month index goes beyond December
                        currentMonth -= 12;
                        year = 2025; // Increment the year if month exceeds December
                    }

                    // Safely access the list and its elements
                    int saleAmount = 0; // Default sale amount
                    if (i < list.size() && list.get(i) != null) { // Check if index is within the list bounds and element is not null
                        saleAmount = list.get(i).getSale_amount(); // Get sale amount safely
                    }
                %>
                    ['<%= year + "-" + (currentMonth < 10 ? "0" + currentMonth : currentMonth) %>', <%= saleAmount %>],
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
                ['カテゴリ', '売上'],
                <% // Iterate over the map to display the aggregated results
                for (Map.Entry<String, Integer> entry : aggregatedSales.entrySet()) {
                %>
                    [, <%= entry.getValue() %>],
                <% } %>
            ]);

            var options = {
                title: 'カテゴリ別売上',
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

    <div align="center">
        <strong id="topPage">取引先別売上詳細</strong>
        <div align="right">
            <p>
                <%= "ようこそ、" + session.getAttribute("username") + "さん" %>
            </p>
            <a href="./first">ログアウト</a>
        </div>
        <div class="top-button">
            <form method="post" action="./detail?no=1">
                <input type="hidden" name="idValue" value="<%= session.getAttribute("idValue") %>">
                <button type="submit" name="switcher" value="year">年表示</button>
                <button type="submit" name="switcher" value="month">月表示</button>
            </form>
            <button type="button" onclick="location.href='manageControl?no=4'">戻る</button>
        </div>
        <!-- table -->
        <div class="table">
            <table>
                <tr>
                    <th>取引先ID▽</th>
                    <th>取引先名▽</th>
                    <th>売上(万円)▽</th>
                    <th>先年度比(%)▽</th>
                </tr>
                <% for (int i = 0; i < list.size(); i++) { %>
                <tr>
                    <td><%= list.get(i).getCus_id() %></td>
                    <td><%= list.get(i).getCus_name() %></td>
                    <td><%= list.get(i).getSale_amount() %></td>
                    <td><%= list.get(i).getGross_profit() %></td>
                </tr>
                <% } %>
            </table>
        </div>
        <% if (firstBean != null) { %>
        <!-- 取引先別詳細情報 -->
        <div align="left" class="company_info">
            <h3><%= firstBean.getCus_name() %></h3>
            <p>取引先ＩＤ: <%= firstBean.getCus_id() %></p>
            <p>
                <% 
                LocalDate firstDate = firstBean.getDate();
                String formattedDate = firstDate.format(formatter);
                %>
                <%= formattedDate %>の売上: <%= firstBean.getSale_amount() %>万円
            </p>
            <p>先年度比: <%= firstBean.getGross_profit() %>%</p>
        </div>
        <% } %>
        <div class="chart-1">
            <div id="barChart" style="width: 400px; height: 300px;"></div>
            <div>
                <table>
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
                <h3>購入した商品カテゴリの売上</h3>
                ランキング
                </p>
                <table>
                    <thead>
                        <tr>
                            <th>カテゴリ</th>
                            <th>売上</th>
                        </tr>
                    </thead>
					<tbody>
						<%
						// Iterate over the map to display the aggregated results
						for (Map.Entry<String, Integer> entry : aggregatedSales.entrySet()) {
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
            <button type="button" onclick="location.href='manageControl?no=4'">戻る</button>
        </div>
    </div>
</body>
</html>
