<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Đăng ký</title></head>
<body>
<h2>Đăng ký tài khoản</h2>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red"><%= request.getAttribute("error") %></p>
<% } %>

<form action="../signup" method="post">
    <label>Họ tên:</label><br>
    <input type="text" name="fullName" required><br>
    <label>Email:</label><br>
    <input type="email" name="email" required><br>
    <label>Mật khẩu:</label><br>
    <input type="password" name="password" required><br>
    <label>SĐT:</label><br>
    <input type="text" name="phone"><br>
    <label>Địa chỉ:</label><br>
    <input type="text" name="address"><br><br>
    <button type="submit">Đăng ký</button>
</form>

<p><a href="login.jsp">Đã có tài khoản? Đăng nhập</a></p>
</body>
</html>
