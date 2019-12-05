package ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow extends Stage implements LibWindow {
	public static final LoginWindow INSTANCE = new LoginWindow();
	
	private boolean isInitialized = false;
	
	public boolean isInitialized() {	
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private Text messageBar = new Text();
	private TextField usernameTxt = new TextField(); 
	private PasswordField passwordField = new PasswordField(); 
	public void clear() {
		messageBar.setText("");
		usernameTxt.clear();
		passwordField.clear();
	}
	
	/* This class is a singleton */
    private LoginWindow () {}
    
   public void init() { 
	   Parent root = null;
	   try {
		root = FXMLLoader.load(getClass().getResource("./view/Login.fxml"));
		
		Scene scene = new Scene(root, 800,500);
		setScene(scene);
		isInitialized(false);
	} catch (IOException e) {
		e.printStackTrace();
	}
	   
        
    }
	
	
}
