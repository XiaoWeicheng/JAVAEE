package service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.OrderDAO;
import entity.Book;
import entity.Order;
import entity.User;


@Service
public class OrderService {

	@Resource
	private OrderDAO orderDAO;
	public OrderDAO getOrderDAO() {
		return orderDAO;
	}
	public void setOrderDAO(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	public int putIntoShoppingTrolley(String[] selected,List<Book> books,User user){
		return orderDAO.putIntoShoppingTrolley(selected, books, user);
	}
	public int deleteFromShoppingTrolley(String[] selected,List<Order> orders) {
		return orderDAO.deleteFromShoppingTrolley(selected, orders);
	}
	public int commit(String selected[],List<Order> orders,String address,String tel){
		return orderDAO.commit(selected, orders, address, tel);
	}
	public int confirm(String selected[],List<Order> orders) {
		return orderDAO.confirm(selected, orders);
	}
	public int complete(String selected[],List<Order> orders) {
		return orderDAO.complete(selected, orders);
	}
	public List<Order> getOrders(String id,String userName,String bookID,String commited,String confirmed,String done,String time0,String time1){
		return orderDAO.getOrders(id, userName, bookID,commited, confirmed, done, time0, time1);
	}
	
}
