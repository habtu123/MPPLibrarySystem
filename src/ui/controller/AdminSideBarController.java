package ui.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import business.Author;
import business.Book;
import business.CheckoutEntry;
import business.ControllerInterface;
import business.SystemController;
import business.exceptions.BookNotFoundException;
import business.exceptions.MemberNotFoundException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.dto.BookListDto;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import ui.AdminWindow;
import ui.LoginWindow;
import ui.Start;

public class AdminSideBarController {

	private static Logger logger = Logger.getLogger(AdminSideBarController.class.getName());
	
	@FXML
	private BorderPane contentBars;
	@FXML
	private TextField memeberID;
	@FXML
	private TextField bookISBN;

	@FXML private Label actiontarget; 
	
	@FXML private TableView<BookListDto> booksTable;
	
	@FXML
	protected void addNewMemeberAction(ActionEvent event) throws IOException {
//        actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/addMember.fxml"));
		contentBars.setCenter(pane);

	}

	@FXML
	protected void addNewCopyAction(ActionEvent event) throws IOException {
//      actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/addBookCopy.fxml"));
		contentBars.setCenter(pane);

	}

	@FXML
	protected void addNewBookAction(ActionEvent event) throws IOException {
//      actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/addNewBook.fxml"));
		contentBars.setCenter(pane);

	}

	@FXML
	protected void checkoutBookBoth(ActionEvent event) throws IOException {
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/checkoutBook.fxml"));
		contentBars.setCenter(pane);
	}
	
	@FXML
	protected void handleLogoutButtonAction(ActionEvent event) throws IOException {
//      actiontarget.setText("Sign in button pressed " + usernameTxt.getText());
		System.out.println("logout pressed");
		AdminWindow.INSTANCE.clear();
		if (LoginWindow.INSTANCE.isInitialized())
			LoginWindow.INSTANCE.clear();
		Start.hideAllWindows();
		//Start.primStage().show();
		LoginWindow.INSTANCE.show();
	}

	@FXML
	protected void handleCheckoutBook(ActionEvent event) throws IOException {
		logger.info("Start book checkout" + memeberID);
		DataAccess da = new DataAccessFacade();
		logger.info("original " + da.readBooksMap());
		ControllerInterface c = new SystemController();
		try {
			List<CheckoutEntry> checkoutHistory = c.checkoutBook(memeberID.getText(), bookISBN.getText());
			if (!checkoutHistory.isEmpty()) {
				logger.info("Book checked out successfully");
				logger.info("checkout histroy" + checkoutHistory.size());

				for (CheckoutEntry ch : checkoutHistory) {
					System.out.println("Checked out book :" + ch.getBook().getBook().getTitle());
					System.out.println("Due date" + ch.getCheckoutDate());
				}
			}
		} catch (BookNotFoundException e) {
			actiontarget.setVisible(true);
			actiontarget.setText(e.getMessage());
		} catch (MemberNotFoundException e) {
			actiontarget.setVisible(true);
			actiontarget.setText(e.getMessage());
		}
	}
	
	@FXML
	protected void showAllMemberidAction(ActionEvent event) throws IOException {
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/AllMember.fxml"));
		contentBars.setCenter(pane);
	}
	
	@FXML
	protected void listALlBooks(ActionEvent event) throws IOException {
		GridPane pane = FXMLLoader.load(getClass().getResource("../view/AllBooks.fxml"));
		contentBars.setCenter(pane);
	
	}

}