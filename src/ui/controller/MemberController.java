package ui.controller;

import java.io.IOException;

import business.Address;
import business.ControllerInterface;
import business.LibraryMember;
import business.LibrarySystemException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MemberController {
	@FXML private TextField streetTxt;
	@FXML private TextField cityTxt;
	@FXML private TextField stateTxt;
	@FXML private TextField zipTxt;
	@FXML private TextField memberIdTxt;
	@FXML private TextField firstnameTxt;
	@FXML private TextField lastnameTxt;
	@FXML private TextField phonoTxt;
	
	
	@FXML protected void handleAddNewmemberAction(ActionEvent event) throws IOException {
      System.out.println("Add Book copy");
      if(emptyFields(new String[] {memberIdTxt.getText().trim(), firstnameTxt.getText().trim(), 
    		  lastnameTxt.getText().trim(), phonoTxt.getText().trim()})) {
    	  throw new IOException("Empty LibraryMember fields");
      }
      
      if(emptyFields(new String[] {streetTxt.getText().trim(), cityTxt.getText().trim(), 
    		  stateTxt.getText().trim(), zipTxt.getText().trim()})) {
    	  throw new IOException("Empty addreess fields");
      }
      
      LibraryMember newMember = new LibraryMember(memberIdTxt.getText().trim(), firstnameTxt.getText().trim(), 
    		  lastnameTxt.getText().trim(), phonoTxt.getText().trim(), 
    		  new Address(streetTxt.getText(), cityTxt.getText(), stateTxt.getText(), zipTxt.getText()));

      ControllerInterface c = new SystemController();
      try {
		c.addMember(newMember);
	} catch (LibrarySystemException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	}

	private boolean emptyFields(String[] fields) {
		for (String field : fields) {
			if(field.isEmpty())
				return true;
		}
		return false;
	}
  
}