package order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ProductViewBean;
import dao.ProductDao;

public class ProductSearchServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ProductDao productDao = new ProductDao();

        if ("search".equals(action)) {
            String productName = request.getParameter("product_name");
            String[] largeCategories = request.getParameterValues("large_category");
            String[] smallCategories = request.getParameterValues("small_category");

            ArrayList<ProductViewBean> products = productDao.selectMultipleProducts(productName, largeCategories, smallCategories);
            List<ProductViewBean> largeCategoriesList = productDao.getLargeCategories();

            request.setAttribute("products", products);
            request.setAttribute("largeCategories", largeCategoriesList);

            for (int i = 0; i < largeCategories.length; i++) {
                if (largeCategories[i] != null && !largeCategories[i].isEmpty()) {
                    int largeCategoryId = Integer.parseInt(largeCategories[i]);
                    List<ProductViewBean> smallCategoriesList = productDao.getSmallCategories(largeCategoryId);
                    request.setAttribute("smallCategories" + i, smallCategoriesList);
                }
            }

            request.getRequestDispatcher("/search_product.jsp").forward(request, response);
        } else if ("addProduct".equals(action)) {
            HttpSession session = request.getSession();
            List<ProductViewBean> selectedProducts = (List<ProductViewBean>) session.getAttribute("selectedProducts");
            if (selectedProducts == null) {
                selectedProducts = new ArrayList<>();
            }

            int selectedProductId = Integer.parseInt(request.getParameter("selectedProductId"));
            String selectedProductName = request.getParameter("selectedProductName");
            ProductViewBean selectedProduct = new ProductViewBean();
            selectedProduct.setProductId(selectedProductId);
            selectedProduct.setProductName(selectedProductName);

            selectedProducts.add(selectedProduct);
            session.setAttribute("selectedProducts", selectedProducts);

            response.sendRedirect(request.getParameter("referer"));
        }
    }
}