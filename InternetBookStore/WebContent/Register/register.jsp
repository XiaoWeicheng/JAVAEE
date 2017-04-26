<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
Please Input the Register Information
	<form action="../register" method="post">
		<table>
			<tr><td align="right">User Name：</td><td><input type="text" name="user.userName"/></td></tr>
			<tr><td align="right">Password：</td><td><input type="password" name="user.userPassword"/></td></tr>
			<tr><td align="right">Repeat Password：</td><td><input type="password" name="userPassword"/></td></tr>
			<tr><td align="right">Real Name：</td><td><input type="text" name="user.userRealName"/></td></tr>
			<tr><td><input type="submit" value="Register"/></td></tr>
		</table>
	</form>
	Have Register, <a href="/InternetBookStore/Login/login.jsp">Login</a>
</body>
</html>