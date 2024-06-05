package order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ProductViewBean;
import dao.ProductDao;

public class SearchProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ProductDao productDao = new ProductDao();

        if ("search".equals(action)) {
            String productName = request.getParameter("product_name");
            String[] largeCategories = request.getParameterValues("large_category");
            String[] smallCategories = request.getParameterValues("small_category");

            ArrayList<ProductViewBean> products = productDao.selectMultipleProducts(productName, largeCategories, smallCategories);
            request.setAttribute("list", products);

            List<ProductViewBean> largeCategoriesList = productDao.getLargeCategories();
            request.setAttribute("largeCategories", largeCategoriesList);

            for (int i = 0; i < largeCategories.length; i++) {
                if (largeCategories[i] != null && !largeCategories[i].isEmpty()) {
                    int largeCategoryId = Integer.parseInt(largeCategories[i]);
                    List<ProductViewBean> smallCategoriesList = productDao.getSmallCategories(largeCategoryId);
                    request.setAttribute("smallCategories" + i, smallCategoriesList);
                }
            }

            request.getRequestDispatcher("/jsp/search_product.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDao = new ProductDao();
        List<ProductViewBean> largeCategoriesList = productDao.getLargeCategories();
        request.setAttribute("largeCategories", largeCategoriesList);
        request.getRequestDispatcher("/jsp/search_product.jsp").forward(request, response);
    }
}