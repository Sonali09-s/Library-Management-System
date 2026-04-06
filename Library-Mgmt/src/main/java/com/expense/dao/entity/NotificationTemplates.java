package com.expense.dao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "notification_template")
public class NotificationTemplates {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name="destination_to")
	private String destinationTo;
	
	@Column(name="destination_from")
	private String destinationFrom;
	
	@Column(name="content_type")
	private String contentType;
	
	@Column(name="subject")
	private String subject;
	
	@Lob
	@Column(name="content",columnDefinition = "TEXT")
	private String content;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	private LocalDateTime updatedAt;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getDestinationTo() {
		return destinationTo;
	}
	public void setDestinationTo(String destinationTo) {
		this.destinationTo = destinationTo;
	}
	public String getDestinationFrom() {
		return destinationFrom;
	}
	public void setDestinationFrom(String destinationFrom) {
		this.destinationFrom = destinationFrom;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
