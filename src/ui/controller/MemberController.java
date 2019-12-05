package ui.controller;

import java.io.IOException;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MemberController {
	
	@FXML protected void handleAddBookCopy(ActionEvent event) throws IOException {
      System.out.println("Add Book copy");
      ControllerInterface c = new SystemController();
      
      try {
		c.addBookCopy("23-11451");
	} catch (LibrarySystemException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	}
  
}
