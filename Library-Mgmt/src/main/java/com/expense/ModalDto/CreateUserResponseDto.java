package com.expense.ModalDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateUserResponseDto {
	
	private long id;
	private String userName;
	private String mobileNumber;
	private String userEmail;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
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
