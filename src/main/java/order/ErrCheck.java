package order;

public class ErrCheck {
	private String E010 = "値を入力してください";
	private String E012 = "商品IDが入力されていません";
	private String E013 = "取引先IDが入力されていません";
	
	//入力されているかチェック
	public boolean IsEnteredCheck(String str) {
		boolean result = false;
		if(str == "") {
			result = true;
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