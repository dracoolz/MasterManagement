package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.CustomerViewBean;

public class CustomerDao extends DBAccess {

    public ArrayList<CustomerViewBean> selectMultipleCustomer(String cus_name, String contact_name, String district) {

        ArrayList<CustomerViewBean> list = new ArrayList<CustomerViewBean>();

        StringBuilder sql = new StringBuilder("SELECT cus_name, contact_name, district FROM customer WHERE 0=0");

        if (cus_name != null && !cus_name.isEmpty()) {
            sql.append(" AND cus_name = ?");
        }
        if (contact_name != null && !contact_name.isEmpty()) {
            sql.append(" AND contact_name = ?");
        }
        if (district != null && !district.isEmpty()) {
            sql.append(" AND district = ?");
        }

        try {
            connect();
            PreparedStatement ps = getConnection().prepareStatement(sql.toString());

            int index = 1;
            if (cus_name != null && !cus_name.isEmpty()) {
                ps.setString(index++, cus_name);
            }
            if (contact_name != null && !contact_name.isEmpty()) {
                ps.setString(index++, contact_name);
            }
            if (district != null && !district.isEmpty()) {
                ps.setString(index++, district);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CustomerViewBean bean = new CustomerViewBean();
                bean.setCusName(rs.getString("cus_name"));
                bean.setContactName(rs.getString("contact_name"));
                bean.setDistrict(rs.getString("district"));
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