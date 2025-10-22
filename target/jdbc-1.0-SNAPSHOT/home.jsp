<%@page import="model.User"%>
<%@page import="model.Product"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page session="true" %>
<!DOCTYPE html>
<%
    if (request.getAttribute("products") == null) {
        response.sendRedirect(request.getContextPath() + "/home");
        return;
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Trang chủ</title>
    </head>
    <body>
        <header style="background:#009688;padding:10px;color:white;">
            <span><b>FastFoodShop</b></span>
            <span style="float:right;">
                <%
                    User u = (User) session.getAttribute("user");
                    if (u == null) {
                %>
                <a href="views/login.jsp" style="color:white;">Đăng nhập</a> |
                <a href="views/signup.jsp" style="color:white;">Đăng ký</a>
                <%
                } else {
                %>
                Xin chào, <%= u.getFullName()%> (<%= u.getRoleName()%>) |
                <a href="logout" style="color:white;">Đăng xuất</a>
                <%
                    }
                %>
            </span>
        </header>

        <main style="padding:20px;">
            <h2>🍔 Danh sách sản phẩm</h2>
            <div class="container">
                <%
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (Product p : products) {
                %>
                <div class="product">
                    <img src="<%= request.getContextPath() + p.getImageUrl()%>" 
                         alt="<%= p.getProductName()%>" 
                         style="width:180px; height:180px; object-fit:cover; border-radius:8px;">
                    <p class="name"><%= p.getProductName()%></p>
                    <p><%= p.getDescription()%></p>
                    <p class="price"><%= String.format("%.0f", p.getPrice())%> VNĐ</p>
                </div>
                <%
                    }
                } else {
                %>
                <p>Không có sản phẩm nào để hiển thị!</p>
                <%
                    }
                %>
            </div>
        </main>
    </body>
</html>
