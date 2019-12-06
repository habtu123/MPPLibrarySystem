package ui;

import business.SystemController;
import dataaccess.Auth;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SystemWindow extends Stage implements LibWindow {
	public static final SystemWindow INSTANCE = new SystemWindow(); 
	private boolean isInitialized = false;
	
	private Text messageBar = new Text();
	private Button button = new Button(); 
	//private Label userDisplay =new Label();
	@Override
	public void init() {
		Parent root = null;
		   try {
			root = FXMLLoader.load(getClass().getResource("./view/systemWindow.fxml"));
			Scene scene = new Scene(root, 1000, 500); 
			
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
				Label authority=(Label)root.lookup("#userDisplay");
				authority.setText(SystemController.currentAuth.toString());
				System.out.println("Full authority");
			}
			scene.getStylesheets().add("./view/style/bootstrapfx.css"); 
			
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
