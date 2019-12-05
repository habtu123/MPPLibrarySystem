package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SystemWindow extends Stage implements LibWindow {
	public static final SystemWindow INSTANCE = new SystemWindow(); 
	private boolean isInitialized = false;
	
	private Text messageBar = new Text();
	@Override
	public void init() {
		Parent root = null;
		   try {
			root = FXMLLoader.load(getClass().getResource("Test.fxml"));
			Scene scent = new Scene(root, 800, 500); 
			setScene(scent);
			isInitialized(true); 
		   }catch(Exception ex) {
			   
		   }
		
	}

	@Override
	public boolean isInitialized() {
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {		
		isInitialized = val; 		
	}
	
	private SystemWindow() {}
	
	public void clear() {
		messageBar.setText("");
	}
	
	
}
