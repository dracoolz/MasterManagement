package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControlServlet extends HttpServlet {

	static final long serialVersionUID = 1L;

	public void doGet (HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
			doPost( request, response );
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//セッションの取得
		HttpSession session = request.getSession(true);
		
		String forward = null;
		
		if(request.getParameter("type").equals("user")) {
			String status = request.getParameter("submit");
			if(status.equals("戻る")){
				forward="./manageControl?no=1";
			} else if(request.getParameter("submit").equals("登録")){
				String name = request.getParameter("name");
				String furigana = request.getParameter("furigana");
				String email = request.getParameter("email");
				String pass = request.getParameter("pass");
				int kubun = Integer.parseInt(request.getParameter("kubun"));
				String[] item = {name,furigana,email,pass,String.valueOf(kubun)};
				String[] necItem = {name,furigana,email,pass,String.valueOf(kubun)};
				String[] itemName = {"ユーザ名","ふりがな","メールアドレス","パスワード","区分"};
				String[] necItemName = {"ユーザ名","ふりがな","メールアドレス","パスワード","区分"};
				
				Errcheck err = new Errcheck();
				String errmsg1=err.inputCheck(item,necItem,itemName,necItemName);
				String errmsg2=err.fullWidthCheck(furigana);
				String errmsg3=err.emailCheck(email);
				
				if(errmsg1 == null && errmsg2 == null && errmsg3 == null){
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("pass", pass);
					session.setAttribute("kubun", kubun);
					forward="/jsp/userConf.jsp?submit=登録";
				} else {
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("pass", pass);
					session.setAttribute("kubun", kubun);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					forward="/jsp/userMod.jsp?submit=登録";
				}
			 } else {
				String name = request.getParameter("name");
				String furigana = request.getParameter("furigana");
				String email = request.getParameter("email");
				int kubun = Integer.parseInt(request.getParameter("kubun"));
				String[] item = {name,furigana,email,String.valueOf(kubun)};
				String[] necItem = {name,furigana,email,String.valueOf(kubun)};
				String[] itemName = {"ユーザ名","ふりがな","メールアドレス","区分"};
				String[] necItemName = {"ユーザ名","ふりがな","メールアドレス","区分"};
				
				Errcheck err = new Errcheck();
				String errmsg1=err.inputCheck(item,necItem,itemName,necItemName);
				String errmsg2=err.fullWidthCheck(furigana);
				String errmsg3=err.emailCheck(email);
				
				if(errmsg1==null && errmsg2==null && errmsg3==null){
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("kubun", kubun);
					forward="/jsp/userConf.jsp?submit=変更";
				}else{
					session.setAttribute("name", name);
					session.setAttribute("furigana", furigana);
					session.setAttribute("email", email);
					session.setAttribute("kubun", kubun);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					forward="/jsp/userMod.jsp?submit=変更";
				}
			 }
		}
		
		
		if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("bc")) {
			
			
			if(request.getParameter("submit").equals("登録")){
				String id = request.getParameter("bc_id");
				String name = request.getParameter("bc_category");
				String[] item = {id,name};
				String[] necItem = {id,name};
				String[] itemName = {"大カテゴリ番号","大カテゴリ名"};
				String[] necItemName = {"大カテゴリ番号","大カテゴリ名"};
				
				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(item,necItem,itemName,necItemName)==null && err.idExistCheck(id,"bc")==null){
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					forward="/jsp/categoryConf.jsp?submit=登録";
				}else{
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=登録";
				}

				forward="/jsp/categoryConf.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				String id = request.getParameter("bc_id");
				String name = request.getParameter("bc_category");
				String[] item = {id,name};
				String[] necItem = {id,name};
				String[] itemName = {"大カテゴリ番号","大カテゴリ名"};
				String[] necItemName = {"大カテゴリ番号","大カテゴリ名"};

				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(item,necItem,itemName,necItemName)==null && err.idExistCheck(id,"bc")==null){
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					forward="/jsp/categoryConf.jsp?submit=変更";
				}else{
					session.setAttribute("bc_id", id);
					session.setAttribute("bc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=変更";
				}

				forward="/jsp/categoryConf.jsp?submit=変更";

			 }


			if(request.getParameter("submit").equals("戻る")){
				forward="./manageControl?no=2";
			}
	    }
		
		
		if(request.getParameter("type").equals("category") && request.getParameter("maker").equals("sc")) {
			
			
			if(request.getParameter("submit").equals("登録")){

				String sc_id = request.getParameter("sc_id");
				String bc_id = request.getParameter("bc_id");
				String name = request.getParameter("sc_category");
				String[] item = {sc_id,bc_id,name};
				String[] necItem = {sc_id,bc_id,name};
				String[] itemName = {"小カテゴリ番号","大カテゴリ番号","小カテゴリ名"};
				String[] necItemName = {"小カテゴリ番号","大カテゴリ番号","小カテゴリ名"};
				
				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(item,necItem,itemName,necItemName)==null && err.idExistCheck(sc_id,"bc")==null){
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					forward="/jsp/categoryConf.jsp?submit=登録";
				}else{
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=登録";
				}

				forward="/jsp/categoryConf.jsp?submit=登録";

			 }
			
			if(request.getParameter("submit").equals("変更")){

				String sc_id = request.getParameter("sc_id");
				String bc_id = request.getParameter("bc_id");
				String name = request.getParameter("sc_category");
				String[] item = {sc_id,bc_id,name};
				String[] necItem = {sc_id,bc_id,name};
				String[] itemName = {"小カテゴリ番号","大カテゴリ番号","小カテゴリ名"};
				String[] necItemName = {"小カテゴリ番号","大カテゴリ番号","小カテゴリ名"};

				String errmsg1=null;
				String errmsg2=null;
				String errmsg3=null;
				String errmsg4=null;
				Errcheck err = new Errcheck();
				
				if(err.inputCheck(item,necItem,itemName,necItemName)==null && err.idExistCheck(sc_id,"bc")==null){
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					forward="/jsp/categoryConf.jsp?submit=変更";
				}else{
					session.setAttribute("sc_id", sc_id);
					session.setAttribute("bc_id", bc_id);
					session.setAttribute("sc_category", name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					request.setAttribute("errmsg4", errmsg4);
					forward="/jsp/categoryMod.jsp?submit=変更";
				}

				forward="/jsp/categoryConf.jsp?submit=変更";

			 }


			if(request.getParameter("submit").equals("戻る")){
				forward="./manageControl?no=2";
			}
	    }
		
		
		if(request.getParameter("type").equals("product")) {
			
			if(request.getParameter("submit").equals("登録")){
				
				String pro_id = request.getParameter("pro_id");
				String sp_id = request.getParameter("sp_id");
				String pi_id = request.getParameter("pi_id");
				String pd_id = request.getParameter("pd_id");
				String images_id = request.getParameter("images_id");
				String itemization = request.getParameter("itemization");
				String wholesale = request.getParameter("wholesale");
				String set_quantity = request.getParameter("set_quantity");
				String pi_name = request.getParameter("pi_name");
				String jan_code = request.getParameter("jan_code");
				String branch_no = request.getParameter("branch_no");
				String ref_type = request.getParameter("ref_type");
				String retail_price = request.getParameter("retail_price");
				String tax_rate_class = request.getParameter("tax_rate_class");
				String shipping_term = request.getParameter("shipping_term");
				String image_permission = request.getParameter("image_permission");
				String sell_permission = request.getParameter("sell_permission");
				String auction_permission = request.getParameter("auction_permission");
				String direct_permission = request.getParameter("direct_permission");
				String out_of_stock = request.getParameter("out_of_stock");
				String bc_category_1 = request.getParameter("bc_id_1");
				String sc_category_1 = request.getParameter("sc_id_1");
				String bc_category_2 = request.getParameter("bc_id_2");
				String sc_category_2 = request.getParameter("sc_id_2");
				String bc_category_3 = request.getParameter("bc_id_3");
				String sc_category_3 = request.getParameter("sc_id_3");
				String descr = request.getParameter("descr");
				String detail = request.getParameter("detail");
				String image_1 = request.getParameter("image_1");
				String image_2 = request.getParameter("image_2");
				String image_3 = request.getParameter("image_3");
				String image_4 = request.getParameter("image_4");
				String image_5 = request.getParameter("image_5");
				String image_6 = request.getParameter("image_6");
				String image_7 = request.getParameter("image_7");
				String image_8 = request.getParameter("image_8");
				String image_9 = request.getParameter("image_9");
				String image_10 = request.getParameter("image_10");
				String shop_name = request.getParameter("shop_name");
				String[] item = {pro_id,sp_id,pi_id,pi_name,bc_category_1,sc_category_1,bc_category_2,sc_category_2,bc_category_3,sc_category_3,shop_name,descr,detail,jan_code,branch_no,itemization,ref_type,retail_price,wholesale,set_quantity,tax_rate_class,shipping_term,image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10,image_permission,sell_permission,auction_permission,direct_permission,out_of_stock};
				String[] necItem = {pro_id,sp_id,pi_id,pi_name,shop_name,ref_type,wholesale,set_quantity,tax_rate_class};
				String[] numItem = {sp_id,wholesale,set_quantity,retail_price,tax_rate_class};
				String[] itemName = {"ダイレクト商品ID","サプライヤーID","商品管理番号","商品名","カテゴリ1(大)","カテゴリ1(小)","カテゴリ2(大)","カテゴリ2(小)","カテゴリ3(大)","カテゴリ3(小)","ショップ名","消費者向け商品説明文","消費者向け商品詳細","JANコード","商品管理枝番号","内訳","参考価格種別","上代価格","卸価格単価","セット毎数量","税率区分","出荷条件","商品画像1","商品画像2","商品画像3","商品画像4","商品画像5","商品画像6","商品画像7","商品画像8","商品画像9","商品画像10","画像転載可","ネット販売可","ネットオークション可","消費者直送可","品切れ"};
				String[] necItemName = {"ダイレクト商品ID","サプライヤーID","商品管理番号","商品名","ショップ名","参考価格種別","卸価格単価","セット毎数量","税金区分"};
				String[] necNumItem = {"サプライヤー番号","卸価格単価","セット毎数量","上代価格","税金区分"};
				
				Errcheck err = new Errcheck();
				String errmsg1=err.inputCheck(item,necItem,itemName,necItemName);
				String errmsg2=err.numberCheck(numItem,necNumItem);
				String errmsg3=err.idExistCheck(pro_id, "product");
				
				
				if(errmsg1 == null && errmsg2 == null && errmsg3 == null){
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("image_1",image_1);
					session.setAttribute("image_2",image_2);
					session.setAttribute("shop_name",shop_name);
					forward="/jsp/productConf.jsp?submit=登録";
				}else{
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("images_1",image_1);
					session.setAttribute("images_2",image_2);
					session.setAttribute("shop_name",shop_name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					forward="/jsp/productMod.jsp?submit=登録";
				}
			 }
			
			if(request.getParameter("submit").equals("変更")){

				String pro_id = request.getParameter("pro_id");
				String sp_id = request.getParameter("sp_id");
				String pi_id = request.getParameter("pi_id");
				String pd_id = request.getParameter("pd_id");
				String images_id = request.getParameter("images_id");
				String itemization = request.getParameter("itemization");
				String wholesale = request.getParameter("wholesale");
				String set_quantity = request.getParameter("set_quantity");
				String pi_name = request.getParameter("pi_name");
				String jan_code = request.getParameter("jan_code");
				String branch_no = request.getParameter("branch_no");
				String ref_type = request.getParameter("ref_type");
				String retail_price = request.getParameter("retail_price");
				String tax_rate_class = request.getParameter("tax_rate_class");
				String shipping_term = request.getParameter("shipping_term");
				String image_permission = request.getParameter("image_permission");
				String sell_permission = request.getParameter("sell_permission");
				String auction_permission = request.getParameter("auction_permission");
				String direct_permission = request.getParameter("direct_permission");
				String out_of_stock = request.getParameter("out_of_stock");
				String bc_category_1 = request.getParameter("bc_id_1");
				String sc_category_1 = request.getParameter("sc_id_1");
				String bc_category_2 = request.getParameter("bc_id_2");
				String sc_category_2 = request.getParameter("sc_id_2");
				String bc_category_3 = request.getParameter("bc_id_3");
				String sc_category_3 = request.getParameter("sc_id_3");
				String descr = request.getParameter("descr");
				String detail = request.getParameter("detail");
				String image_1 = request.getParameter("image_1");
				String image_2 = request.getParameter("image_2");
				String image_3 = request.getParameter("image_3");
				String image_4 = request.getParameter("image_4");
				String image_5 = request.getParameter("image_5");
				String image_6 = request.getParameter("image_6");
				String image_7 = request.getParameter("image_7");
				String image_8 = request.getParameter("image_8");
				String image_9 = request.getParameter("image_9");
				String image_10 = request.getParameter("image_10");
				String shop_name = request.getParameter("shop_name");
				String[] item = {pro_id,sp_id,pi_id,pi_name,bc_category_1,sc_category_1,bc_category_2,sc_category_2,bc_category_3,sc_category_3,shop_name,descr,detail,jan_code,branch_no,itemization,ref_type,retail_price,wholesale,set_quantity,tax_rate_class,shipping_term,image_1,image_2,image_3,image_4,image_5,image_6,image_7,image_8,image_9,image_10,image_permission,sell_permission,auction_permission,direct_permission,out_of_stock};
				String[] necItem = {pro_id,sp_id,pi_id,pi_name,shop_name,ref_type,wholesale,set_quantity,tax_rate_class};
				String[] numItem = {sp_id,wholesale,set_quantity,retail_price,tax_rate_class};
				String[] itemName = {"ダイレクト商品ID","サプライヤーID","商品管理番号","商品名","カテゴリ1(大)","カテゴリ1(小)","カテゴリ2(大)","カテゴリ2(小)","カテゴリ3(大)","カテゴリ3(小)","ショップ名","消費者向け商品説明文","消費者向け商品詳細","JANコード","商品管理枝番号","内訳","参考価格種別","上代価格","卸価格単価","セット毎数量","税率区分","出荷条件","商品画像1","商品画像2","商品画像3","商品画像4","商品画像5","商品画像6","商品画像7","商品画像8","商品画像9","商品画像10","画像転載可","ネット販売可","ネットオークション可","消費者直送可","品切れ"};
				String[] necItemName = {"ダイレクト商品ID","サプライヤーID","商品管理番号","商品名","ショップ名","参考価格種別","卸価格単価","セット毎数量","税金区分"};
				String[] necNumItem = {"サプライヤー番号","卸価格単価","セット毎数量","上代価格","税金区分"};
				
				Errcheck err = new Errcheck();
				String errmsg1=err.inputCheck(item,necItem,itemName,necItemName);
				String errmsg2=err.numberCheck(numItem,necNumItem);
				String errmsg3=err.idExistCheck(pro_id, "product");
				
				if(errmsg1==null && errmsg2==null && errmsg3==null){
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("images_1",image_1);
					session.setAttribute("images_2",image_2);
					session.setAttribute("shop_name",shop_name);
					forward="/jsp/productConf.jsp?submit=変更";
				}else{
					session.setAttribute("pro_id",pro_id);
					session.setAttribute("sp_id",sp_id);
					session.setAttribute("pi_id",pi_id);
					session.setAttribute("pd_id",pd_id);
					session.setAttribute("images_id",images_id);
					session.setAttribute("itemization",itemization);
					session.setAttribute("wholesale",wholesale);
					session.setAttribute("set_quantity",set_quantity);
					session.setAttribute("pi_name",pi_name);
					session.setAttribute("jan_code",jan_code);
					session.setAttribute("branch_no",branch_no);
					session.setAttribute("ref_type",ref_type);
					session.setAttribute("retail_price",retail_price);
					session.setAttribute("tax_rate_class",tax_rate_class);
					session.setAttribute("shipping_term",shipping_term);
					session.setAttribute("image_permission",image_permission);
					session.setAttribute("sell_permission",sell_permission);
					session.setAttribute("auction_permission",auction_permission);
					session.setAttribute("direct_permission",direct_permission);
					session.setAttribute("out_of_stock",out_of_stock);
					session.setAttribute("sc_id",out_of_stock);
					session.setAttribute("bc_category_1",bc_category_1);
					session.setAttribute("sc_category_1",sc_category_1);
					session.setAttribute("bc_category_2",bc_category_2);
					session.setAttribute("sc_category_2",sc_category_2);
					session.setAttribute("bc_category_3",bc_category_3);
					session.setAttribute("sc_category_3",sc_category_3);
					session.setAttribute("descr",descr);
					session.setAttribute("detail",detail);
					session.setAttribute("images_1",image_1);
					session.setAttribute("images_2",image_2);
					session.setAttribute("shop_name",shop_name);
					//入力画面に戻した際の画面表示の設定
					request.setAttribute("errmsg1", errmsg1);
					request.setAttribute("errmsg2", errmsg2);
					request.setAttribute("errmsg3", errmsg3);
					forward="/jsp/productMod.jsp?submit=変更";
				}
			 }
			
			if(request.getParameter("submit").equals("戻る")){
				forward="./manageControl?no=3";
			}
	    }
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		
	}	
}
