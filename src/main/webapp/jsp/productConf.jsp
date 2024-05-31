<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザ管理</title>
<link rel="stylesheet" src="productConf.css">
</head>

<body>
	
<% if(request.getParameter("submit").equals("登録")){ %>
<form action ="Master/checkControl" method="post">
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong>商品情報の登録</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>以下の内容を登録します。よろしいですか？</p>
        <table>
			<tr>
				<td align="right">ダイレクト商品ID：</td>
				<td align="left"><%=session.getAttribute("pro_id") %></td>
			</tr>
			<tr>
				<td align="right">サプライヤーID：</td>
				<td align="left"><%=session.getAttribute("sp_id") %></td>
			</tr>
			<tr>
				<td align="right">商品管理番号：</td>
				<td align="left"><%=session.getAttribute("pi_id") %></td>
			</tr>
			<tr>
				<td align="right">商品名：</td>
				<td align="left"><%=session.getAttribute("pi_name") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ1(大)：</td>
				<td align="left"><%=session.getAttribute("bc_1") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ1(小)：</td>
				<td align="left"><%=session.getAttribute("sc_1") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ2(大)：</td>
				<td align="left"><%=session.getAttribute("bc_2") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ2(小)：</td>
				<td align="left"><%=session.getAttribute("sc_2") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ3(大)：</td>
				<td align="left"><%=session.getAttribute("bc_3") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ3(小)：</td>
				<td align="left"><%=session.getAttribute("sc_3") %></td>
			</tr>
			<tr>
				<td align="right">ショップ名：</td>
				<td align="left"><%=session.getAttribute("shop_name") %></td>
			</tr>
			<tr>
				<td align="right">消費者向け商品説明文：</td>
				<td align="left"><%=session.getAttribute("descr") %></td>
			</tr>
			<tr>
				<td align="right">消費者向け商品詳細：</td>
				<td align="left"><%=session.getAttribute("detail") %></td>
			</tr>
			<tr>
				<td align="right">JANコード：</td>
				<td align="left"><%=session.getAttribute("jan_code") %></td>
			</tr>
			<tr>
				<td align="right">商品管理枝番号：</td>
				<td align="left"><%=session.getAttribute("branch_no") %></td>
			</tr>
			<tr>
				<td align="right">内訳：</td>
				<td align="left"><%=session.getAttribute("itemization") %></td>
			</tr>
			<tr>
				<td align="right">参考価格種別：</td>
				<td align="left"><%=session.getAttribute("ref_type") %></td>
			</tr>
			<tr>
				<td align="right">上代価格(税抜)：</td>
				<td align="left"><%=session.getAttribute("retail_price") %></td>
			</tr>
			<tr>
				<td align="right">卸価格単価(税抜)：</td>
				<td align="left"><%=session.getAttribute("wholesale") %></td>
			</tr>
			<tr>
				<td align="right">セット毎数量：</td>
				<td align="left"><%=session.getAttribute("set_quantity") %></td>
			</tr>
			<tr>
				<td align="right">税率区分：</td>
				<td align="left"><%=session.getAttribute("tax_rate_class") %></td>
			</tr>
			<tr>
				<td align="right">出荷条件：</td>
				<td align="left"><%=session.getAttribute("shipping_term") %></td>
			</tr>
			<tr>
				<td align="right">商品画像1：</td>
				<td align="left"><%=session.getAttribute("image_1") %></td>
			</tr>
			<tr>
				<td align="right">商品画像2：</td>
				<td align="left"><%=session.getAttribute("image_2") %></td>
			</tr>
			<tr>
				<td align="right">商品画像3：</td>
				<td align="left"><%=session.getAttribute("image_3") %></td>
			</tr>
			<tr>
				<td align="right">商品画像4：</td>
				<td align="left"><%=session.getAttribute("image_4") %></td>
			</tr>
			<tr>
				<td align="right">商品画像5：</td>
				<td align="left"><%=session.getAttribute("image_5") %></td>
			</tr>
			<tr>
				<td align="right">商品画像6：</td>
				<td align="left"><%=session.getAttribute("image_6") %></td>
			</tr>
			<tr>
				<td align="right">商品画像7：</td>
				<td align="left"><%=session.getAttribute("image_7") %></td>
			</tr>
			<tr>
				<td align="right">商品画像8：</td>
				<td align="left"><%=session.getAttribute("image_8") %></td>
			</tr>
			<tr>
				<td align="right">商品画像9：</td>
				<td align="left"><%=session.getAttribute("image_9") %></td>
			</tr>
			<tr>
				<td align="right">商品画像10：</td>
				<td align="left"><%=session.getAttribute("image_10") %></td>
			</tr>
			<tr>
				<td align="right">画像転載可：</td>
				<td align="left"><%=session.getAttribute("image_permission") %></td>
			</tr>
			<tr>
				<td align="right">ネット販売可：</td>
				<td align="left"><%=session.getAttribute("sell_permission") %></td>
			</tr>
			<tr>
				<td align="right">ネットオークション可：</td>
				<td align="left"><%=session.getAttribute("auction_permission") %></td>
			</tr>
			<tr>
				<td align="right">消費者直送可：</td>
				<td align="left"><%=session.getAttribute("direct_permission") %></td>
			</tr>
			<tr>
				<td align="right">品切れ：</td>
				<td align="left"><%=session.getAttribute("out_of_stock") %></td>
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
<form action ="Master/checkControl" method="post">
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong>商品情報の変更</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>以下の内容を変更します。よろしいですか？</p>
        <table>
			<tr>
				<td align="right">ダイレクト商品ID：</td>
				<td align="left"><%=session.getAttribute("pro_id") %></td>
			</tr>
			<tr>
				<td align="right">サプライヤーID：</td>
				<td align="left"><%=session.getAttribute("sp_id") %></td>
			</tr>
			<tr>
				<td align="right">商品管理番号：</td>
				<td align="left"><%=session.getAttribute("pi_id") %></td>
			</tr>
			<tr>
				<td align="right">商品名：</td>
				<td align="left"><%=session.getAttribute("pi_name") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ1(大)：</td>
				<td align="left"><%=session.getAttribute("bc_1") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ1(小)：</td>
				<td align="left"><%=session.getAttribute("sc_1") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ2(大)：</td>
				<td align="left"><%=session.getAttribute("bc_2") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ2(小)：</td>
				<td align="left"><%=session.getAttribute("sc_2") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ3(大)：</td>
				<td align="left"><%=session.getAttribute("bc_3") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ3(小)：</td>
				<td align="left"><%=session.getAttribute("sc_3") %></td>
			</tr>
			<tr>
				<td align="right">ショップ名：</td>
				<td align="left"><%=session.getAttribute("shop_name") %></td>
			</tr>
			<tr>
				<td align="right">消費者向け商品説明文：</td>
				<td align="left"><%=session.getAttribute("descr") %></td>
			</tr>
			<tr>
				<td align="right">消費者向け商品詳細：</td>
				<td align="left"><%=session.getAttribute("detail") %></td>
			</tr>
			<tr>
				<td align="right">JANコード：</td>
				<td align="left"><%=session.getAttribute("jan_code") %></td>
			</tr>
			<tr>
				<td align="right">商品管理枝番号：</td>
				<td align="left"><%=session.getAttribute("branch_no") %></td>
			</tr>
			<tr>
				<td align="right">内訳：</td>
				<td align="left"><%=session.getAttribute("itemization") %></td>
			</tr>
			<tr>
				<td align="right">参考価格種別：</td>
				<td align="left"><%=session.getAttribute("ref_type") %></td>
			</tr>
			<tr>
				<td align="right">上代価格(税抜)：</td>
				<td align="left"><%=session.getAttribute("retail_price") %></td>
			</tr>
			<tr>
				<td align="right">卸価格単価(税抜)：</td>
				<td align="left"><%=session.getAttribute("wholesale") %></td>
			</tr>
			<tr>
				<td align="right">セット毎数量：</td>
				<td align="left"><%=session.getAttribute("set_quantity") %></td>
			</tr>
			<tr>
				<td align="right">税率区分：</td>
				<td align="left"><%=session.getAttribute("tax_rate_class") %></td>
			</tr>
			<tr>
				<td align="right">出荷条件：</td>
				<td align="left"><%=session.getAttribute("shipping_term") %></td>
			</tr>
			<tr>
				<td align="right">商品画像1：</td>
				<td align="left"><%=session.getAttribute("image_1") %></td>
			</tr>
			<tr>
				<td align="right">商品画像2：</td>
				<td align="left"><%=session.getAttribute("image_2") %></td>
			</tr>
			<tr>
				<td align="right">商品画像3：</td>
				<td align="left"><%=session.getAttribute("image_3") %></td>
			</tr>
			<tr>
				<td align="right">商品画像4：</td>
				<td align="left"><%=session.getAttribute("image_4") %></td>
			</tr>
			<tr>
				<td align="right">商品画像5：</td>
				<td align="left"><%=session.getAttribute("image_5") %></td>
			</tr>
			<tr>
				<td align="right">商品画像6：</td>
				<td align="left"><%=session.getAttribute("image_6") %></td>
			</tr>
			<tr>
				<td align="right">商品画像7：</td>
				<td align="left"><%=session.getAttribute("image_7") %></td>
			</tr>
			<tr>
				<td align="right">商品画像8：</td>
				<td align="left"><%=session.getAttribute("image_8") %></td>
			</tr>
			<tr>
				<td align="right">商品画像9：</td>
				<td align="left"><%=session.getAttribute("image_9") %></td>
			</tr>
			<tr>
				<td align="right">商品画像10：</td>
				<td align="left"><%=session.getAttribute("image_10") %></td>
			</tr>
			<tr>
				<td align="right">画像転載可：</td>
				<td align="left"><%=session.getAttribute("image_permission") %></td>
			</tr>
			<tr>
				<td align="right">ネット販売可：</td>
				<td align="left"><%=session.getAttribute("sell_permission") %></td>
			</tr>
			<tr>
				<td align="right">ネットオークション可：</td>
				<td align="left"><%=session.getAttribute("auction_permission") %></td>
			</tr>
			<tr>
				<td align="right">消費者直送可：</td>
				<td align="left"><%=session.getAttribute("direct_permission") %></td>
			</tr>
			<tr>
				<td align="right">品切れ：</td>
				<td align="left"><%=session.getAttribute("out_of_stock") %></td>
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
<form action ="Master/checkControl" method="post">
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong>商品情報の削除</strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="/first">ログアウト</a>
        </div>
        <p>以下の内容を登録します。よろしいですか？</p>
        <table>
			<tr>
				<td align="right">ダイレクト商品ID：</td>
				<td align="left"><%=session.getAttribute("pro_id") %></td>
			</tr>
			<tr>
				<td align="right">サプライヤーID：</td>
				<td align="left"><%=session.getAttribute("sp_id") %></td>
			</tr>
			<tr>
				<td align="right">商品管理番号：</td>
				<td align="left"><%=session.getAttribute("pi_id") %></td>
			</tr>
			<tr>
				<td align="right">商品名：</td>
				<td align="left"><%=session.getAttribute("pi_name") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ1(大)：</td>
				<td align="left"><%=session.getAttribute("bc_1") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ1(小)：</td>
				<td align="left"><%=session.getAttribute("sc_1") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ2(大)：</td>
				<td align="left"><%=session.getAttribute("bc_2") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ2(小)：</td>
				<td align="left"><%=session.getAttribute("sc_2") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ3(大)：</td>
				<td align="left"><%=session.getAttribute("bc_3") %></td>
			</tr>
			<tr>
				<td align="right">カテゴリ3(小)：</td>
				<td align="left"><%=session.getAttribute("sc_3") %></td>
			</tr>
			<tr>
				<td align="right">ショップ名：</td>
				<td align="left"><%=session.getAttribute("shop_name") %></td>
			</tr>
			<tr>
				<td align="right">消費者向け商品説明文：</td>
				<td align="left"><%=session.getAttribute("descr") %></td>
			</tr>
			<tr>
				<td align="right">消費者向け商品詳細：</td>
				<td align="left"><%=session.getAttribute("detail") %></td>
			</tr>
			<tr>
				<td align="right">JANコード：</td>
				<td align="left"><%=session.getAttribute("jan_code") %></td>
			</tr>
			<tr>
				<td align="right">商品管理枝番号：</td>
				<td align="left"><%=session.getAttribute("branch_no") %></td>
			</tr>
			<tr>
				<td align="right">内訳：</td>
				<td align="left"><%=session.getAttribute("itemization") %></td>
			</tr>
			<tr>
				<td align="right">参考価格種別：</td>
				<td align="left"><%=session.getAttribute("ref_type") %></td>
			</tr>
			<tr>
				<td align="right">上代価格(税抜)：</td>
				<td align="left"><%=session.getAttribute("retail_price") %></td>
			</tr>
			<tr>
				<td align="right">卸価格単価(税抜)：</td>
				<td align="left"><%=session.getAttribute("wholesale") %></td>
			</tr>
			<tr>
				<td align="right">セット毎数量：</td>
				<td align="left"><%=session.getAttribute("set_quantity") %></td>
			</tr>
			<tr>
				<td align="right">税率区分：</td>
				<td align="left"><%=session.getAttribute("tax_rate_class") %></td>
			</tr>
			<tr>
				<td align="right">出荷条件：</td>
				<td align="left"><%=session.getAttribute("shipping_term") %></td>
			</tr>
			<tr>
				<td align="right">商品画像1：</td>
				<td align="left"><%=session.getAttribute("image_1") %></td>
			</tr>
			<tr>
				<td align="right">商品画像2：</td>
				<td align="left"><%=session.getAttribute("image_2") %></td>
			</tr>
			<tr>
				<td align="right">商品画像3：</td>
				<td align="left"><%=session.getAttribute("image_3") %></td>
			</tr>
			<tr>
				<td align="right">商品画像4：</td>
				<td align="left"><%=session.getAttribute("image_4") %></td>
			</tr>
			<tr>
				<td align="right">商品画像5：</td>
				<td align="left"><%=session.getAttribute("image_5") %></td>
			</tr>
			<tr>
				<td align="right">商品画像6：</td>
				<td align="left"><%=session.getAttribute("image_6") %></td>
			</tr>
			<tr>
				<td align="right">商品画像7：</td>
				<td align="left"><%=session.getAttribute("image_7") %></td>
			</tr>
			<tr>
				<td align="right">商品画像8：</td>
				<td align="left"><%=session.getAttribute("image_8") %></td>
			</tr>
			<tr>
				<td align="right">商品画像9：</td>
				<td align="left"><%=session.getAttribute("image_9") %></td>
			</tr>
			<tr>
				<td align="right">商品画像10：</td>
				<td align="left"><%=session.getAttribute("image_10") %></td>
			</tr>
			<tr>
				<td align="right">画像転載可：</td>
				<td align="left"><%=session.getAttribute("image_permission") %></td>
			</tr>
			<tr>
				<td align="right">ネット販売可：</td>
				<td align="left"><%=session.getAttribute("sell_permission") %></td>
			</tr>
			<tr>
				<td align="right">ネットオークション可：</td>
				<td align="left"><%=session.getAttribute("auction_permission") %></td>
			</tr>
			<tr>
				<td align="right">消費者直送可：</td>
				<td align="left"><%=session.getAttribute("direct_permission") %></td>
			</tr>
			<tr>
				<td align="right">品切れ：</td>
				<td align="left"><%=session.getAttribute("out_of_stock") %></td>
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