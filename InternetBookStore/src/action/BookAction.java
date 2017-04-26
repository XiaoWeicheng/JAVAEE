package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import entity.Book;
import entity.User;
import service.BookService;
import util.ImageScaleUtil;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace(value="/")
public class BookAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpSession session;
	private String id,name,class_,author,press,price0,price1,discount0,discount1;
	private BookService bookService;
	
	public BookAction() {
		super();
		bookService=new BookService();
	}
	@Action(
			value="query",
			results={
					@Result(name="query",location="/ShowBooks/showbooks.jsp",type="dispatcher"),
					@Result(name="manage",location="/Manage/managebooks.jsp",type="dispatcher")
			}
	)
	public String query() throws Exception {
		session=request.getSession();
		List<Book> books=bookService.queryBook(id,name,class_,author,press,price0,price1,discount0,discount1);
		session.setAttribute("books",books);
		User user=(User)session.getAttribute("user");
		if(user!=null)
			System.out.println("manager:"+user.getIsManager());
		if(user!=null&&user.getIsManager())
			return "manage";
		return "query";
	}
	
	private File image;
	private String imageFileName,imageContentType;
	private int bookIndex;
	@SuppressWarnings("unchecked")
	@Action(
			value="uploadImage",
			interceptorRefs={
					@InterceptorRef(value="fileUpload",
							params={"allowedType","image/bmp,image/png,image/gif,image/jpg,image/pjpeg,image/jpeg",
									"maximumSize","10485760"}),
					@InterceptorRef(value="defaultStack")
			},
			results={
					@Result(name="return",location="/Manage/managebooks.jsp",type="dispatcher"),
					@Result(name="input",location="/Manage/managebooks.jsp",type="dispatcher")
			}
	)
	public String uploadImage() throws Exception{
		String realPath=ServletActionContext.getServletContext().getRealPath("/Image");
		String realPath_=ServletActionContext.getServletContext().getRealPath("/SmallImage")+"/";
		if(image!=null){
			System.out.println(realPath);
			File saveImage=new File(new File(realPath), imageFileName);
			File smallImage=new File(new File(realPath_), imageFileName);
			if(!saveImage.getParentFile().exists())
				saveImage.getParentFile().mkdirs();
			if(!smallImage.getParentFile().exists())
				smallImage.getParentFile().mkdirs();
			FileUtils.copyFile(image, saveImage);
			ImageScaleUtil.scale(saveImage, 75, smallImage);
			session=request.getSession();
			List<Book> books=(List<Book>)session.getAttribute("books");
			Book book=books.get(bookIndex);
			book.setImageName(saveImage.getName());
			bookService.uploadImage(book);
		}
		return "return";
	}
	private long contentLength;
	private String contentDisposition,contentType,imageName;
	private InputStream inputStream;
	@SuppressWarnings("unchecked")
	@Action(
			value="downloadImage",
			results={
					@Result(name="return",type="stream",
							params = {"bufferSize", "4096"})
			}
	)
	public String downloadImage() throws Exception{
		String realPath=ServletActionContext.getServletContext().getRealPath("/Image");
		contentType="application/octet-stream";
		session=request.getSession();
		List<Book> books=(List<Book>)session.getAttribute("books");
		Book book=books.get(bookIndex);
		File image=new File(realPath,book.getImageName());
		String name=java.net.URLEncoder.encode(image.getName(),"UTF-8");
		contentDisposition="attachment;filename="+name;
		System.out.println(image.getPath());
		inputStream=new FileInputStream(image.getPath());
		contentLength=inputStream.available();
		return "return";
	}
	@SuppressWarnings("unchecked")
	@Action(
			value="saveChange",
			results={
					@Result(name="result",location="/Manage/manage_result.jsp",type="dispatcher")
			}
	)
	public String saveChange(){
		session=request.getSession();
		List<Book> books=(List<Book>)session.getAttribute("books");
		String discounts=request.getParameter("discounts");
		int result=bookService.setDisCount(books,bookIndex, discounts);
		if(result==0)
			request.setAttribute("msg", "--Error to Save Discount Information--");
		else 
			request.setAttribute("msg", "--Success to Save the Information of "+result+" Book"+(result==1?"":"s")+"--");
		return "result";
	}
	@Action(
			value="addBook",
			results={
					@Result(name="result",location="/Manage/manage_result.jsp",type="dispatcher")
			}
	)
	public String addBook(){
		if(bookService.addBook(new Book(id,name,class_,author,press,
				Double.parseDouble(price0),Double.parseDouble(discount0))))
			request.setAttribute("msg", "--Success to Add Book--");
		else 
			request.setAttribute("msg", "--Error to Add Book--");
		return "result";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BookService getBookService() {
		return bookService;
	}
	@Autowired
	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	public String getClass_() {
		return class_;
	}
	public void setClass_(String bookclass) {
		this.class_ = bookclass;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String bookAuthor) {
		this.author = bookAuthor;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String bookPress) {
		this.press = bookPress;
	}
	public String getPrice0() {
		return price0;
	}
	public void setPrice0(String price0) {
		this.price0 = price0;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getName() {
		return name;
	}
	public void setName(String bookName) {
		this.name = bookName;
	}
	public String getDiscount0() {
		return discount0;
	}
	public void setDiscount0(String discount0) {
		this.discount0 = discount0;
	}
	public String getDiscount1() {
		return discount1;
	}
	public void setDiscount1(String discount1) {
		this.discount1 = discount1;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public int getBookIndex() {
		return bookIndex;
	}
	public void setBookIndex(int bookIndex) {
		this.bookIndex = bookIndex;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public long getContentLength() {
		return contentLength;
	}
	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}
	public String getContentDisposition() {
		return contentDisposition;
	}
	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
}
