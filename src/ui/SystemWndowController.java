package ui;

import java.io.IOException;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SystemWndowController {
//	    @FXML private Label actiontarget;
//	    @FXML private TextField usernameTxt;
//	    @FXML private PasswordField passwordField;
//	    @FXML private GridPane paneScreen;
	    @FXML private Button addNewMemeberBoth;
	    @FXML private Button addBookCopy;
	    @FXML private Button textArea;
	    
	    @FXML protected void handleaddNewMemeberButtonAction(ActionEvent event) throws IOException, LoginException {
//	        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
	    	

	    	textArea.setText("Hello!");
	    	}
	    

}
