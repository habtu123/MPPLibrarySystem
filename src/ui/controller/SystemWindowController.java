package ui.controller;

import java.io.IOException;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import ui.LoginWindow;
import ui.Start;
import ui.SystemWindow;
 
public class SystemWindowController {
    @FXML private Label actiontarget;
    @FXML private TextField usernameTxt;
    @FXML private PasswordField passwordField;
    @FXML private GridPane paneScreen;
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
//        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
    	System.out.println("logout pressed");
    	usernameTxt.setText("");
    	passwordField.setText("");
    	SystemWindow.INSTANCE.clear();
    	if(LoginWindow.INSTANCE.isInitialized())
    		LoginWindow.INSTANCE.clear();
    		LoginWindow.INSTANCE.isInitialized(false);
    	Start.hideAllWindows();
		Start.primStage().show();
		
    	}
    
}