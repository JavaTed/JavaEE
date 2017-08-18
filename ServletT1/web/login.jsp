<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
<%String repeat = request.getParameter("repeat");
if (repeat != null && repeat.equals("1")) {%>
<h2>Invlid login or password. Please reenter your credentials</h2>
<%} else {%>
<h2>Please enter your credentials</h2>
<%}%>
<form  action="/trylogin" method="POST">
    <TABLE>
        <TR>
            <TD>Login:<TD><input type="text" name="login">
        <TR>
            <TD>Password:<TD><input type="password" name="password">
    </Table>
    <INPUT type="submit">
</form>
</body>
</html>