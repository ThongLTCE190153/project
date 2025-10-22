/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private final UserDAO dao = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
      
        req.setCharacterEncoding("UTF-8");

        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phone = req.getParameter("phone");
        String address = req.getParameter("address");

        if (dao.exists(email)) {
            req.setAttribute("error", "Email đã tồn tại!");
            req.getRequestDispatcher("views/signup.jsp").forward(req, resp);
            return;
        }

        User u = new User();
        u.setFullName(fullName);
        u.setEmail(email);
        u.setPassword(password);
        u.setPhoneNumber(phone);
        u.setAddress(address);

        if (dao.register(u)) {
            resp.sendRedirect("login.jsp");
        } else {
            req.setAttribute("error", "Đăng ký thất bại, thử lại sau.");
            req.getRequestDispatcher("views/signup.jsp").forward(req, resp);
        }
    }
}