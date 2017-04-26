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
<title>Manage Orders</title>
<script type="text/javascript">
	function selectAll(all){
		var selected=document.getElementsByName("selected");
			for(var i=0;i<selected.length;i++)
				selected[i].checked=all.checked;
	} 
	function confirm(){
		document.form.action="/InternetBookStore/confirm";
		document.form.submit();
	}
	function complete(){
		document.form.action="/InternetBookStore/complete";
	}
</script>
</head>
<body>
	
	<p align="right">
		<a href="/InternetBookStore/Set/setinfo.jsp"><%=((User)session.getAttribute("user")).getUserName() %></a>
		<a href="logout" >Logout</a>
	</p>
	<form action="/InternetBookStore/query" method="post">
	<table border="0" align="center" >
		<tr>
		<td align="center">OrderID:<input name="id"></td>
		<td align="center">UserName:<input name="userName"></td>
		<td align="center">BookID:<input name="bookID"></td>
		<td align="center">Confirmed:<input name="confirmed"></td>
		<td align="center">Completed:<input name="done"></td>
		<td align="center">PurchaseTime:<input name="time0">~<input name="time1"></td>
		<td align="center"><input type="submit" value="搜索"></td>
		</tr>
	</table>
	</form>
	<form name="form" action="/InternetBookStore/uploadImage" method="post" enctype="multipart/form-data">
		<table  border="0" align="center" >
		<tr>
		<td align="center"><input type="checkbox" onclick="selectAll(this)"></td>
		<td align="center">OrderID</td>
		<td align="center">UserName</td>
		<td align="center">BookID</td>
		<td align="center">Confirmed</td>
		<td align="center">Completed</td>
		<td align="center">PurchaseTime</td>
		<td align="center">PhoneNumber</td>
		<td align="center">Address</td>
		</tr>
		<%
		@SuppressWarnings("unchecked")
		List<Order> orders=(List<Order>)session.getAttribute("orders");
		Order order=null;
		if(orders!=null){
			if(orders.size()==0){
		%>
		<tr><td colspan="9" align="center">No Records!</td></tr>
		<%
			}
			else{
				int i;
				for(i=0;i<orders.size();i++){
					order=orders.get(i);
		%>
		<tr>
		<td align="center"><input type="checkbox" name="selected" value="<%=i %>"></td>
		<td align="center"><input type="hidden" name="bookIndex" value=<%=i %>><%=order.getId() %></td>
		<td align="center"><%=order.getUser().getUserName() %></td>
		<td align="center"><%=order.getBook().getBookID() %></td>
		<td align="center"><%=order.getConfirmed() %></td>
		<td align="center"><%=order.getDone() %></td>
		<td align="center"><%=order.getTimeStamp() %></td>
		<td align="center"><%=order.getTel() %></td>
		<td align="center"><%=order.getAddress() %></td>
		</tr>
		<%
				}
			}
		%>
		<tr>
		<td colspan="7" align="right">
			<input type="submit" onclick="confirm()" value="Confirm Orders">
		</td>
		<td align="right">
			<input type="submit" onclick="complete()" value="Complete Orders">
		</td>
		<td align="right">
			<a href="/InternetBookStore/Manager/managebooks.jsp"><input type="button" value="Manage Books"></a>
		</td>
		</tr>
		<%
		}
		else{
			response.sendRedirect("getOrders");
		}
		%>
		</table>
	</form>
	
</body>
</html>