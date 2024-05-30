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
　　　バドミントンを軽蔑するのは、ちょっと待ってくださいね。バドミントンは実際には非常に技術的で素晴らしいスポーツですよ。素早い動き、正確なショット、戦略的なプレイが必要です。プロ選手のスピードと反応力にはただただ驚嘆しますし、そのスマッシュやドロップショットの精度は尊敬に値します。バドミントンは体力と集中力を要するスポーツであり、軽視することはできません。




}