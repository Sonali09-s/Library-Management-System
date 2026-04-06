package com.expense.ModalDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CreateUserRequestDto {
	
	private String fName;
	private String lName;
	private String phone;
	private String emailId;
	private Integer membershipDays;
	
	private String userType;
	
	
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	

	public Integer getMembershipDays() {
		return membershipDays;
	}
	public void setMembershipDays(Integer membershipDays) {
		this.membershipDays = membershipDays;
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
