package dao;

import org.springframework.stereotype.Repository;

import entity.User;

@Repository
public class UserDAO extends DaoHibernate<User>{
	public boolean register(User user,String userPassword_){
		boolean result=false;
		if(user.getUserName()==null||user.getUserPassword()==null||user.getUserRealName()==null||
		   user.getUserName()==" "||user.getUserPassword()==" "||user.getUserRealName()==" ") 
			result=false;
		else if(!user.getUserPassword().equals(userPassword_)) result=false;
		else if(findByID(User.class, user.getUserName())==null){
			insert(user);
			result=true;
		}
		return result;
	}
	public User login(User user){
		User loginUser=null;
		User user_=findByID(User.class, user.getUserName());
		if(user_!=null&&user.getUserPassword().equals(user_.getUserPassword()))
			loginUser=user_;
		return loginUser;
	}
	public boolean changeInfo(User user){
		boolean result=false;
		if(update(user)==1)
			result=true;
		return result;
	}
}
