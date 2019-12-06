package ui.controller;

import java.io.IOException;
import java.util.logging.Logger;

import business.ControllerInterface;
import business.SystemController;
import business.exceptions.BookNotFoundException;
import business.exceptions.MemberNotFoundException;
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
    	ControllerInterface c = new SystemController(); 
    	
    	try {
    		Boolean checkedout = c.checkoutBook(memeberID.getText(), bookISBN.getText());
    		if(checkedout)
    			logger.info("Book checked out successfully");
		} catch (BookNotFoundException e) {
			actiontarget.setVisible(true);
			actiontarget.setText(e.getMessage()); 
		} catch (MemberNotFoundException e) {
			actiontarget.setVisible(true);
			actiontarget.setText(e.getMessage()); 
		} 
    }
    
    
   
    
}