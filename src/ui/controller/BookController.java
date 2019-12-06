package ui.controller;

import java.io.IOException;

import business.Address;
import business.Author;
import business.Book;
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
	@FXML protected TextField authorsTxt; 
	@FXML protected TextField checkoutTxt; 
	@FXML protected TextField copiesTxt; 
	
	@FXML private TableView<Author> tableView;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField phoneField;
    @FXML private TextField bioField;
	
	@FXML
    protected void addAuthors(ActionEvent event) {
        ObservableList<Author> data = tableView.getItems();
        data.add(new Author(firstNameField.getText(), lastNameField.getText(), phoneField.getText(), new Address("101 S. Main", "Fairfield", "IA", "52556"), bioField.getText()));
        
        firstNameField.setText("");
        lastNameField.setText("");
        phoneField.setText("");
        bioField.setText("");
    }
	
	@FXML protected void handleAddBook(ActionEvent event) throws IOException {
	      System.out.println("Add New book");
	      
	      if(LibrarianUtil.emptyFields(new String[] {isbnTxt.getText().trim(), titleTxt.getText().trim(), 
	    		  authorsTxt.getText().trim(), checkoutTxt.getText().trim(), copiesTxt.getText().trim()})) {
	    	  actiontarget.setText("Empty book fields");
	    	  actiontarget.setStyle("-fx-text-fill: red;");
	    	  throw new IOException("Empty book fields");
	      }
	      
	      if(!LibrarianUtil.isNumeric(copiesTxt.getText().trim())) {
	    	  actiontarget.setText("Insert number of copies");
	    	  actiontarget.setStyle("-fx-text-fill: red;");
	    	  throw new IOException("Insert number of copies");
	      }
	      if(tableView.getItems().size()==0) {
	    	  actiontarget.setText("You must add at least one author");
	    	  actiontarget.setStyle("-fx-text-fill: red;");
	    	  throw new IOException("You must add at least one author");
	      }
	      
	      Book book = new Book(isbnTxt.getText().trim(), titleTxt.getText().trim(), Integer.valueOf(checkoutTxt.getText().trim()), tableView.getItems());
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