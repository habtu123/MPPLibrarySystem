package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import business.exceptions.BookNotFoundException;
import business.exceptions.MemberNotFoundException;
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
	public List<CheckoutEntry> checkoutBook(String memberId, String isbn) throws BookNotFoundException, MemberNotFoundException {
		loger.info("Start book checkout process");
		loger.info("....................");
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memeberList = da.readMemberMap(); 
		
		if(!memeberList.containsKey(memberId))
			throw new MemberNotFoundException("Sorry, Library Memeber not found"); 
		
		LibraryMember member = memeberList.get(memberId); 
		//check if the book exists
		Book book = findBook(isbn); 
		
		//check if the book is available for checkout
		if(!book.isAvailable())
			throw new BookNotFoundException("Book is not availbale"); 
		
		BookCopy bookTobeCheckedOut = book.getNextAvailableCopy(); 
		loger.info("book copy:"+bookTobeCheckedOut.toString()); 
		int maxCheckeoutDays = book.getMaxCheckoutLength(); 
		
		List<CheckoutEntry> checkoutHistory = member.checkout(bookTobeCheckedOut, LocalDate.now(), (long)maxCheckeoutDays);
		da.saveNewMember(member);
		da.saveNewBook(book);
		loger.info("....................");
		loger.info("End book checkout process.......");
		loger.info("checkout complete: "+member.getFirstName() +"Book:"+member.getCheckoutRecord().getCheckoutEntry().get(0) );
		return checkoutHistory; 
	}
	
	@Override
	public Book findBook(String isbn) throws BookNotFoundException {
		List<String> books = allBookIds(); 
		if(!books.contains(isbn)) {
			throw new BookNotFoundException("Book not Found"); 
		}
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> bookSet = da.readBooksMap(); 
		Book book = bookSet.get(isbn); 
		
		return book;
	}
	@Override
	public LibraryMember findMemeber(String memberId) throws MemberNotFoundException {
		LibraryMember libraryMember;
		DataAccess da = new DataAccessFacade();
		HashMap<String, LibraryMember> memeberList = da.readMemberMap(); 
		
		if(!memeberList.containsKey(memberId))
			throw new MemberNotFoundException("Sorry, Library Memeber not foudd");
	
		return memeberList.get(memberId);
	}
	@Override
	public List<Book> findAllBooks() {
		DataAccess da = new DataAccessFacade();
		List<Book> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().values());
		return retval;
	}
	@Override
	public List<LibraryMember> findAllMemebers() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> memebrs = new ArrayList<LibraryMember>(); 
		HashMap<String, LibraryMember> allMemebers= da.readMemberMap();
		allMemebers.forEach((k,v)->memebrs.add(v));
		return memebrs;
	}
}
