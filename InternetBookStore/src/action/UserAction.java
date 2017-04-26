package action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import entity.User;
import service.UserService;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String userPassword,newPassword,newPassword_;
	private HttpServletRequest request=ServletActionContext.getRequest();
	private HttpSession session;
	@Resource
	private UserService userService;
	public UserAction() {
	}
	public User getUser() {
		return user;
	}
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String password) {
		this.userPassword = password;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassword_() {
		return newPassword_;
	}
	public void setNewPassword_(String newPassword_) {
		this.newPassword_ = newPassword_;
	}
	public String register() throws Exception{
		System.out.println("×¢²á£º");
		String result="failure";
		if(userService.register(user, userPassword))
			result="success";
		System.out.println(result);
		return result;
	}
	public String login() throws Exception{
		System.out.println("µÇÂ¼£º");
		String result="failure";
		session=request.getSession();
		User loginUser=userService.login(user);
		if(loginUser!=null)
			result="success";
		session.setAttribute("user", loginUser);
		System.out.println(result);
		return result;
	}
	public String logout() throws Exception{
		session=request.getSession();
		session.setAttribute("user", null);
		return "next";
	}
	public String setInfo(){
		session=request.getSession();
		if(userService.changeInfo((User)session.getAttribute("user")))
			request.setAttribute("setmsg", "Error to Edit Information");
		else 
			request.setAttribute("setmsg", "Success to Edit Information");
		return "result";
	}
}
