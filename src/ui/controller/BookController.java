package ui.controller;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;

import business.Address;
import business.Author;
import business.Book;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibrarySystemException;
import business.SystemController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import util.LibrarianUtil;

public class BookController {
	Logger logger = Logger.getLogger(BookController.class.getName()); 
	@FXML protected TextField bookISBN; 
	@FXML protected Label actiontarget; 
	@FXML protected void handleAddBookCopy(ActionEvent event) throws IOException {
      System.out.println("Add New book");
      ControllerInterface c = new SystemController();
      
      try {
    	  if(bookISBN.getText().trim().isEmpty()) {
    		  actiontarget.setVisible(true);
    		  actiontarget.setText("Invalid ISBN");
    	  }
    	  else 
    		  c.addBookCopy(bookISBN.getText());
	} catch (LibrarySystemException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	}
	
	//Book elements
	@FXML protected TextField isbnTxt; 
	@FXML protected TextField titleTxt; 
	@FXML protected TextField checkoutTxt; 
	@FXML protected TextField copiesTxt; 
	
	@FXML private TableView<Author> authorsTable;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField bioField;
	
    /**
     * add new author to the table
     */
	@FXML protected void addAuthors(ActionEvent event) {
		
        ObservableList<Author> data = authorsTable.getItems();
        data.add(new Author(firstNameField.getText(), lastNameField.getText(), phoneField.getText(), new Address("101 S. Main", "Fairfield", "IA", "52556"), bioField.getText()));
        
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        bioField.setText("");
    }
	
	@FXML protected void handleAddBook(ActionEvent event) throws IOException {
	      System.out.println("Add New book");
	      
	      if(LibrarianUtil.emptyFields(new String[] {isbnTxt.getText().trim(), titleTxt.getText().trim(), checkoutTxt.getText().trim(), copiesTxt.getText().trim()})) {
	    	  actiontarget.setText("Empty book fields");
	    	  actiontarget.setStyle("-fx-text-fill: red;");
	    	  throw new IOException("Empty book fields");
	      }
	      
	      if(!LibrarianUtil.isNumericAtLeastOne(copiesTxt.getText().trim())) {
	    	  actiontarget.setText("Insert number of copies");
	    	  actiontarget.setStyle("-fx-text-fill: red;");
	    	  throw new IOException("Insert number of copies");
	      }
	      if(!LibrarianUtil.isNumericAtLeastOne(checkoutTxt.getText().trim())) {
	    	  actiontarget.setText("Insert number of maximum checkout length");
	    	  actiontarget.setStyle("-fx-text-fill: red;");
	    	  throw new IOException("Insert number of maximum checkout length");
	      }
	      if(authorsTable.getItems().size()==0) {
	    	  actiontarget.setText("You must add at least one author");
	    	  actiontarget.setStyle("-fx-text-fill: red;");
	    	  throw new IOException("You must add at least one author");
	      }
	      
	      List<Author> authors = new ArrayList<Author>();
	      Author autAux;
	      for(int i = 0; i < authorsTable.getItems().size(); i++) {
	    	  autAux = authorsTable.getItems().get(i);
	    	  authors.add(autAux);
	      }
	      Book book = new Book(isbnTxt.getText().trim(), titleTxt.getText().trim(), Integer.valueOf(checkoutTxt.getText().trim()), 
	    		  authors);

	      for(int i = 0; i < Integer.valueOf(copiesTxt.getText().trim()); i++) {
	    	  book.addCopy();
	      }
	      ControllerInterface c = new SystemController();
	      
	      try {
	    	c.addBook(book);
	    	actiontarget.setText("Book added successfully");
			actiontarget.setStyle("-fx-text-fill: blue;");
		} catch (LibrarySystemException e) {
			 actiontarget.setText(e.getMessage());
			 actiontarget.setStyle("-fx-text-fill: red;");
		}
	  }
  
}