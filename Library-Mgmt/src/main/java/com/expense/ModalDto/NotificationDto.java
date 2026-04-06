package com.expense.ModalDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NotificationDto {
	
	private String destinationTo;
	private String destinationFrom;
	private String contentType;
	private String subject;
	private String content;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	
	@Override
	public String toString(){
	  ObjectMapper mapper = new ObjectMapper();
	  String json = "JSON conversion of to string failed";
	try {
	 json = mapper.writeValueAsString(this);

	  } catch (JsonProcessingException e) {
	  e.printStackTrace();
	  }
	  return json;
	}


}
