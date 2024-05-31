package master;

import bean.UserBean;
import dao.UserDao;

public class Errcheck {

	public String inputCheck(String str, String strnames) {
		if(str.length() == 0) {
			return strnames+"が入力されていません";
		}
		return null;
	}
	
	public String inputCheck(String[] strs, String[] strnames) {
		for(int i = 0; i < strs.length; ++i) {
			if(strs[i].length() == 0) {
				return strnames[i]+"が入力されていません";
			}
		}
		return null;
	}

	public String injectionCheck(String str, String strname) {
		if(str.contains("'")) {
			return  strname + "に\'は使用しないでください";
		}
		return null;
	}
	
	public String injectionCheck(String[] strs, String[] strnames) {
		String place = null;
		for(int i = 0; i < strs.length; ++i) {
			if(strs[i].contains("'")) {
				if(place == null) {
					place = strnames[i];
				}
				else {
					place = place + "、" + strnames[i];
				}
			}
		}
		if(place != null) {
			return  place + "に\'は使用しないでください";
		}
		return null;
	}
	
	public String sameCheck(String pass1, String pass2){
		if(pass1.equals(pass2)){
			return null;
		}else{
			return "新しいパスワードが確認と一致していません";
		}
	}
	
	public String correctCheck(String pass1, String pass2){
		if(pass1.equals(pass2)){
			return null;
		}else{
			return "パスワードが違います";
		}
	}
	
	public String numberCheck(String str){
		if(str.matches("[0-9]+")){
			if(Integer.parseInt(str)>0){
				return null;
			}
		}
		return "整数で入力してください";
	}
	
	public String existId(int id){
		UserDao dao = new UserDao();
		UserBean bean = dao.select(id);
		if(id == bean.getEmp_id()){
			return null;
		}
		return "存在しない社員番号です";
	}
}
