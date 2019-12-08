package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import business.CheckoutEntry;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import business.exceptions.MemberNotFoundException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
 
public class PrintCheckoutRecordController implements Initializable{
	private static Logger logger = Logger.getLogger(PrintCheckoutRecordController.class.getName()); 
	StringBuilder sb = new StringBuilder();
	@FXML private TextField memberId; 
	@FXML private Label actiontarget; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
   
	
	@FXML
	protected void handleCheckoutPrint(ActionEvent event) throws IOException{
		System.out.println("here");
	}
	
	@FXML
	protected void handleSearchMemeber(ActionEvent event) throws IOException{
    	ControllerInterface c = new SystemController();  
    	try {
    	LibraryMember memeber = c.findMemeber(memberId.getText());
    	CheckoutRecord record = memeber.getCheckoutRecord(); 
    	
    	List<CheckoutEntry> checkoutHistory = record.getCheckoutEntry(); 
    	if(checkoutHistory.size() == 0){
    		logger.info("Memeber has no record history");
    	}
    	sb.append("\nISBN \t Title \t Checkout Date \t Due Date \tOver due\n"); 
    	sb.append("=====================================\n");
    	for(CheckoutEntry h : checkoutHistory) { 
    		sb.append(h.getBook().getBook().getIsbn()); 
    		sb.append("\t"); 
    		sb.append(h.getBook().getBook().getTitle()); 
    		sb.append("\t");
    		sb.append(h.getCheckoutDate().toString()); 
    		sb.append("\t");
    		sb.append(h.getDueDate().toString());
    		if(LocalDate.now().compareTo(h.getDueDate()) > 0)
    			sb.append("\tOver Due");
    		else 
    			sb.append("\tNot yet");
    		sb.append("\n");
    		sb.append("====================================="); 
    		sb.append("\n"); 
    	}
    	
    	logger.info(sb.toString());
    	}catch(MemberNotFoundException e) {
    		actiontarget.setVisible(true);
    		actiontarget.setText("Sorry, Memeber not found");
    	}
    	
    	
	}
	
}