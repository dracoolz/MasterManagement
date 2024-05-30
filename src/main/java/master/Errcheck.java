package master;

import bean.UserBean;
import dao.UserDao;

public class Errcheck {

	public int inputCheck(String str) {
		if(str.length() == 0) {
			return 1;
		}
		return 0;
	}
	
	public int inputCheck(String str1, String str2) {
		if(str1.length() == 0) {
			return 1;
		} else if(str2.length() == 0) {
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

	public Boolean existId(int id){
		UserDao dao = new UserDao();
		UserBean bean = dao.select(id);
		if(id == bean.getEmp_id()){
			return true;
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
