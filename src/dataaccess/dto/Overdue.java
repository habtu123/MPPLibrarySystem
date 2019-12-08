package dataaccess.dto;

public class Overdue implements Comparable<Overdue>{
	private String isbn; 
	private String bookTitle; 
	private Integer copyNumber; 
	private String memeberName; 
	private String dueDate; 
	private String status;
	
	public Overdue() {
		
	}
	
	public Overdue(String isbn, String bookTitle, Integer copyNumber, String memeberName, String dueDate,
			String status) {
		super();
		this.isbn = isbn;
		this.bookTitle = bookTitle;
		this.copyNumber = copyNumber;
		this.memeberName = memeberName;
		this.dueDate = dueDate;
		this.status = status;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public Integer getCopyNumber() {
		return copyNumber;
	}
	public void setCopyNumber(Integer copyNumber) {
		this.copyNumber = copyNumber;
	}
	public String getMemeberName() {
		return memeberName;
	}
	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int compareTo(Overdue o) {
		return (this.getCopyNumber() - o.getCopyNumber());
	} 
	
	
}
