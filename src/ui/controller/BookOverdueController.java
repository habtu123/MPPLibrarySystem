package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import business.CheckoutEntry;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import dataaccess.dto.Overdue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
 
public class BookOverdueController implements Initializable{
	private static Logger logger = Logger.getLogger(BookOverdueController.class.getName()); 
	StringBuilder sb = new StringBuilder();
	@FXML private TextField bookIsbn; 
	@FXML private Label actiontarget; 
	@FXML private TableView<Overdue> overdueTable; 
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	@FXML
	protected void handleSearchBook(ActionEvent event) throws IOException{
		System.out.println("here");
		
		ControllerInterface c  = new SystemController(); 
		String isbn = bookIsbn.getText(); 
		ObservableList<Overdue> overdue = overdueTable.getItems(); 
		
		List<LibraryMember> memebrHavingTheCopy = new ArrayList<LibraryMember>(); 
		try {
			List<LibraryMember> memebers = c.findAllMemebers(); 
			for(LibraryMember memeber: memebers) {
				List<CheckoutEntry> checkoutEntries= memeber.getCheckoutRecord().getCheckoutEntry(); 
				for(CheckoutEntry entry: checkoutEntries) {
					if(isbn.equals(entry.getBook().getBook().getIsbn())) {
						memebrHavingTheCopy.add(memeber); 
					}
				}
			}
			
			memebrHavingTheCopy.forEach(memeber -> {
			 
			 
			 CheckoutRecord record = memeber.getCheckoutRecord(); 
			 List<CheckoutEntry> entries = record.getCheckoutEntry(); 
			 for(CheckoutEntry ent: entries) {
				 System.out.println(ent);
				 overdue.add(new Overdue(ent.getBook().getBook().getIsbn(),
						 ent.getBook().getBook().getTitle(),
						 Integer.valueOf(ent.getBook().getCopyNum()),
						 memeber.getFirstName() + "" + memeber.getLastName(),
						 ent.getBook().getBook().getIsbn(),
						 LocalDate.now().compareTo(ent.getDueDate()) > 0 ? "Overdue" : "Not Overdue")); 
				 
			 }
			});
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
//	
//	@FXML
//	protected void handleSearchMemeber(ActionEvent event) throws IOException{
//    	ControllerInterface c = new SystemController();  
//    	try {
//    	LibraryMember memeber = c.findMemeber(memberId.getText());
//    	CheckoutRecord record = memeber.getCheckoutRecord(); 
//    	
//    	List<CheckoutEntry> checkoutHistory = record.getCheckoutEntry(); 
//    	if(checkoutHistory.size() == 0){
//    		logger.info("Memeber has no record history");
//    	}
//    	sb.append("\nISBN \t Title \t Checkout Date \t Due Date \tOver due\n"); 
//    	sb.append("=====================================\n");
//    	for(CheckoutEntry h : checkoutHistory) { 
//    		sb.append(h.getBook().getBook().getIsbn()); 
//    		sb.append("\t"); 
//    		sb.append(h.getBook().getBook().getTitle()); 
//    		sb.append("\t");
//    		sb.append(h.getCheckoutDate().toString()); 
//    		sb.append("\t");
//    		sb.append(h.getDueDate().toString());
//    		if(LocalDate.now().compareTo(h.getDueDate()) > 0)
//    			sb.append("\tOver Due");
//    		else 
//    			sb.append("\tNot yet");
//    		sb.append("\n");
//    		sb.append("====================================="); 
//    		sb.append("\n"); 
//    	}
//    	
//    	logger.info(sb.toString());
//    	}catch(MemberNotFoundException e) {
//    		actiontarget.setVisible(true);
//    		actiontarget.setText("Sorry, Memeber not found");
//    	}
//    	
//    	
//	}
	
}