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
<title>ShoppingTrolley</title>
<script type="text/javascript">
	function selectAll(all){
		var selected=document.getElementsByName("selected");
			for(var i=0;i<selected.length;i++)
				selected[i].checked=all.checked;
	} 
	function commit(){
		document.form.action="/InternetBookStore/commit";
		document.form.submit();
	}
	function deleteFromShoppingTrolley(){
		document.form.action="/InternetBookStore/deleteFromShoppingTrolley";
	}
</script>
</head>
<body>
	<%User user=(User)session.getAttribute("user"); %>
	<p align="right">
		<a href="/InternetBookStore/Set/setinfo.jsp"><%=user.getUserName() %></a>
		<a href="logout" >Logout</a>
	</p>
	<form name="form" action="/InternetBookStore/getOrders" method="post" enctype="multipart/form-data">
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
		<tr><td colspan="4" align="center">No Record!</td></tr>
		<%
			}
			else{
				int i;
				for(i=0;i<orders.size();i++){
					order=orders.get(i);
					if(!order.getCommitted()){
		%>
		<tr>
		<td align="center"><input type="hidden" name="bookIndex" value=<%=i %>><%=order.getId() %></td>
		<td align="center"><%=order.getBook().getBookID() %></td>
		<td align="center"><%=order.getBook().getBookPrice()*(1-order.getBook().getBookDiscount()) %></td>
		</tr>
		<%
					}
				}
			}
		}
		else 
			response.sendRedirect("getOrders?committed=否");
		%>
		<tr>
		<td>PhoneNumber：</td><td colspan="3"><input name="tel"></td>
		<td>Address：</td><td colspan="3"><input name="address"></td>
		</tr>
		<tr>
		<td colspan="4" align="right">
			<input type="submit" onclick="commit()" value="Commit Orders">
			<input type="submit" onclick="deleteFromShoppingTrolley()" value="Delete">
			<a href="/InternetBookStore/Order/ordershow.jsp"><input type="button" value="Check in Orders"></a>
			<a href="/InternetBookStore/ShowBooks/showbooks.jsp"><input type="button" value="InternetBookStore"></a>
		</td>
		</tr>
		</table>
	</form>
	
</body>
</html>