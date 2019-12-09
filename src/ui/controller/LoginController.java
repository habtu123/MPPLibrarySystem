package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import ui.AdminWindow;
import ui.LibrarianWindow;
import ui.LoginWindow;
import ui.Start;
import ui.SystemWindow;
import util.LibrarianUtil;
 
public class LoginController implements Initializable{
    @FXML private Label actiontarget;
    @FXML private TextField usernameTxt;
    @FXML private PasswordField passwordField;
    @FXML private GridPane paneScreen;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
//        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());

    	
    	if (LibrarianUtil.emptyFields(new String[] {usernameTxt.getText().trim(), passwordField.getText().trim()}))
    	{
    		actiontarget.setVisible(true);
			actiontarget.setText("Please fill your credentials correctly! ");
    	}
    	else {
    	try {
			ControllerInterface c = new SystemController();
			c.login(usernameTxt.getText().trim(), passwordField.getText().trim());
			
			if(SystemController.currentAuth.equals(Auth.ADMIN)) {
			
				if(!AdminWindow.INSTANCE.isInitialized()) {
						AdminWindow.INSTANCE.init();
					}
					LoginWindow.INSTANCE.clear(); 
					AdminWindow.INSTANCE.clear();
					AdminWindow.INSTANCE.show();
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
			
			else if(SystemController.currentAuth.equals(Auth.BOTH)) {
				
				if(!SystemWindow.INSTANCE.isInitialized()) {
						SystemWindow.INSTANCE.init();
					}
					LoginWindow.INSTANCE.clear(); 
					SystemWindow.INSTANCE.clear();
					SystemWindow.INSTANCE.show();
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
    
    @FXML protected void handleBackButtonAction(ActionEvent action) {
    	Start.hideAllWindows();
		Start.primStage().show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	paneScreen.setStyle("-fx-background-image: url('ui/view/library2.jpeg')");
	}
}