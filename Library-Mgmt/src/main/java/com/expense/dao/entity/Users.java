package com.expense.dao.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class Users implements  UserDetails {
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFName() {
		return userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public String getUserLName() {
		return userLName;
	}

	public void setUserLName(String userLName) {
		this.userLName = userLName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "user_first_name")
	private String userFName;
	
	@Column(name = "user_last_name")
	private String userLName;
	
	@Column(name = "user_mobile",nullable = true)
	private String userMobile;
	
	@Column(name = "user_email",nullable = true)
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="token")
	private String token;
	
	@Column(name="token_expiry")
    private LocalDateTime tokenExpiry;
	
	@Column(name="is_email_sent")
    private Integer isEmailSent;
	
	@Column(name="member_ship_days")
    private Integer memberShipDays;
	
	@Column(name="user_type")
    private String userType;
	

	


	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getMemberShipDays() {
		return memberShipDays;
	}

	public void setMemberShipDays(Integer memberShipDays) {
		this.memberShipDays = memberShipDays;
	}

	public Integer getIsEmailSent() {
		return isEmailSent;
	}

	public void setIsEmailSent(Integer isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

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

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return List.of(new SimpleGrantedAuthority(userType));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userPassword;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userEmail;
	}
	
	
	

}
