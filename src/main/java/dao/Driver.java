package dao;



import java.util.ArrayList;

import bean.OrderListViewBean;

public class Driver {

	public static void main(String args[]){


		//一覧表示呼び出し
		selectAll();
		

	}
	//一覧表示
	public static void selectAll(){
		System.out.println("****全件検索テストメソッド****");
		OrderListDao order = new OrderListDao();
		// orderList()メソッドを呼び出して受注情報を取得
       ArrayList<OrderListViewBean> orderList = order.orderList();
		for(int i = 0; i< orderList.size();i++){
			System.out.print( orderList.get(i).getOrderId()+",");
			System.out.print( orderList.get(i).getDate()+",");
			System.out.println( orderList.get(i).getCustomerName());
		}
		if(orderList.size()==0){
			System.out.println("データは存在しません");
		}
	}
}
