<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result of Manage</title>
</head>
<body>
<%=(String)request.getAttribute("msg") %><br>
<a href="/InternetBookStore/Manage/managebooks.jsp">Manage Books</a><br>
<a href="/InternetBookStore/Manage/manageorder.jsp">Manage Orders</a>
</body>
</html>