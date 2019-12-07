package ui.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import business.CheckoutEntry;
import business.ControllerInterface;
import business.SystemController;
import business.exceptions.BookNotFoundException;
import business.exceptions.MemberNotFoundException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ui.LoginWindow;
import ui.Start;
import ui.SystemWindow;

public class LibrarianController {
	private static Logger logger = Logger.getLogger(LibrarianController.class.getName()); 
	@FXML private BorderPane contentBars;
	@FXML private TextField bookISBN; 
	@FXML private TextField memeberID; 
	@FXML private Label actiontarget; 
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
//      actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
  	System.out.println("logout pressed");
  	SystemWindow.INSTANCE.clear();
  	if(LoginWindow.INSTANCE.isInitialized())
  		LoginWindow.INSTANCE.clear();
  	Start.hideAllWindows();
		Start.primStage().show();
		
  	}
    
    @FXML protected void handleAddNewMemeberBoth(ActionEvent event) throws IOException{
    	
    	GridPane pane = FXMLLoader.load(getClass().getResource("../view/checkoutBook.fxml"));
    	contentBars.setCenter(pane);
    }
    
    @FXML protected void handleCheckoutBook(ActionEvent event) throws IOException{
    	logger.info("Start book checkout"+memeberID);
    	DataAccess da = new DataAccessFacade(); 
    	logger.info("original "+da.readBooksMap()); 
    	ControllerInterface c = new SystemController();    	
    	try {
    		List<CheckoutEntry> checkoutHistory = c.checkoutBook(memeberID.getText(), bookISBN.getText());
    		if(!checkoutHistory.isEmpty()) {
    			logger.info("Book checked out successfully");
    			logger.info("checkout histroy"+checkoutHistory.size());
    			
    			for(CheckoutEntry ch:checkoutHistory)
    			{
    				System.out.println("Checked out book :"+ch.getBook().getBook().getTitle());
    				System.out.println("Due date"+ ch.getCheckoutDate());
    			}
    		}
		} catch (BookNotFoundException e) {
			actiontarget.setVisible(true);
			actiontarget.setText(e.getMessage()); 
		} catch (MemberNotFoundException e) {
			actiontarget.setVisible(true);
			actiontarget.setText(e.getMessage()); 
		} 
    }
    

	@FXML
	protected void showAllMemberidAction(ActionEvent event) throws IOException {
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/AllMember.fxml"));
		contentBars.setCenter(pane);
	}
	
	@FXML
	protected void listALlBooks(ActionEvent event) throws IOException {
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/AllBooks.fxml"));
		contentBars.setCenter(pane);
	
	}
    
   
    
}