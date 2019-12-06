package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import ui.controller.BookController;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord checkoutRecord; 
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add, CheckoutRecord checkoutRecord) {
		super(fname,lname, tel, add);
		this.memberId = memberId;	
		this.checkoutRecord = checkoutRecord; 
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	public CheckoutRecord getCheckoutRecord() {
		return checkoutRecord;
	}


	public void setCheckoutRecord(CheckoutRecord checkoutRecord) {
		this.checkoutRecord = checkoutRecord;
	}


	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress() +" Checkout Record:"+getCheckoutRecord().toString();
	}

	public void checkout(BookCopy bookCopy, LocalDate todayDate, long checkoutLength) {
		bookCopy.setAvailable(false);
		CheckoutEntry checkoutEntry = new CheckoutEntry(bookCopy, LocalDate.now(), LocalDate.now().plusDays(checkoutLength)); 
		CheckoutRecord checkoutRecord = new CheckoutRecord(); 
		checkoutRecord.addCheckoutEntry(checkoutEntry);
	}
	private static final long serialVersionUID = -2226197306790714013L;
}
