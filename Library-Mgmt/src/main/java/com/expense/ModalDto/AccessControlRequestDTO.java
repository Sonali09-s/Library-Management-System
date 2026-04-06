package com.expense.ModalDto;

import java.time.LocalDateTime;

import com.expense.enums.AccessStatus;

public class AccessControlRequestDTO {

	
	private Long AccessId;
	
	private Long userId;
	
    private AccessStatus accessStatus; 
    
    private String remarks;
    
    private LocalDateTime uploadedAt;
	
	
	
	public AccessStatus getAccessStatus() {
		return accessStatus;
	}

	public void setAccessStatus(AccessStatus accessStatus) {
		this.accessStatus = accessStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public LocalDateTime getUploadedAt() {
		return uploadedAt;
	}

	public void setUploadedAt(LocalDateTime uploadedAt) {
		this.uploadedAt = uploadedAt;
	}

	public Long getAccessId() {
		return AccessId;
	}

	public void setAccessId(Long accessId) {
		AccessId = accessId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	private Long bookId;
	
}
