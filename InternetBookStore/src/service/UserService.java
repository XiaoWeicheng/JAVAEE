package service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.UserDAO;
import entity.User;

@Service
public class UserService {

	@Resource
	private UserDAO userDAO;
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	public boolean register(User user,String userPassword_){
		return userDAO.register(user, userPassword_);
	}
	public User login(User user){
		return userDAO.login(user);
	}
	public boolean changeInfo(User user){
		return userDAO.changeInfo(user);
	}
}
