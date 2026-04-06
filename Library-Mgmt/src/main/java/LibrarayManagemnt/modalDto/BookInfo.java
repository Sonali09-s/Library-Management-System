package LibrarayManagemnt.modalDto;

import java.time.LocalDateTime;

public class BookInfo {

	private String title;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate; // Will be null for current books
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDateTime getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}
	public LocalDateTime getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}
    
    
}
