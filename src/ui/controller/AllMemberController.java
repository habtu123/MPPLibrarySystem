package ui.controller;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import business.ControllerInterface;
import business.SystemController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
 
public class AllMemberController implements Initializable{
	StringBuilder sb = new StringBuilder();
    @FXML private Label actiontarget;
    @FXML private TextArea memberArea;
    
    {
    	ControllerInterface ci = new SystemController();
		List<String> ids = ci.allMemberIds();
		Collections.sort(ids);
		System.out.println(ids);
		
		for(String s: ids) {
			sb.append(s + "\n");
		}
		
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		memberArea.setText(sb.toString());
		System.out.println(sb.toString());
	}
   
}