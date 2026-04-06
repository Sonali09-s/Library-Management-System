package LibrarayManagemnt.modalDto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class IssueReturnBookDTO {

	private Long bookId;
    private String eventType; // "ISSUE" or "RETURN"
    
    @Min(value = 1, message = "You must issue the book for at least 1 day")
    @Max(value = 14, message = "You cannot issue a book for more than 14 days")
    private Integer durationDays; // How many days they want the book

    // Option A: For Registered Users
    private Long userId;
    
    private String issueDate;

    private String actualReturnDate;
    
	public String getActualReturnDate() {
		return actualReturnDate;
	}

	public void setActualReturnDate(String actualReturnDate) {
		this.actualReturnDate = actualReturnDate;
	}

	

	public IssueReturnBookDTO(Long bookId, String eventType,
			@Min(value = 1, message = "You must issue the book for at least 1 day") @Max(value = 14, message = "You cannot issue a book for more than 14 days") Integer durationDays,
			Long userId, String issueDate, String actualReturnDate) {
		super();
		this.bookId = bookId;
		this.eventType = eventType;
		this.durationDays = durationDays;
		this.userId = userId;
		this.issueDate = issueDate;
		this.actualReturnDate = actualReturnDate;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getDurationDays() {
		return durationDays;
	}

	public void setDurationDays(Integer durationDays) {
		this.durationDays = durationDays;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	
    
}
