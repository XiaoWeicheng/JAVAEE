package service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.BookDAO;
import entity.Book;

@Service()
public class BookService {

	@Resource
	private BookDAO bookDAO;

	public BookService() {
		bookDAO=new BookDAO();
	}
	public BookDAO getBookDAO() {
		return bookDAO;
	}
	public void setBookDAO(BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}
	public List<Book> queryBook(String id,String name,String class_,String author,String press,String price0,String price1,String discount0,String discount1){
		return bookDAO.queryBook(id, name, class_, author, press, price0, price1, discount0, discount1);
	}
	public boolean uploadImage(Book book){
		return bookDAO.uploadImage(book);
	}
	public int setDisCount(List<Book> books,int BookIndex,String discounts){
		return bookDAO.setDisCount(books,BookIndex,discounts);
	}
	public boolean addBook(Book book){
		return bookDAO.addBook(book);
	}
}
