package ui.controller;

import java.io.IOException;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ui.LibrarianWindow;
import ui.LoginWindow;
import ui.SystemWindow;
 
public class LoginController {
    @FXML private Label actiontarget;
    @FXML private TextField usernameTxt;
    @FXML private PasswordField passwordField;
    @FXML private GridPane paneScreen;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
//        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
    	
    	if (usernameTxt.getText().trim().equals("")|| passwordField.getText().trim().equals(""))
    	{
    		actiontarget.setVisible(true);
			actiontarget.setText("Please fill your credentials correctly! ");
    	}
    	else {
    	try {
			ControllerInterface c = new SystemController();
			c.login(usernameTxt.getText().trim(), passwordField.getText().trim());
			
			if(SystemController.currentAuth.equals(Auth.ADMIN)) {
			
				if(!SystemWindow.INSTANCE.isInitialized()) {
						SystemWindow.INSTANCE.init();
					}
					LoginWindow.INSTANCE.clear(); 
					SystemWindow.INSTANCE.clear();
					SystemWindow.INSTANCE.show();
					LoginWindow.INSTANCE.hide();
				}
			else if (SystemController.currentAuth.equals(Auth.LIBRARIAN)) {
				if(!LibrarianWindow.INSTANCE.isInitialized()) {
					LibrarianWindow.INSTANCE.init();
				}
				LoginWindow.INSTANCE.clear(); 
				LibrarianWindow.INSTANCE.clear();
				LibrarianWindow.INSTANCE.show();
				LoginWindow.INSTANCE.hide();
			}
			}
    	
    	 catch(LoginException ex) {
				//actiontarget.set(Start.Colors.red);
				actiontarget.setVisible(true);
				actiontarget.setText("Error! " + ex.getMessage());
				//actiontarget.setText("bad ");
    	}
    }
    }
}