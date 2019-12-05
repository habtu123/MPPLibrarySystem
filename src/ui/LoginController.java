package ui;

import java.io.IOException;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
 
public class LoginController {
    @FXML private Text actiontarget;
    @FXML private TextField usernameTxt;
    @FXML private PasswordField passwordField;
    @FXML private GridPane paneScreen;
    
    @FXML protected void handleSubmitButtonAction(ActionEvent event) throws IOException {
//        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
    	
    	try {
			ControllerInterface c = new SystemController();
			c.login(usernameTxt.getText().trim(), passwordField.getText().trim());
//			messageBar.setFill(Start.Colors.green);
//     	    messageBar.setText("Login successful");
     	    
			//GridPane pane = FXMLLoader.load(getClass().getResource("Test.fxml"));
			//paneScreen.getChildren().setAll(pane);
		if(!SystemWindow.INSTANCE.isInitialized()) {
				SystemWindow.INSTANCE.init();
			}
			SystemWindow.INSTANCE.clear(); 
			SystemWindow.INSTANCE.show();
			LoginWindow.INSTANCE.hide();
		} catch(LoginException ex) {
			actiontarget.setFill(Start.Colors.red);
			actiontarget.setText("Error! " + ex.getMessage());
			//actiontarget.setText("bad ");
		}
    }
}