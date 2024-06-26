package master;

import bean.BigCategoryBean;
import bean.ProductBean;
import bean.SmallCategoryBean;
import bean.UserBean;
import dao.BigCategoryDao;
import dao.ProductDao;
import dao.SmallCategoryDao;
import dao.UserDao;

public class Errcheck {
	
	public String injectionCheck(String str, String strName) {
		if(str.contains("'") && str != null) {
			return  strName + "に\'は使用しないでください";
		}
		return null;
	}
	
	public String injectionCheck(String[] strs, String[] strNames) {
		String place = null;
		for(int i = 0; i < strs.length; ++i) {
			if(strs[i].contains("'") && strs[i] != null) {
				if(place == null) {
					place = strNames[i];
				}
				else {
					place = place + "、" + strNames[i];
				}
			}
		}
		if(place != null) {
			return  place + "に\'は使用しないでください";
		}
		return null;
	}
	
	public String inputCheck(String str, String strNames) {
		this.injectionCheck(str, strNames);
		if(str.length() == 0) {
			return strNames+"が入力されていません";
		}
		return null;
	}
	
	public String inputCheck(String[] strs, String[] necStrs, String[] strNames, String[] necStrNames) {
		String place = null;
		if(this.injectionCheck(strs, strNames) == null) {
			for(int i = 0; i < necStrs.length; ++i) {
				if(necStrs[i].length() == 0) {
					if(place == null) {
						place = strNames[i];
					}
					else {
						place = place + "、" + strNames[i];
					}
				}
			}
			if(place != null) {
				return place + "が入力されていません";
			}
			return null;
		}
		return this.injectionCheck(strs, strNames);
	}
	
	public String correctCheck(String pass1, String pass2){
		if(pass1.equals(pass2)){
			return null;
		} else{
			return "パスワードが違います";
		}
	}
	
	public String sameCheck(String pass1, String pass2){
		if(pass1.equals(pass2)){
			return null;
		} else{
			return "新しいパスワードが確認と一致していません";
		}
	}
	
	public String numberCheck(String str, String strName){
		if(!str.matches("[0-9]+") || Integer.parseInt(str)<0){
			return strName+"は整数で入力してください";
		}
		return null;
	}
	
	public String numberCheck(String[] strs, String[] strNames){
		String str;
		for(int i=0; i<strs.length; i++) {
			str = strs[i];
			if(!str.matches("[0-9]+") || Integer.parseInt(str)<0){
				return strNames[i]+"は整数で入力してください";
			}
		}
		return null;
	}
	
	public String fullWidthCheck(String str) {
		if(str.matches("^[ぁ-ん ]+$")) {
			return null;
		}
		return "ふりがなは全角ひらがなで入力してください";
	}
	
	public String emailCheck(String email) {
		if(email.matches("^([a-zA-Z0-9])+([a-zA-Z0-9.-])*@([a-zA-Z0-9-])+([a-zA-Z0-9._-]+)+$")) {
			return null;
		}
		return "正しいメールアドレスを入力してください";
	}
	
	public String idExistCheck(int id, String type){
		if(type.equals("user")) {
			UserDao dao = new UserDao();
			UserBean bean = dao.select(id);
			if(id == bean.getEmp_id()){
				return null;
			}
			return "存在しない社員番号です";
		} else if(type.equals("bc")) {
			BigCategoryDao dao = new BigCategoryDao();
			BigCategoryBean bean = dao.select(id);
			if(id == bean.getBc_id()){
				return null;
			}
			return "存在しない大カテゴリ番号です";
		} else if(type.equals("sc")) {
			SmallCategoryDao dao = new SmallCategoryDao();
			SmallCategoryBean bean = dao.selectSc(id);
			if(id == bean.getSc_id()){
				return null;
			}
			return "存在しない小カテゴリ番号です";
		}
		return null;
	}
	
	public String idExistCheck(String id, String type){
		if(type.equals("product")) {
			ProductDao dao = new ProductDao();
			ProductBean bean = dao.select(String.valueOf(id));
			if(bean.getPro_id() == null){
				return null;
			}
			return "このダイレクト商品番号は既に存在しています";
		}
		return null;
	}
	
	public String categoryExistCheck(String category, String type) {
		if(type.equals("bc")) {
			BigCategoryDao dao = new BigCategoryDao();
			BigCategoryBean bean = dao.selectIf(type);
			if(bean != null) {
				return "その大カテゴリ名は既に存在しています";
			}
		} else if(type.equals("sc")){
			SmallCategoryDao dao = new SmallCategoryDao();
			SmallCategoryBean bean = dao.selectIf(category);
			if(bean != null) {
				return "その小カテゴリ名は既に存在しています";
			}
		}
	return null;
	}
	
	public String contentsCheck(String type, String id, String[] strs, String[] strNames) {
	    ProductDao dao = new ProductDao();
	    String bean = dao.selectShop(id);
	    if (type.equals("サプライヤー")) {
	    	if(bean != null) {
	    		return null;
	    	}
	    }
	    return "そのサプライヤーは存在しません";
	    /*else {
	        if (!bean.getPi_name().equals(strs[0])) {
	            return "その商品管理番号の" + strNames[0] + "は" + bean.getPi_name() + "です";
	        }
	        if (!bean.getJan_code().equals(strs[1])) {
	            return "その商品管理番号の" + strNames[1] + "は" + bean.getJan_code() + "です";
	        }
	        if (!bean.getRef_type().equals(strs[2])) {
	            return "その商品管理番号の" + strNames[2] + "は" + bean.getRef_type() + "です";
	        }
	        if (!String.valueOf(bean.getRetail_price()).equals(strs[3])) {
	            return "その商品管理番号の" + strNames[3] + "は" + bean.getRetail_price() + "です";
	        }
	        if (!String.valueOf(bean.getTax_rate_class()).equals(strs[4])) {
	            return "その商品管理番号の" + strNames[4] + "は" + bean.getTax_rate_class() + "です";
	        }
	        if (!bean.getShipping_term().equals(strs[5])) {
	            return "その商品管理番号の" + strNames[5] + "は" + bean.getShipping_term() + "です";
	        }
	        if (!bean.getDescr().equals(strs[6])) {
	            return "その商品管理番号の" + strNames[6] + "は" + bean.getDescr() + "です";
	        }
	        if (!bean.getDetail().equals(strs[7])) {
	            return "その商品管理番号の" + strNames[7] + "は" + bean.getDetail() + "です";
	        }
	   }
	        return null;*/
	    
	}
}
