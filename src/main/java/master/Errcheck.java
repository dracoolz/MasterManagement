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
	
	public String injectionCheck(String str, String strname) {
		if(str.contains("'")) {
			return  strname + "に\'は使用しないでください";
		}
		return null;
	}
	public String injectionCheck(String[] strs,String[] strnames) {
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
	
	

	public String inputCheck(String str, String strnames) {
		this.injectionCheck(str, strnames);
		if(str.length() == 0) {
			return strnames+"が入力されていません";
		}
		return null;
	}
	public String inputCheck(String[] strs, String[] necStrs, String[] strnames, String[] necStrNames) {
		if(this.injectionCheck(strs, strnames) == null) {
			for(int i = 0; i < necStrs.length; ++i) {
				if(necStrs[i].length() == 0) {
					return necStrNames[i]+"が入力されていません";
				}
			}
			return null;
		}
		return this.injectionCheck(strs, strnames);
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
	
	public String numberCheck(String str, String str2){
		if(str.matches("[0-9]+")){
			if(Integer.parseInt(str)>0){
				return null;
			}
		}
		return "整数で入力してください";
	}
	public String numberCheck(String[] strs){
		String str;
		for(int i=0; i<strs.length; i++) {
			str = strs[i];
			if(str.matches("[0-9]+")){
				if(Integer.parseInt(str)>0){
					return null;
				}
			}
		}
		return "整数で入力してください";
	}
	
	public String idExistCheck(int id, String type){
		if(type.equals("user")) {
			UserDao dao = new UserDao();
			UserBean bean = dao.select(id);
			if(id == bean.getEmp_id()){
				return null;
			}
			return "存在しない社員番号です";
		}else if(type.equals("bc")) {
			BigCategoryDao dao = new BigCategoryDao();
			BigCategoryBean bean = dao.select(id);
			if(id == bean.getBc_id()){
				return null;
			}
			return "存在しない大カテゴリ番号です";
		}else if(type.equals("sc")) {
			SmallCategoryDao dao = new SmallCategoryDao();
			SmallCategoryBean bean = dao.select(id);
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
			if(String.valueOf(id) == bean.getPro_id()){
				return null;
			}
			return "存在しないダイレクト商品番号です";
		}
		return null;
	}
	
	public String fullWidthCheck(String str) {
        if(str.matches("^[ぁ-ん] +$")) {
            return null;
        }
        return "全角ひらがなで入力してください";
    }

    public String emailCheck(String email) {
        if(email.matches("^([a-zA-Z0-9])+([a-zA-Z0-9.-])*@([a-zA-Z0-9-])+([a-zA-Z0-9._-]+)+$")) {
            return null;
        }
        return "正しいメールアドレスを入力してください";
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
}
