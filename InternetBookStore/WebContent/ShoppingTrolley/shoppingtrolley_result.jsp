<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result of Purchasing</title>
</head>
<body>
<%=(String)request.getAttribute("msg") %><br>
<a href="/InternetBookStore/ShowBooks/showbooks.jsp">InternetBookStore</a>
<a href="/InternetBookStore/ShoppingTrolley/shoppingtrolleyshow.jsp">ShoppingTrolley</a>
<a href="/InternetBookStore/Order/ordershow.jsp">Orders</a>
</body>
</html>