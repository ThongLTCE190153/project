/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import db.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import model.User;

/**
 *
 * @author NgKaitou
 */

public class UserDAO {
    DBContext db = new DBContext();

    // Đăng nhập bằng email và password
    public User login(String email, String password) {
        String sql = " SELECT u.user_id, u.full_name, u.email, u.phone_number, u.address, "
                   + "u.role_id, r.role_name "
                   + "FROM [User] u "
                   + "JOIN Role r ON u.role_id = r.role_id "
                   + "WHERE u.email = ? AND u.[password] = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User u = new User();
                u.setUserID(rs.getInt("user_id"));
                u.setFullName(rs.getString("full_name"));
                u.setEmail(rs.getString("email"));
                u.setPhoneNumber(rs.getString("phone_number"));
                u.setAddress(rs.getString("address"));
                u.setRoleID(rs.getInt("role_id"));
                u.setRoleName(rs.getString("role_name"));
                return u;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Đăng ký người dùng mới
    public boolean register(User u) {
        String sql ="INSERT INTO [User] (full_name, email, [password], phone_number,[address], role_id) "
           + "VALUES (?, ?, ?, ?, ?, 3)"; // mặc định role_id = 3 (Customer)
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getFullName());
            ps.setString(2, u.getEmail());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getPhoneNumber());
            ps.setString(5, u.getAddress());
            return ps.executeUpdate() > 0;
        } catch (SQLIntegrityConstraintViolationException ex) {
            return false; // email trùng
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra email tồn tại
    public boolean exists(String email) {
        String sql = "SELECT 1 FROM [User] WHERE email = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}