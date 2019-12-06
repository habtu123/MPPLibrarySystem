package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord  implements Serializable {
	private static final long serialVersionUID = 3665880920647848288L;
	//public static final CheckoutRecord INSTANCE = new CheckoutRecord(); 
	private List<CheckoutEntry> checkoutEntry = new ArrayList<CheckoutEntry>(); 
	
	
	public CheckoutRecord() {
	}
	
	public void addCheckoutEntry(CheckoutEntry checkoutEntry) {
		this.checkoutEntry.add(checkoutEntry); 
	}
}
