package entity;

import java.util.Date;

public class Order {

	private int id;
	private User user;
	private Book book;
	private boolean committed=false;
	private String address,tel;
	private boolean confirmed=false;
	private boolean done=false;
	private Date timeStamp;
	public Order() {
	}
	public Order(User user, Book book) {
		this.user = user;
		this.book = book;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public boolean getCommitted() {
		return committed;
	}
	public void setCommitted(boolean committed) {
		this.committed = committed;
	}
	public boolean getConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean ok) {
		this.confirmed = ok;
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
	public boolean getDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
