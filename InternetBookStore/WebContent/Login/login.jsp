<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<form action="../login" method="post">
		<table>
			<tr><td align="right">User Name:</td><td><input type="text" name="user.userName"/></td></tr>
			<tr><td align="right">Password:</td><td><input type="password" name="user.userPassword"/></td></tr>
			<tr><td><input type="submit" value="Login"/></td></tr>
		</table>
	</form>
	<a href="/InternetBookStore/Register/register.jsp">Register</a>,First<br>
	<a href="/InternetBookStore/ShowBooks/showbooks.jsp">InternetBookStore</a>
</body>
</html>