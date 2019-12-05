package business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	
	
}
