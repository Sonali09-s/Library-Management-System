package com.expense.ModalDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationRequestDTO {
	
	private String emailId;
	private String password;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
