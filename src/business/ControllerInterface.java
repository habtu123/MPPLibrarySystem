package business;

import java.util.List;

import business.exceptions.BookNotFoundException;
import business.exceptions.MemberNotFoundException;


public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	public void addBookCopy(String ISBN) throws LibrarySystemException; 
	public void addMember(LibraryMember member) throws LibrarySystemException; 
	public List<CheckoutEntry> checkoutBook(String memberId, String isbn) throws BookNotFoundException, MemberNotFoundException;
	public LibraryMember findMemeber(String memberId) throws MemberNotFoundException; 
	public void addBook(Book book) throws LibrarySystemException; 
	public List<Book> findAllBooks(); 
	public Book findBook(String isbn) throws BookNotFoundException; 
	public List<LibraryMember> findAllMemebers(); 
}
