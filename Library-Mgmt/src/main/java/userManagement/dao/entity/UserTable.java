package userManagement.dao.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_table")
public class UserTable {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_fname", length = 50, nullable = false)
    private String userFName;

    @Column(name = "user_lname", length = 50, nullable = false)
    private String userLName;

    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;

    @Column(name = "phone_no", length = 15)
    private String phoneNo;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "membership_days")
    private Integer membershipDays = 0;

    @Column(name = "user_role", length = 20, nullable = false)
    private String userRole = "GUEST";

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;

//	public UserTable(Long id, String userFName, String userLName, String email, String phoneNo, String password,
//			Integer membershipDays, String userRole, LocalDateTime createdAt) {
//		super();
//		this.id = id;
//		this.userFName = userFName;
//		this.userLName = userLName;
//		this.email = email;
//		this.phoneNo = phoneNo;
//		this.password = password;
//		this.membershipDays = membershipDays;
//		this.userRole = userRole;
//		this.createdAt = createdAt;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getMembershipDays() {
		return membershipDays;
	}

	public void setMembershipDays(Integer membershipDays) {
		this.membershipDays = membershipDays;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    
    
}
