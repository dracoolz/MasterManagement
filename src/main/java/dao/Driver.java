package dao;



import java.util.ArrayList;

import bean.OrderSlipViewBean;

public class Driver {

	public static void main(String args[]){


		//一覧表示呼び出し
		selectAll();
		

	}
	//一覧表示
//	//public static void selectAll(){
//		System.out.println("****全件検索テストメソッド****");
//		OrderListDao order = new OrderListDao();
//		// orderList()メソッドを呼び出して受注情報を取得
//       ArrayList<OrderListViewBean> orderList = order.orderList();
//		for(int i = 0; i< orderList.size();i++){
//			System.out.print( orderList.get(i).getOrderId()+",");
//			System.out.print( orderList.get(i).getDate()+",");
//			System.out.println( orderList.get(i).getCustomerName());
//		}
//		if(orderList.size()==0){
//			System.out.println("データは存在しません");
//		}
//	}
	
	public static void selectAll(){
	OrderSlipDao orderSlip = new OrderSlipDao(); // この OrderDAO のインスタンスは適切に初期化されている必要があります
    int orderId = 74; // 注文番号を適切な値に置き換える
    ArrayList<OrderSlipViewBean> orderSlipList = orderSlip.selectOrderSlip(orderId);
    
    // 取得した受注情報の詳細を表示する
    for (OrderSlipViewBean orderSlip1 : orderSlipList) {
        System.out.println("Product ID: " + orderSlip1.getProductId());
        System.out.println("Product Name: " + orderSlip1.getProductName());
        System.out.println("Order Quantity: " + orderSlip1.getOrderQty());
        System.out.println("Cancel Quantity: " + orderSlip1.getCancelQty());
        System.out.println("Refund Quantity: " + orderSlip1.getRefundQty());
    }}
}
