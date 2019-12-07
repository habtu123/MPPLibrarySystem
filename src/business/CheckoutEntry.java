package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class CheckoutEntry  implements Serializable {
	private static final long serialVersionUID = 3665880920647848288L;
	 private BookCopy book; 
	 private LocalDate checkoutDate; 
	 private LocalDate dueDate; 
	 
	 CheckoutEntry(BookCopy book, LocalDate checkoutDat, LocalDate dueDate){
		 this.book = book; 
		 this.checkoutDate = checkoutDat; 
		 this.dueDate = dueDate; 
	 }

	public BookCopy getBook() {
		return book;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
	 
	 
	 
}
