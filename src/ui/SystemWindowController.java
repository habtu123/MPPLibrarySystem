package ui;

import java.io.IOException;

import business.ControllerInterface;
import business.LoginException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
 
public class SystemWindowController {
    @FXML private Label actiontarget;
    @FXML private TextField usernameTxt;
    @FXML private PasswordField passwordField;
    @FXML private GridPane paneScreen;
    @FXML private VBox vboxContent; 
    
    @FXML protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
//        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
    	SystemWindow.INSTANCE.clear();
    	if(LoginWindow.INSTANCE.isInitialized())
    		LoginWindow.INSTANCE.clear();
    	Start.hideAllWindows();
		Start.primStage().show();
		
    	}
    
    @FXML protected void handleAddNewMemberAction(ActionEvent event) throws IOException{
    	Button button = new Button("Go back"); 
    	System.out.println(vboxContent.getChildren().size());
    	vboxContent.getChildren().clear();
    	vboxContent.getChildren().add(button);
    	System.out.println("Add Memeber has been clicked");
    }
}