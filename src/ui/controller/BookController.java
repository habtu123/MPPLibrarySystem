package ui.controller;

import java.io.IOException;

import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class BookController {
	@FXML protected TextField bookISBN; 
	
	@FXML protected void handleAddBookCopy(ActionEvent event) throws IOException {
      System.out.println("Add New book");
      ControllerInterface c = new SystemController();
      
      try {
    	  if(bookISBN.getText().trim().isEmpty())
    		  	throw new LibrarySystemException("Invalid ISBN"); 
    	  else 
    		  c.addBookCopy(bookISBN.getText());
	} catch (LibrarySystemException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	}
  
}
