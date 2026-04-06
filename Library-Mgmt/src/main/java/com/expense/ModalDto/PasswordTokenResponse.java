package com.expense.ModalDto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PasswordTokenResponse {
	
	private String token;
	private LocalDateTime tokenExpiry;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public LocalDateTime getTokenExpiry() {
		return tokenExpiry;
	}
	public void setTokenExpiry(LocalDateTime tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}
	
//	@Override
//	public String toString(){
//	  ObjectMapper mapper = new ObjectMapper();
//	  String json = "JSON conversion of to string failed";
//	try {
//	 json = mapper.writeValueAsString(this);
//
//	  } catch (JsonProcessingException e) {
//	  e.printStackTrace();
//	  }
//	  return json;
//	}


}
