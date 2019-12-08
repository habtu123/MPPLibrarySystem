package ui.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import business.Book;
import business.BookCopy;
import business.CheckoutEntry;
import business.CheckoutRecord;
import business.ControllerInterface;
import business.LibraryMember;
import business.SystemController;
import business.exceptions.BookNotFoundException;
import dataaccess.dto.Overdue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BookOverdueController implements Initializable {
	private static Logger logger = Logger.getLogger(BookOverdueController.class.getName());
	StringBuilder sb = new StringBuilder();
	@FXML
	private TextField bookIsbn;
	@FXML
	private Label actiontarget;
	@FXML
	private TableView<Overdue> overdueTable;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	protected void handleSearchBook(ActionEvent event) throws BookNotFoundException {
		actiontarget.setVisible(false);
		overdueTable.getItems().clear();
		Book book;
		int borrowedCounter;
		List<Integer> availbleCopyNums = new ArrayList<Integer>();
		ControllerInterface c = new SystemController();
		String isbn = bookIsbn.getText();
		ObservableList<Overdue> overdue = overdueTable.getItems();
		try {
			book = findBooksByIsbn(isbn);
			BookCopy[] bookCopy = book.getCopies();

			for (BookCopy copy : bookCopy) {
				if (copy.isAvailable()) {
					overdue.add(new Overdue(copy.getBook().getIsbn(), copy.getBook().getTitle(),
							Integer.valueOf(copy.getCopyNum()), "", "", "Available"));
				}
			}
		} catch (BookNotFoundException e) {
			actiontarget.setVisible(true);
			actiontarget.setText(e.getMessage());
		}

		List<LibraryMember> memebrHavingTheCopy = new ArrayList<LibraryMember>();
		List<LibraryMember> memebers = c.findAllMemebers();
		for (LibraryMember memeber : memebers) {
			List<CheckoutEntry> checkoutEntries = memeber.getCheckoutRecord().getCheckoutEntry();
			for (CheckoutEntry entry : checkoutEntries) {
				if (isbn.equals(entry.getBook().getBook().getIsbn())) {
					
					memebrHavingTheCopy.add(memeber);
				}
			}
		}
		List<Integer> checkedOutCopy = new ArrayList<Integer>();
		if (memebrHavingTheCopy.size() > 0) {
			memebrHavingTheCopy.forEach(memeber -> {

				CheckoutRecord record = memeber.getCheckoutRecord();
				List<CheckoutEntry> entries = record.getCheckoutEntry();
				for (CheckoutEntry ent : entries) {
					checkedOutCopy.add(ent.getBook().getCopyNum());
					if (isbn.equals(ent.getBook().getBook().getIsbn())) {
						overdue.add(new Overdue(ent.getBook().getBook().getIsbn(), ent.getBook().getBook().getTitle(),
								Integer.valueOf(ent.getBook().getCopyNum()),
								memeber.getFirstName() + "" + memeber.getLastName(), ent.getBook().getBook().getIsbn(),
								LocalDate.now().compareTo(ent.getDueDate()) > 0 ? "Overdue" : "Not Overdue"));

					}
				}
			});
		}
	}

	void checkBookExist(String isbn) throws BookNotFoundException {
		ControllerInterface c = new SystemController();
		c.findBook(isbn);
	}

	Book findBooksByIsbn(String isbn) throws BookNotFoundException {
		ControllerInterface c = new SystemController();
		return c.findBook(isbn);
	}

}