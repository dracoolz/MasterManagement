package order;

import java.util.ArrayList;

import bean.OrderSlipBean;

public class ErrCheck {
	private String E001 = "社員番号またはパスワードが違います";
	private String E002 = "必須項目が入力されていません";
	private String E003 = "社員番号が存在していません";
	private String E004 = "入力されたパスワードが一致していません";
	private String E005 = "大カテゴリを選択してください";
	private String E006 = "以前のパスワードが違います";
	private String E007 = "全角ひらがなで入力してください";
	private String E008 = "正しいメールアドレスを入力してください";
	private String E009 = "そのカテゴリ名は既に存在しています";
	private String E010 = "注文数を入力してください";
	private String E011 = "キャンセル数を入力してください";
	private String E012 = "返品数を入力してください";
	private String E013 = "受注数を上回っています";
	private String E014 = "条件に一致する結果はありません";
	private String E015 = "商品IDが入力されていません";
	private String E016 = "取引先IDが入力されていません";
	private String E017 = "対象データはありません";
	private String E018 = "商品を追加してください";
	private String E019 = "取引先情報を入力してください";
	private String E020 = "商品情報を入力してください";
	private String E021 = "対象商品は既に存在しています";
	private String E022 = "取引先名を追加してください";
	
	//入力されているかチェック
	public boolean IsEnteredCheck(String str) {
		boolean result = true;
		if(str == "") {
			result = false;
		}
		return result;
	}
	
	//キャンセル数と返品数の合計が受注数を超えないか
	public boolean decrementQtyOverCheck(int orderQty, int cancelQty ,int refundQty) {
		boolean result = true;
		if(orderQty < (cancelQty+refundQty)) {
			result = false;
		}
		return result;
	}
	
	//商品が追加されているか（キャンセル商品返品商品でも使えそう
	public boolean addProductCheck(ArrayList<OrderSlipBean> productList) {
		boolean result = true;
		if(productList.size() == 0) {
			result = false;
		}
		return result;
	}

	//errmsg getter
	public String getE001() {
		return E001;
	}

	public String getE002() {
		return E002;
	}

	public String getE003() {
		return E003;
	}

	public String getE004() {
		return E004;
	}

	public String getE005() {
		return E005;
	}

	public String getE006() {
		return E006;
	}

	public String getE007() {
		return E007;
	}

	public String getE008() {
		return E008;
	}

	public String getE009() {
		return E009;
	}

	public String getE010() {
		return E010;
	}

	public String getE011() {
		return E011;
	}

	public String getE012() {
		return E012;
	}

	public String getE013() {
		return E013;
	}

	public String getE014() {
		return E014;
	}

	public String getE015() {
		return E015;
	}

	public String getE016() {
		return E016;
	}

	public String getE017() {
		return E017;
	}

	public String getE018() {
		return E018;
	}

	public String getE019() {
		return E019;
	}

	public String getE020() {
		return E020;
	}

	public String getE021() {
		return E021;
	}

	public String getE022() {
		return E022;
	}	
}