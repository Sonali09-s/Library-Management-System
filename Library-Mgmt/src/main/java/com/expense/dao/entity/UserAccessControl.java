package com.expense.dao.entity;

import java.time.LocalDateTime;

import com.expense.enums.AccessStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name ="user_access_control")
public class UserAccessControl {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="access_id")
	    private Long accessId;
	
	    @Column(name="user_id")
	    private Long userId;
	    
	    @Column(name="book_id")
	    private Long bookid;
	    
	    @Column(name="remarks")
	    private String remarks;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name="access_status")
	    private AccessStatus accessStatu = AccessStatus.PENDING;
	    
	    @Column(name="uploaded_at")
	    private LocalDateTime uploadedAt;

		public Long getAccessId() {
			return accessId;
		}

		public void setAccessId(Long accessId) {
			this.accessId = accessId;
		}

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public Long getBookid() {
			return bookid;
		}

		public void setBookid(Long bookid) {
			this.bookid = bookid;
		}

		public AccessStatus getAccessStatu() {
			return accessStatu;
		}

		public void setAccessStatu(AccessStatus accessStatu) {
			this.accessStatu = accessStatu;
		}

		public LocalDateTime getUploadedAt() {
			return uploadedAt;
		}

		public void setUploadedAt(LocalDateTime uploadedAt) {
			this.uploadedAt = uploadedAt;
		}

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
	    
		
	    
	    
}
