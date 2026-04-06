package com.expense.ModalDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SetPasswordRequestDto {


	private String password;

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
