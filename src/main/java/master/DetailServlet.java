package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SalesDao;

public class DetailServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
        int no = Integer.parseInt(req.getParameter("no"));
        String idValue = req.getParameter("idValue");

        if(no == 1) {
            //for companyList
            // DAOをインスタンス化する
            SalesDao dao = new SalesDao();
            // リクエストにDAOで取得したユーザ情報をセットする
            req.setAttribute("companylist", dao.selectCusSalesById(idValue));
            
            //検索機能のため
            String customerName = req.getParameter("customer_name");
            String contactName = req.getParameter("contact_name");
            String district = req.getParameter("district");
            //出力
            System.out.println("CustomerList");
            System.out.println("CustomerName : " + customerName);
            System.out.println("contactName : " + contactName);
            System.out.println("idvalue : " + idValue);
            System.out.println("district : " + district + "\n");

            RequestDispatcher rd = req.getRequestDispatcher("/jsp/companyListDetail.jsp");
            rd.forward(req, res);

        } else {
            //for productList
            // DAOをインスタンス化する
            SalesDao dao = new SalesDao();
            // リクエストにDAOで取得したユーザ情報をセットする
            req.setAttribute("productlist", dao.selectProSalesById(idValue));
            
            //検索機能のため
            String productName = req.getParameter("product_name");
            String bigCategory = req.getParameter("big");
            String smallCategory = req.getParameter("small");
            //出力
            System.out.println("ProductList");
            System.out.println("product_name : "  + productName);
            System.out.println("big_category : " + bigCategory);
            System.out.println("small_category : " + smallCategory);
            System.out.println("idvalue : " + idValue + "\n");

            RequestDispatcher rd = req.getRequestDispatcher("/jsp/productListDetail.jsp");
            rd.forward(req, res);
        }
    }
}
