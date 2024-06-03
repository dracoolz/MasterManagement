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
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession(true);

        if (request.getParameter("customerSearch") != null) {
            String cus_name = request.getParameter("cus_name");
            String contact_name = request.getParameter("contact_name");
            String district = request.getParameter("district");

            int page = 1;
            int recordsPerPage = 10;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
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

            request.getRequestDispatcher("search_customer.jsp").forward(request, response);
        } 
        
        //追加ボタン押した時
        else if (request.getParameter("customerSend") != null) {
            int selectedCusId = Integer.parseInt(request.getParameter("selectedCusId"));
            String selectedCusName = request.getParameter("selectedCusName");
            session.setAttribute("selectedCusId", selectedCusId);
            session.setAttribute("selectedCusName", selectedCusName);

            // データ遷移処理後、前のページへ遷移
            String referer = request.getParameter("referer");
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
                request.getRequestDispatcher("???????.jsp").forward(request, response);
            }
        } else if (request.getParameter("referer") != null) {
            // '戻る'前のページへ遷移
            String referer = request.getParameter("referer");
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
                request.getRequestDispatcher("???????.jsp").forward(request, response);
            }
        }
    }
}