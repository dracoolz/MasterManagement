package master;

public class Errcheck {

	public int inputCheck(String str1, String str2) {
		if(str1 == null) {
			return 1;
		} else if(str2 == null) {
			return 2;
		}
		return 0;
	}
	
	public Boolean correctCheck(String pass1, String pass2){
		if(pass1.equals(pass2)){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean numberCheck(String str){
		if(str.matches("[0-9]+")){
			if(Integer.parseInt(str)>0){
				return true;
			}
		}
		return false;
	}

	/*
	 * 	public String numCheck(String id,String kakaku){
		String msg;
		if(id.matches("[0-9]+") && kakaku.matches("[0-9]+")){
			if(Integer.parseInt(id)>0 && Integer.parseInt(kakaku)>0){
				msg=null;
			}else{
				msg="IDと価格には整数を入力して下さい";
			}
		}else{
			msg="IDと価格には数字を入力して下さい";
		}
		return msg;
	}
	
	public String exitId(String id){
		String msg=null;
		UserDao dao = new UserDao();
		ArrayList<UserBean> list = dao.jouken(id);
		for(int i = 0; i<list.size();i++){

			if(id.equals(list.get(i).getId())){
				msg="既に存在しています";
			}

		}
		return msg;
	}

	public String notExitId(String id){
		String msg=null;
		UserDao dao = new UserDao();

		ArrayList<UserBean> list = dao.jouken(id);
		if(0==list.size()){
			msg="対象データはありません";
		}
		return msg;
	}
	*/
}
