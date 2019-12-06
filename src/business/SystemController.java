package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	private final static Logger loger = Logger.getLogger(SystemController.class.getName());
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		System.out.println(currentAuth.toString());
		
	}
	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	@Override
	public void addBookCopy(String ISBN) throws LibrarySystemException {
		loger.info("Start addBooCopy .....");
		List<String> books = allBookIds(); 
		if(!books.contains(ISBN)) {
			throw new LibrarySystemException("Book not Found"); 
		}
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookSet = da.readBooksMap(); 
		Book book = bookSet.get(ISBN); 
		loger.info("book to be added"+book.getIsbn());
		book.addCopy();
		loger.info("End addBooCopy started.....");
		
	}
	@Override
	public void addMember(LibraryMember newMember) throws LibrarySystemException {
		loger.info("Start addMember .....");

		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> members = da.readMemberMap();
		if(existMember(members, newMember)) {
			throw new LibrarySystemException("User already exist"); 
		}

		loger.info("member to be added "+newMember.getMemberId());
		da.saveNewMember(newMember);
		loger.info("End addMember started.....");
	}
	
	private boolean existMember(HashMap<String, LibraryMember> members, LibraryMember newMember) {
		LibraryMember libraryMember;
		for (Map.Entry<String, LibraryMember> entry : members.entrySet())  {
            libraryMember = entry.getValue();
            if(libraryMember.getMemberId().equals(newMember.getMemberId()))
            	return true;
		}
		return false;
	}
	@Override
	public void addBook(Book book) throws LibrarySystemException {
		loger.info("Start addMember .....");

		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> books = da.readBooksMap();
		if(existBook(books, book)) {
			throw new LibrarySystemException("Book already exist"); 
		}

		loger.info("book to be added "+book.getIsbn());
		da.saveNewBook(book);
		loger.info("End addBoo started.....");
	}
	
	private boolean existBook(HashMap<String, Book> books, Book newBook) {
		Book bookAux;
		for (Map.Entry<String, Book> entry : books.entrySet())  {
            bookAux = entry.getValue();
            if(bookAux.getIsbn().equals(newBook.getIsbn()))
            	return true;
		}
		return false;
	}
}
