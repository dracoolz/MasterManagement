package order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CustomerViewBean;
import dao.CustomerDao;

public class SearchCustomerServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);
        String action = request.getParameter("action");
        System.out.println("Action: " + action);

        if ("search".equals(action)) {
            String cus_name = request.getParameter("cus_name");
            String contact_name = request.getParameter("contact_name");
            String district = request.getParameter("district");
            System.out.println("Search parameters: cus_name=" + cus_name + ", contact_name=" + contact_name + ", district=" + district);

            int page = 1;
            int recordsPerPage = 10;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
                System.out.println("Page: " + page);
            }

            CustomerDao dao = new CustomerDao();
            ArrayList<CustomerViewBean> list = dao.selectMultipleCustomer(cus_name, contact_name, district);

            int noOfRecords = list.size();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            int start = (page - 1) * recordsPerPage;
            int end = Math.min(start + recordsPerPage, noOfRecords);
            List<CustomerViewBean> paginatedList = list.subList(start, end);

            request.setAttribute("list", paginatedList);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
            request.setAttribute("cus_name", cus_name);
            request.setAttribute("contact_name", contact_name);
            request.setAttribute("district", district);
            request.setAttribute("referer", request.getParameter("referer"));
            System.out.println("Records found: " + noOfRecords + ", No of pages: " + noOfPages);

            request.getRequestDispatcher("jsp/search_customer.jsp").forward(request, response);
        } 
        
        //追加ボタン押した時
        else if ("customerSend".equals(request.getParameter("customerSend"))) {
            int selectedCusId = Integer.parseInt(request.getParameter("selectedCusId"));
            String selectedCusName = request.getParameter("selectedCusName");
            session.setAttribute("selectedCusId", selectedCusId);
            session.setAttribute("selectedCusName", selectedCusName);

            // データ遷移処理後、前のページへ遷移
            String referer = request.getParameter("referer");
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect(request.getContextPath() + "/defaultPage"); // デフォルトページのURLに変更
            }
        } else if (request.getParameter("referer") != null) {
            // '戻る'前のページへ遷移
            String referer = request.getParameter("referer");
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect(request.getContextPath() + "/defaultPage"); // デフォルトページのURLに変更
            }
        } else {
            System.out.println("No action matched");
        }
    }
}