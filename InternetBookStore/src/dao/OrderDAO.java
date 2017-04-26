package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import entity.Book;
import entity.Order;
import entity.User;

@Repository
public class OrderDAO extends DaoHibernate<Order> {

	public int putIntoShoppingTrolley(String[] selected,List<Book> books,User user){
		int result=0;
		if(selected==null||selected.length==0)
			result=-1;
		else{
			int select;
			List<Order> orders=new ArrayList<>(0);
			for(int i=0;i<selected.length;i++){
				select=Integer.parseInt(selected[i]);
				Order order=new Order(user,books.get(select));
				orders.add(order);
			}
			result=updateList(orders);
		}
		return result;
	}
	public int deleteFromShoppingTrolley(String[] selected,List<Order> orders) {
		List<Order> delete=new ArrayList<>(0);
		Order order=null;
		for(int i=0,j=0;j<selected.length;i++){
			if(selected[j].equals(orders.get(i).getId())){
				order=orders.get(i);
				delete.add(order);
				orders.remove(order);
				j++;
			}
		}
		return deleteList(delete);
	}
	public int commit(String selected[],List<Order> orders,String address,String tel){
		if(address==null||"".equals(address)||tel==null||"".equals(tel))
			return -1;
		for(int i=0,j=0;j<selected.length;i++){
			if(selected[j].equals(orders.get(i).getId())){
				Order order=orders.get(i);
				order.setDone(true);
				order.setAddress(address);
				order.setTel(tel);
			}
		}
		return updateList(orders);
	}
	public int confirm(String selected[],List<Order> orders) {
		for(int i=0;i<selected.length;i++){
			int select=Integer.parseInt(selected[i]);
			Order order=orders.get(select);
			order.setConfirmed(true);
		}
		return updateList(orders);
	}
	public int complete(String selected[],List<Order> orders) {
		for(int i=0;i<selected.length;i++){
			int select=Integer.parseInt(selected[i]);
			Order order=orders.get(select);
			order.setConfirmed(true);
		}
		return updateList(orders);
	}
	@SuppressWarnings("deprecation")
	public List<Order> getOrders(String id,String userName,String bookID,String commited,String confirmed,String done,String time0,String time1){
		String hQL="from Orders";
		List<Object> params=new ArrayList<>(0);
		if(id!=null&&!"".equals(id)){
			hQL+="and id=? ";
			params.add(id);
		}
		if(userName!=null&&!"".equals(userName)){
			hQL+="and username=? ";
			params.add(userName);
		}
		if(bookID!=null&&!"".equals(bookID)){
			hQL+="and bookid=? ";
			params.add(bookID);
		}
		if(commited!=null&&!"".equals(commited)){
			hQL+="and confirmed=? ";
			params.add("ÊÇ".equals(commited)?true:false);
		}
		if(confirmed!=null&&!"".equals(confirmed)){
			hQL+="and confirmed=? ";
			params.add("ÊÇ".equals(confirmed)?true:false);
		}
		if(done!=null&&!"".equals(done)){
			hQL+="and done=? ";
			params.add("ÊÇ".equals(confirmed)?true:false);
		}
		if(time0!=null&&!"".equals(time0)){
			hQL+="and timestamp>=? ";
			params.add(Date.parse(time0));
		}
		if(time1!=null&&!"".equals(time1)){
			hQL+="and timestamp<=? ";
			params.add(Date.parse(time1));
		}
		List<Order> orders=find(hQL, params);
		return orders;
	}
		
}
