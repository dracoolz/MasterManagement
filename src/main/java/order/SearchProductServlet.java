package order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductViewBean;
import dao.ProductDao;

public class SearchProductServlet extends HttpServlet {

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

        if ("search".equals(action)) {
            String productName = request.getParameter("product_name");

            System.out.println("Searching for product: " + productName);

            ProductDao dao = new ProductDao();
            List<ProductViewBean> list = dao.selectMultipleProduct(productName);

            request.setAttribute("list", list);
            request.setAttribute("referer", request.getParameter("referer"));

            request.getRequestDispatcher("jsp/search_product.jsp").forward(request, response);

        } else if ("productSend".equals(action)) {
            String selectedProductId = request.getParameter("selectedProductId");
            String selectedProductName = request.getParameter("selectedProductName");
            session.setAttribute("selectedProductId", selectedProductId);
            session.setAttribute("selectedProductName", selectedProductName);

            String referer = request.getParameter("referer");
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect(request.getContextPath() + "/defaultPage");
            }

        } else if (request.getParameter("referer") != null) {
            String referer = request.getParameter("referer");
            if (referer != null && !referer.isEmpty()) {
                response.sendRedirect(referer);
            } else {
                response.sendRedirect(request.getContextPath() + "/defaultPage");
            }
        } else {
            System.out.println("No action matched");
        }
    }
}