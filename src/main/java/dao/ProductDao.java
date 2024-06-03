package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.ProductViewBean;

public class ProductDao extends DBAccess {

    public ArrayList<ProductViewBean> selectMultipleProducts(String product_name, String[] large_categories, String[] small_categories) {
        ArrayList<ProductViewBean> list = new ArrayList<ProductViewBean>();
        StringBuilder sql = new StringBuilder("SELECT pd.pi_id, pd.pi_name, sc.sc_id, sc.sc_category, bc.bc_id, bc.bc_category FROM product_description pd ");
        sql.append("JOIN small_category sc ON pd.category_id = sc.sc_id ");
        sql.append("JOIN big_category bc ON sc.bc_id = bc.bc_id WHERE 1=1");

        if (product_name != null && !product_name.isEmpty()) {
            sql.append(" AND pd.pi_name LIKE ?");
        }

        for (int i = 0; i < large_categories.length; i++) {
            if ((large_categories[i] != null && !large_categories[i].isEmpty()) && (small_categories[i] != null && !small_categories[i].isEmpty())) {
                sql.append(" AND (sc.bc_id = ? AND sc.sc_category = ?)");
            }
        }

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql.toString());

            int index = 1;
            if (product_name != null && !product_name.isEmpty()) {
                ps.setString(index++, "%" + product_name + "%");
            }

            for (int i = 0; i < large_categories.length; i++) {
                if ((large_categories[i] != null && !large_categories[i].isEmpty()) && (small_categories[i] != null && !small_categories[i].isEmpty())) {
                    ps.setInt(index++, Integer.parseInt(large_categories[i]));
                    ps.setString(index++, small_categories[i]);
                }
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductViewBean bean = new ProductViewBean();
                bean.setProductId(rs.getInt("pi_id"));
                bean.setProductName(rs.getString("pi_name"));
                bean.setScId(rs.getInt("sc_id"));
                bean.setScCategory(rs.getString("sc_category"));
                bean.setBcId(rs.getInt("bc_id"));
                bean.setBcCategory(rs.getString("bc_category"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return list;
    }

    public List<ProductViewBean> getLargeCategories() {
        List<ProductViewBean> list = new ArrayList<>();
        String sql = "SELECT bc_id, bc_category FROM big_category";

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductViewBean bean = new ProductViewBean();
                bean.setBcId(rs.getInt("bc_id"));
                bean.setBcCategory(rs.getString("bc_category"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return list;
    }

    public List<ProductViewBean> getSmallCategories(int bcId) {
        List<ProductViewBean> list = new ArrayList<>();
        String sql = "SELECT sc_id, sc_category FROM small_category WHERE bc_id = ?";

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setInt(1, bcId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProductViewBean bean = new ProductViewBean();
                bean.setScId(rs.getInt("sc_id"));
                bean.setScCategory(rs.getString("sc_category"));
                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return list;
    }
}