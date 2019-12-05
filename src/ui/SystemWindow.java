package ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SystemWindow extends Stage implements LibWindow {
	public static final SystemWindow INSTANCE = new SystemWindow(); 
	private boolean isInitialized = false;
	
	private Text messageBar = new Text();
	@Override
	public void init() {
		GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Button logout = new Button("Log out"); 
        
        Text scenetitle = new Text("Second Window");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);
        
        grid.add(logout, 0, 1);
        
        logout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Start.hideAllWindows();
        		Start.primStage().show();
			}
		});
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
		
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
	
	private SystemWindow() {}
	
	public void clear() {
		messageBar.setText("");
	}
	
	
}
