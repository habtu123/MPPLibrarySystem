package business;

import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord {
	public static final CheckoutRecord INSTANCE = new CheckoutRecord(); 
	private List<CheckoutEntry> checkoutEntry = new ArrayList<CheckoutEntry>(); 
	
	
	CheckoutRecord() {
	}
	
	public void addCheckoutEntry(CheckoutEntry checkoutEntry) {
		this.checkoutEntry.add(checkoutEntry); 
	}
}
