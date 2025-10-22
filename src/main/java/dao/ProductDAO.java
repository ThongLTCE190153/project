/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author PC
 */
public class ProductDAO  {
    private final DBContext db = new DBContext();
   public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT product_id, product_name, [description], price, image_url, category_id FROM Product";

        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("product_id"));
                p.setProductName(rs.getString("product_name"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getBigDecimal("price"));
                p.setImageUrl(rs.getString("image_url"));
                p.setCategoryID(rs.getInt("category_id"));
                list.add(p);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}