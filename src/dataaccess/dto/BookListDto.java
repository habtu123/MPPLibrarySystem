package dataaccess.dto;

public class BookListDto {
	String isbn; 
	String bookTitle;
	public BookListDto(String isbn, String bookTitle) {
		super();
		this.isbn = isbn;
		this.bookTitle = bookTitle;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	} 
	
	
}
