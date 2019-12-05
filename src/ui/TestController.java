package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TestController {
	
	@FXML private BorderPane contentBars;
    
    @FXML protected void addNewMemeberAction(ActionEvent event) throws IOException {
//        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
    	GridPane pane = FXMLLoader.load(getClass().getResource("Login.fxml"));
    	contentBars.setCenter(pane);
    	
    }
    
    @FXML protected void addNewCopyAction(ActionEvent event) throws IOException {
//      actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
  	GridPane pane = FXMLLoader.load(getClass().getResource("Test1.fxml"));
  	contentBars.setCenter(pane);
  	
  }
    
    
}