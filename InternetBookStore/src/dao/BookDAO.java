package dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import entity.Book;

@Repository
public class BookDAO extends DaoHibernate<Book>{

	public List<Book> queryBook(String id,String name,String class_,String author,String press,String price0,String price1,String discount0,String discount1){
		String hQL="from Book";
		List<Object> params=new ArrayList<>(0);
		if(id!=null&&!"".equals(id)){
			hQL+=" where bookid=? ";
			params.add(id);
		}
		if(name!=null&&"".equals(name)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookname=? ";
			params.add(name);
		}
		if(class_!=null&&"".equals(class_)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookclass=? ";
			params.add(class_);
		}
		if(author!=null&&"".equals(author)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookauthor=? ";
			params.add(author);
		}
		if(press!=null&&"".equals(press)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookpress=? ";
			params.add(press);
		}
		if(price0!=null&&"".equals(price0)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookprice>=? ";
			params.add(Double.parseDouble(price0));
		}
		if(price1!=null&&"".equals(price1)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookprice<=? ";
			params.add(Double.parseDouble(price1));
		}
		if(discount0!=null&&"".equals(discount0)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookdiscount>=? ";
			params.add(Double.parseDouble(discount0));
		}
		if(discount1!=null&&"".equals(discount1)){
			if(hQL.charAt(hQL.length()-1)!=' ')
				hQL+=" where";
			else
				hQL+="and";
			hQL+=" bookdiscount<=? ";
			params.add(Double.parseDouble(discount1));
		}
		List<Book> books=find(hQL, params.toArray());
		return books;
	}
	public boolean uploadImage(Book book){
		boolean result=false;
		if(update(book)==1)
			result=true;
		return result;
	}
	public int setDisCount(List<Book> books,int bookIndex,String discounts){
		int result=0;
			if(books.get(bookIndex).getBookDiscount()!=Double.parseDouble(discounts))
				books.get(bookIndex).setBookDiscount(Double.parseDouble(discounts));
		result=update(books.get(bookIndex));
		return result;
	}
	public boolean addBook(Book book){
		boolean result=false;
		if(findByID(Book.class, book.getBookID())==null){
			insert(book);
			result=true;
		}
		return result;
	}
}
