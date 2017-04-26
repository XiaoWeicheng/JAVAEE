<%@page import="entity.User"%>
<%@page import="java.util.List"%>
<%@page import="entity.Book"%>
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
<title>Show Books</title>
<script type="text/javascript">
	function putIntoShoppingTrolley(){
		document.form.action="/InternetBookStore/putIntoShoppingTrolley";
	}
	function selectAll(all){
		var selected=document.getElementsByName("selected");
			for(var i=0;i<selected.length;i++)
				selected[i].checked=all.checked;
	} 
	function downloadImage(){
		document.form.action="/InternetBookStore/downloadImage";
		document.form.submit();
	}
</script>
</head>
<body>
	
	<%
	if(session.getAttribute("user")==null) {
	%>
	<p align="right">
		Please <a href="/InternetBookStore/Login/login.jsp">Login</a>
	</p>
	<%
	} 
	else{
	%>
	<p align="right">
		<a href="/InternetBookStore/Set/setinfo.jsp"><%=((User)session.getAttribute("user")).getUserName() %></a>
		<a href="logout" >Logout</a>
	</p>
	<%
	}
	%>
	<form action="/InternetBookStore/query" method="post">
	<table border="0" align="center" width="1440dp">
		<tr>
		<td align="center">BookID:<input name="id"></td>
		<td align="center">BookName:<input name="name"></td>
		<td align="center">Class:<input name="class_"></td>
		<td align="center">Author:<input name="author"></td>
		<td align="center">Press:<input name="press"></td>
		<td align="center">Price:<input name="price0"><br>~<input name="price1"></td>
		<td align="center">Discount:<input name="discount0"><br>~<input name="discount1"></td>
		<td align="center"><input type="submit" value="Search"></td>
		</tr>
	</table>
	</form>
	<form name="form" action="/InternetBookStore/uploadImage" method="post" enctype="multipart/form-data">
		<table  border="1" align="center" width="1440dp">
		<tr>
		<td align="center"><input type="checkbox" onclick="selectAll(this)"></td>
		<td align="center">Cover</td>
		<td align="center">BookID</td>
		<td align="center">BookName</td>
		<td align="center">Class</td>
		<td align="center">Author</td>
		<td align="center">Press</td>
		<td align="center">Price</td>
		<td align="center">Discount</td>
		</tr>
		<%
		@SuppressWarnings("unchecked")
		List<Book> books=(List<Book>)session.getAttribute("books");
		Book book=null;
		if(books!=null){
			if(books.size()==0){
		%>
		<tr><td colspan="9" align="center">No Records!</td></tr>
		<%
			}
			else{
				int i;
				for(i=0;i<books.size();i++){
					book=books.get(i);
		%>
		<tr>
		<td align="center"><input type="checkbox" name="selected" value="<%=i %>"></td>
		<td align="center">
		<%
			if(book.getImageName()!=null&&!"".equals(book.getImageName())){
		%>
					<input type="image" src="/InternetBookStore/SmallImage/<%=book.getImageName() %>" onclick="downloadImage()">
		<%
			}
		%>
		</td>
		<td align="center"><input type="hidden" name="bookIndex" value=<%=i %>><%=book.getBookID() %></td>
		<td align="center"><%=book.getBookName() %></td>
		<td align="center"><%=book.getBookClass() %></td>
		<td align="center"><%=book.getBookAuthor() %></td>
		<td align="center"><%=book.getBookPress() %></td>
		<td align="center"><%=book.getBookPrice() %></td>
		<td align="center"><%=book.getBookDiscount() %></td>
		</tr>
		<%
				}
			}
			if((User)session.getAttribute("user")!=null) {
		%>
		<tr>
		<td colspan="9" align="right">
			<a href="/InternetBookStore/Order/ordershow.jsp"><input type="button" value="Check the Orders"></a>
			<a href="/InternetBookStore/ShoppingTrolley/shoppingtrolleyshow.jsp"><input type="button" value="Check the ShoppingTrolley"></a>
			<input type="submit" onclick="putIntoShoppingTrolley()" value="Put into ShoppingTrolley">
		</td>
		</tr>
		<%
			}
		}
		else{
			response.sendRedirect("query");
		}
		%>
		</table>
	</form>
	
</body>
</html>