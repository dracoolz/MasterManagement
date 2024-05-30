package order;

import master.Errcheck;

public class Driver2 {

	public static void main(String args[]){

		numCheck();
		exitId();
		notExitId();


	}
	public static void numCheck(){
		Errcheck err = new Errcheck();

		String msg = err.numCheck("20","1234");
		System.out.println(msg);
	}

	public static void exitId(){
		Errcheck err= new Errcheck();

		String msg = err.exitId("00001");
		System.out.println("msg="+msg);
	}

	public static void notExitId(){
		Errcheck err= new Errcheck();

		String msg = err.notExitId("00010");
		System.out.println("msg="+msg);
	}




}