<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8"><title>Đăng nhập</title></head>
<body>
<h2>Đăng nhập</h2>

<% if (request.getAttribute("error") != null) { %>
    <p style="color:red"><%= request.getAttribute("error") %></p>
<% } %>

<form action="../login" method="post">
    <label>Email:</label><br>
    <input type="email" name="email" required><br>
    <label>Mật khẩu:</label><br>
    <input type="password" name="password" required><br><br>
    <button type="submit">Đăng nhập</button>
</form>

<p><a href="signup.jsp">Chưa có tài khoản? Đăng ký</a></p>
</body>
</html>
