package ui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LibrarianWindow extends Stage implements LibWindow {
	
	public static final  LibrarianWindow INSTANCE = new LibrarianWindow();
	private boolean isInitialized = false;
	
	@Override
	public void init() {
		Parent root = null;
		   try {
			root = FXMLLoader.load(getClass().getResource("./view/librarianWindow.fxml"));
			
			Scene scene = new Scene(root, 800,500);
			setScene(scene);
			isInitialized(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean isInitialized() {
		// TODO Auto-generated method stub
		return isInitialized;
	}

	@Override
	public void isInitialized(boolean val) {
		isInitialized = val; 
	}

	public void clear() {
		
	}
}
