package com.expense.ModalDto;

import java.util.List;

import LibrarayManagemnt.modalDto.BookInfo;

public class UserHistoryDTO {

	private Long userId;
    private String userName;
    private String membershipStatus;
    private List<BookInfo> currentBooks;
    private List<BookInfo> earlierBooks;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMembershipStatus() {
		return membershipStatus;
	}
	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}
	public List<BookInfo> getCurrentBooks() {
		return currentBooks;
	}
	public void setCurrentBooks(List<BookInfo> currentBooks) {
		this.currentBooks = currentBooks;
	}
	public List<BookInfo> getEarlierBooks() {
		return earlierBooks;
	}
	public void setEarlierBooks(List<BookInfo> earlierBooks) {
		this.earlierBooks = earlierBooks;
	}
    
    
}
