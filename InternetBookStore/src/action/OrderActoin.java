package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import dao.OrderDAO;
import entity.Book;
import entity.Order;
import entity.User;
import service.OrderService;

@Controller
@Scope("prototype")
@ParentPackage("struts-default")
@Namespace(value="/")
public class OrderActoin extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2488228852092896770L;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpSession session;
	private String id,userName,bookID,time0,time1,committed,confirmed,done,address,tel;
	private OrderService orderService;
	
	@SuppressWarnings("unchecked")
	@Action(
			value="putIntoShoppingTrolley",
			results={
					@Result(name="result",location="/ShoppingTrolley/shoppingsrolley_result.jsp",type="dispatcher")
			}
	)
	public String putIntoShoppingTrolley() {
		session=request.getSession();
		String[] selected=request.getParameterValues("selected");
		int result=new OrderDAO().putIntoShoppingTrolley(selected,(List<Book>)session.getAttribute("books")
				,(User)session.getAttribute("user"));
		if(result==-1)
			request.setAttribute("msg", "--Have Choosen No Books--");
		else if(result==0)
			request.setAttribute("msg", "--Error to Put into ShoppingTrolley--");
		else 
			request.setAttribute("msg", "--Success to Put "+result+" Books into ShoppingTrolley--");
		return "result";
	}
	@SuppressWarnings("unchecked")
	@Action(
			value="deleteFromShoppingTrolley",
			results={
					@Result(name="result",location="/ShoppingTrolley/shoppingtrolley_result.jsp",type="dispatcher")
			}
	)
	public String deleteFromShoppingTrolley(){
		session=request.getSession();
		String[] selected=request.getParameterValues("selected");
		int result=new OrderDAO().deleteFromShoppingTrolley(selected,(List<Order>)session.getAttribute("orders"));
		if(result==0)
			request.setAttribute("msg", "--Error to Delete from ShoppingTrolley--");
		else 
			request.setAttribute("msg", "--Success to Delete "+result+" Books from ShoppingTrolley--");
		return "result";
	}
	@SuppressWarnings("unchecked")
	@Action(
			value="commit",
			results={
					@Result(name="result",location="/Order/Order_result.jsp",type="dispatcher")
			}
	)
	public String commit(){
		session=request.getSession();
		String[] selected=request.getParameterValues("selected");
		int result=new OrderDAO().commit(selected,(List<Order>)session.getAttribute("orders"), address, tel);
		if(result==-1)
			request.setAttribute("msg", "--With No Phone Number or Address--");
		else if(result==0)
			request.setAttribute("msg", "--Error to Commit Orders--");
		else 
			request.setAttribute("msg", "--Success to Commit "+result+" Orders--");
		return "result";
	}
	@SuppressWarnings("unchecked")
	@Action(
			value="confirm",
			results={
					@Result(name="result",location="/Manage/manage_result.jsp",type="dispatcher")
			}
	)
	public String confirm(){
		session=request.getSession();
		String[] selected=request.getParameterValues("selected");
		int result=new OrderDAO().confirm(selected,(List<Order>)session.getAttribute("orders"));
		if(result==0)
			request.setAttribute("msg", "--Error to Confirm--");
		else 
			request.setAttribute("msg", "--Success to Confirm "+result+" Orders--");
		return "result";
	}
	@SuppressWarnings("unchecked")
	@Action(
			value="complete",
			results={
					@Result(name="result",location="/Manage/manage_result.jsp",type="dispatcher")
			}
	)
	public String complete(){
		session=request.getSession();
		String[] selected=request.getParameterValues("selected");
		int result=new OrderDAO().complete(selected,(List<Order>)session.getAttribute("orders"));
		if(result==0)
			request.setAttribute("msg", "--Error to Complete--");
		else 
			request.setAttribute("msg", "--Success to Complete "+result+" Orders--");
		return "result";
	}
	@Action(
			value="getOrders",
			results={
					@Result(name="userShoppingTrolley",location="/ShoppingTrolley/shoppingtrolleyshow.jsp",type="dispatcher"),
					@Result(name="userOrder",location="/Order/ordershow.jsp",type="dispatcher"),
					@Result(name="manager",location="/Manage/manageorder.jsp",type="dispatcher")
			}
	)
	public String getOrders(){
		String result="userShoppingTrolley";
		session=request.getSession();
		User user=(User)session.getAttribute("user");
		List<Order> orders;
		if(user.getIsManager()){
			result="manager";
			orders=orderService.getOrders(id,userName,bookID,"ÊÇ",confirmed, done,time0,time1);
			request.setAttribute("orders", orders);
		}
		else {
			orders=orderService.getOrders(id,userName,bookID,committed,confirmed, done,time0,time1);
			if("ÊÇ".equals(committed))
				result="userOrder";
		}
		return result;
	}
	
	public OrderService getOrderService() {
		return orderService;
	}
	@Autowired
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getTime0() {
		return time0;
	}
	public void setTime0(String time0) {
		this.time0 = time0;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(String confirmed) {
		this.confirmed = confirmed;
	}
	public String getDone() {
		return done;
	}
	public void setDone(String done) {
		this.done = done;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCommitted() {
		return committed;
	}
	public void setCommitted(String committed) {
		this.committed = committed;
	}
	
}
