package ui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ui.LoginWindow;
import ui.Start;
import ui.SystemWindow;

public class LibrarianController {
	
	@FXML private BorderPane contentBars;
 
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
//      actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
  	System.out.println("logout pressed");
  	SystemWindow.INSTANCE.clear();
  	if(LoginWindow.INSTANCE.isInitialized())
  		LoginWindow.INSTANCE.clear();
  	Start.hideAllWindows();
		Start.primStage().show();
		
  	}
   
    
}