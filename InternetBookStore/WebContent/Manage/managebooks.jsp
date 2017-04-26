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
<title>Manage Books</title>
<script type="text/javascript">
	function selectAll(all){
		var selected=document.getElementsByName("selected");
			for(var i=0;i<selected.length;i++)
				selected[i].checked=all.checked;
	} 
	function addBook(){
		document.form.action="/InternetBookStore/addBook";
		document.form.submit();
	}
	function saveChange(){
		document.form.action="/InternetBookStore/saveChange";
		document.form.submit();
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
		<td align="center">BookID:<input name="id" ></td>
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
		<table  border="0" align="center" >
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
			System.out.println(book.getImageName());
			if(book.getImageName()!=null&&!"".equals(book.getImageName())){
		%>
			<a href="/InternetBookStore/SmallImage/<%=book.getImageName() %>" onclick="downloadImage()"><%=book.getBookName() %></a>
		<%
			}
			else{
		%>
				<input type="file" name="image"/><br>
				<input type="submit" value="Upload Cover" />
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
		<td align="center"><input name="discounts" value="<%=book.getBookDiscount() %>"></td>
		</tr>
		<%
				}
			}
		%>
		<tr>
		<td align="center">BookID:<input name="id" ></td>
		<td align="center">BookName:<input name="name"></td>
		<td align="center">Class:<input name="class_"></td>
		<td align="center">Author:<input name="author"></td>
		<td align="center">Press:<input name="press"></td>
		<td align="center">Price:<input name="price0"></td>
		<td align="center">Discount:<input name="discount0"></td>
		<td align="center" colspan="2"><input type="submit" value="ADD" onClick="addBook()"></td>
		</tr>
		<tr>
		<td colspan="8" align="right">
			<input type="submit" onclick="saveChange()" value="Save Changes">
		</td>
		<td align="right">
			<a href="/InternetBookStore/Manage/manageorder.jsp"><input type="button" value="Manage Orders"></a>
		</td>
		</tr>
		<%
		}
		else{
			response.sendRedirect("query");
		}
		%>
		</table>
	</form>
	
</body>
</html>