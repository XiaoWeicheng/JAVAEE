<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page import="entity.Order"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ShowOrders</title>
</head>
<body>
	<%User user=(User)session.getAttribute("user"); %>
	<p align="right">
		<a href="/InternetBookStore/Set/setinfo.jsp"><%=user.getUserName() %></a>
		<a href="logout" >Logout</a>
	</p>
		<table  border="1" align="center" width="1440dp">
		<tr>
		<td align="center">OrderID</td>
		<td align="center">BookID</td>
		<td align="center">Price</td>
		</tr>
		<%
		List<Order> orders=(List<Order>)session.getAttribute("orders");
		if(orders!=null){
		Order order=null;
			if(orders==null||orders.size()==0){
		%>
		<tr><td colspan="7" align="center">No Records!</td></tr>
		<%
			}
			else{
				int i;
				for(i=0;i<orders.size();i++){
					order=orders.get(i);
					if(order.getCommitted()){
		%>
		<tr>
		<td align="center"><%=order.getId() %></td>
		<td align="center"><%=order.getBook().getBookID() %></td>
		<td align="center"><%=order.getBook().getBookPrice()*(1-order.getBook().getBookDiscount()) %></td>
		</tr>
		<%
					}
				}
			}
		}
		else 
			response.sendRedirect("getOrders?committed=是");
		%>
		<tr>
		<td  colspan="4" align="right">
			<a href="/InternetBookStore/ShoppingTrolley/shoppingtrolleyshow.jsp">
				<input type="button" value="Check in ShoppingTrolley">
			</a>
			<a href="/InternetBookStore/ShowBooks/showbooks.jsp"><input type="button" value="InternetBookStore"></a>
		</td>
		</tr>
		</table>
	
</body>
</html>