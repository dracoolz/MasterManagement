package order;

import java.util.ArrayList;

import bean.OrderSlipBean;

public class ErrCheck {
	private String E010 = "値を入力してください";
	private String E012 = "商品IDが入力されていません";
	private String E013 = "取引先IDが入力されていません";
	
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

	public String getE010() {
		return E010;
	}
	
	public String getE012() {
		return E012;
	}

	public String getE013() {
		return E013;
	}
	
	
}