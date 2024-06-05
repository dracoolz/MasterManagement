<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.*"%>
<%@ page import="bean.BigCategoryBean"%>
<html>
<head>
<meta charset="UTF-8">
<title>商品管理</title>
<link rel="stylesheet" src="productMod.css">
</head>

<body>
<script src="../js/sc_category.js"></script>
<% ArrayList<BigCategoryBean> bcList = (ArrayList<BigCategoryBean>)session.getAttribute("bclist"); %>
<% String str = request.getParameter("submit"); %>
<form action ="./control?type=product" method="post">
	<div align="center">
		<div align="left">
			<p>商品管理</p>
		</div>
        <strong><%="商品情報の"+str%></strong>
        <div align="right">
            <p><%="ようこそ、"+session.getAttribute("username")+"さん" %></p>
            <a href="./first">ログアウト</a>
            <table>
            <tr>
            <td><input type ="submit" name="submit" value="登録"></td>
            <td><input type ="submit" name="submit" formaction="./manageControl?no=3" value="戻る"></td>
            </tr>
            </table>
        </div>
        <p><%="以下の商品を"+str+"します"%></p>
        <table>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">ダイレクト商品ID</td>
				<td align="left">：<input type="text" name ="pro_id" value="<%=session.getAttribute("pro_id") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg3")!=null){ %>
						<%= request.getAttribute("errmsg3")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">サプライヤーID</td>
				<td align="left">:<input type="text" name ="sp_id" value="<%=session.getAttribute("sp_id") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg2")!=null){ %>
						<%= request.getAttribute("errmsg2")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">商品管理番号</td>
				<td align="left">:<input type="text" name ="pi_id" value="<%=session.getAttribute("pi_id") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg2")!=null){ %>
						<%= request.getAttribute("errmsg2")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">商品名</td>
				<td align="left">:<input type="text" name ="pi_name" value="<%=session.getAttribute("pi_name") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg2")!=null){ %>
						<%= request.getAttribute("errmsg2")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">カテゴリ1(大)</td>
				<td>:<select id ="bc1" name = "bc_id_1">
				<option value=""></option>
				<% for(int i=0; i < bcList.size(); i++){ %>
				<option value="<%=i+1 %>"><%=bcList.get(i).getBc_category() %></option>
				<%} %>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">カテゴリ1(小)</td>
				<td>:<select id ="sc1" name = "sc_id_1">
				<option value=""></option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">カテゴリ2(大)</td>
				<td>:<select id ="bc2" name = "bc_id_2">
				<option value=""></option>
				<% for(int i=0; i < bcList.size(); i++){ %>
				<option value="<%=i+1 %>"><%=bcList.get(i).getBc_category() %></option>
				<%} %>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">カテゴリ2(小)</td>
				<td>:<select id ="sc2" name = "sc_id_2">
				<option value=""></option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">カテゴリ3(大)</td>
				<td>:<select id ="bc3" name = "bc_id_3">
				<option value=""></option>
				<% for(int i=0; i < bcList.size(); i++){ %>
				<option value="<%=i+1 %>"><%=bcList.get(i).getBc_category() %></option>
				<%} %>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">カテゴリ3(小)</td>
				<td>:<select id ="sc3" name = "sc_id_3">
				<option value=""></option>
				</select></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">ショップ名</td>
				<td align="left">:<input type="text" name ="shop_name" value="<%=session.getAttribute("shop_name") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">消費者向け商品説明文</td>
				<td align="left">:<input type="text" name ="descr" value="<%=session.getAttribute("descr") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">消費者向け商品詳細</td>
				<td align="left">:<input type="text" name ="detail" value="<%=session.getAttribute("detail") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">JANコード</td>
				<td align="left">:<input type="text" name ="jan_code" value="<%=session.getAttribute("jan_code") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品管理枝番号</td>
				<td align="left">:<input type="text" name ="branch_no" value="<%=session.getAttribute("branch_no") %>"></td>
				<td align="left"><font color="red">
				</font></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">内訳</td>
				<td align="left">:<input type="text" name ="itemization" value="<%=session.getAttribute("itemization") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">参考価格種別</td>
				<td align="left">:<input type="text" name ="ref_type" value="<%=session.getAttribute("ref_type") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg2")!=null){ %>
						<%= request.getAttribute("errmsg2")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">上代価格(税抜)</td>
				<td align="left">:<input type="text" name ="retail_price" value="<%=session.getAttribute("retail_price") %>"></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">卸価格単価(税抜)</td>
				<td align="left">:<input type="text" name ="wholesale" value="<%=session.getAttribute("wholesale") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg2")!=null){ %>
						<%= request.getAttribute("errmsg2")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">セット毎数量</td>
				<td align="left">:<input type="text" name ="set_quantity" value="<%=session.getAttribute("set_quantity") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg2")!=null){ %>
						<%= request.getAttribute("errmsg2")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td align="right">(必須)</td>
				<td align="left">税率区分</td>
				<td align="left">:<input type="text" name ="tax_rate_class" value="<%=session.getAttribute("tax_rate_class") %>"></td>
				<td align="left"><font color="red">
					<%if(request.getAttribute("errmsg1")!=null){ %>
						<%= request.getAttribute("errmsg1")%><br>
					<% } %>
					<%if(request.getAttribute("errmsg2")!=null){ %>
						<%= request.getAttribute("errmsg2")%>
					<% } %>
				</font></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">出荷条件</td>
				<td align="left">:<input type="text" name ="shipping_term" value="<%=session.getAttribute("shipping_term") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像1</td>
				<td align="left">:<input type="text" name ="image_1" value="<%=session.getAttribute("image_1") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像2</td>
				<td align="left">:<input type="text" name ="image_2" value="<%=session.getAttribute("image_2") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像3</td>
				<td align="left">:<input type="text" name ="image_3" value="<%=session.getAttribute("image_3") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像4</td>
				<td align="left">:<input type="text" name ="image_4" value="<%=session.getAttribute("image_4") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像5</td>
				<td align="left">:<input type="text" name ="image_5" value="<%=session.getAttribute("image_5") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像6</td>
				<td align="left">:<input type="text" name ="image_6" value="<%=session.getAttribute("image_6") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像7</td>
				<td align="left">:<input type="text" name ="image_7" value="<%=session.getAttribute("image_7") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像8</td>
				<td align="left">:<input type="text" name ="image_8" value="<%=session.getAttribute("image_8") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像9</td>
				<td align="left">:<input type="text" name ="image_9" value="<%=session.getAttribute("image_9") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">商品画像10</td>
				<td align="left">:<input type="text" name ="image_10" value="<%=session.getAttribute("image_10") %>"></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">画像転載可</td>
				<td align="left">:<select name ="image_permission">
				<option value="N">N</option>
				<option value="Y">Y</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">ネット販売可</td>
				<td align="left">:<select name ="sell_permission">
				<option value="N">N</option>
				<option value="Y">Y</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">ネットオークション可</td>
				<td align="left">:<select name ="auction_permission">
				<option value="N">N</option>
				<option value="Y">Y</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">消費者直送可</td>
				<td align="left">:<select name ="direct_permission">
				<option value="N">N</option>
				<option value="Y">Y</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td align="left">品切れ</td>
				<td align="left">:<select name ="out_of_stock">
				<option value="N">N</option>
				<option value="Y" selected>Y</option>
				</select></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type ="submit" name="submit" value=<%=str%>></td>
				<td><input type ="reset" value="リセット"></td>
</form>
				<form action="./manageControl?no=3" method="post">
					<td><input type ="submit" name="submit" value="戻る"></td>
				</form>
			</tr>
		</table>
	</div>
</body>
</html>
