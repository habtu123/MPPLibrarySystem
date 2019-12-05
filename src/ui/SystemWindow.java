package ui;

import business.SystemController;
import dataaccess.Auth;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SystemWindow extends Stage implements LibWindow {
	public static final SystemWindow INSTANCE = new SystemWindow(); 
	private boolean isInitialized = false;
	
	private Text messageBar = new Text();
	private Button button = new Button(); 
	@Override
	public void init() {
		Parent root = null;
		   try {
			root = FXMLLoader.load(getClass().getResource("Test.fxml"));
			Scene scene = new Scene(root, 800, 500); 
			
			if(SystemController.currentAuth.equals(Auth.BOTH)) {
				 //Button both = (Button)root.lookup("#sidebar-button");
//				 VBox admin = (VBox)root.lookup("#admin");
//				 VBox lib = (VBox)root.lookup("#lib");
//				// System.out.println(both.getChildren().size());
//				// both.setVisible(false);
//				 //admin.setVisible(false);
//				 
//				 admin.getChildren().removeAll(); 
//				 lib.getChildren().removeAll(); 
				System.out.println("Full authority");
			}
			scene.getStylesheets().add("bootstrapfx.css"); 
			
			setScene(scene);
			isInitialized(true); 
		   }catch(Exception ex) {
			   
		   }
		   
		   System.out.println(SystemController.currentAuth);
		
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
