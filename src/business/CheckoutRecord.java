package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord  implements Serializable {
	private static final long serialVersionUID = 4L;
	//public static final CheckoutRecord INSTANCE = new CheckoutRecord(); 
	private List<CheckoutEntry> checkoutEntry = new ArrayList<>(); 
	
	
	public CheckoutRecord() {
		
	}
	
	public void addCheckoutEntry(CheckoutEntry checkoutEntry) {
		this.checkoutEntry.add(checkoutEntry); 
	}
	public List<CheckoutEntry> getCheckoutEntry() {
		return checkoutEntry;
	}

	@Override
	public String toString() {
		return "CheckoutRecord [checkoutEntry=" + checkoutEntry + "]";
	}
	
	
}
