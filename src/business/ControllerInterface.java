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
	public Boolean checkoutBook(String memberId, String isbn) throws BookNotFoundException, MemberNotFoundException;
	public LibraryMember findMemeber(String memberId) throws MemberNotFoundException; 
	public void addBook(Book book) throws LibrarySystemException; 
	
}
