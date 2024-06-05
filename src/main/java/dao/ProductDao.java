package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ProductViewBean;

public class ProductDao extends DBAccess {

    public List<ProductViewBean> selectMultipleProduct(String productName) {
        List<ProductViewBean> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT pi.pi_id, pi.pi_name, sc.sc_category, pi.retail_price ");
        sql.append("FROM product_info pi ");
        sql.append("JOIN category c ON pi.category_id = c.category_id ");
        sql.append("JOIN small_category sc ON c.sc_id = sc.sc_id ");
        sql.append("WHERE pi.pi_name LIKE ?");

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql.toString());
            ps.setString(1, "%" + productName + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String productId = rs.getString("pi_id");
                ProductViewBean bean = findProductInList(list, productId);

                if (bean == null) {
                    bean = new ProductViewBean();
                    bean.setProductId(productId);
                    bean.setProductName(rs.getString("pi_name"));
                    bean.setRetailPrice(rs.getInt("retail_price"));
                    bean.setSmallCategories(new ArrayList<>());
                    list.add(bean);
                }

                bean.getSmallCategories().add(rs.getString("sc_category"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return list;
    }

    private ProductViewBean findProductInList(List<ProductViewBean> list, String productId) {
        for (ProductViewBean bean : list) {
            if (bean.getProductId().equals(productId)) {
                return bean;
            }
        }
        return null;
    }
}