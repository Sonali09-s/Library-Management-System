package userManagement.modal;

public class CreateUserDTO {
	
	private Long id;
	
	private String UserFName;
	
	private String UserLName;
	
	private String Email;
	
	private String PhoneNo;
	
	private String Password;
	
	private Integer MembershipDays;

	public CreateUserDTO(Long id, String userFName, String userLName, String email, String phoneNo, String passwprd,Integer membershipDays) {
		super();
		this.id = id;
		UserFName = userFName;
		UserLName = userLName;
		Email = email;
		PhoneNo = phoneNo;
		Password = passwprd;
		MembershipDays = membershipDays;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserFName() {
		return UserFName;
	}

	public void setUserFName(String userFName) {
		UserFName = userFName;
	}

	public String getUserLName() {
		return UserLName;
	}

	public void setUserLName(String userLName) {
		UserLName = userLName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNo() {
		return PhoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		PhoneNo = phoneNo;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Integer getMembershipDays() {
		return MembershipDays;
	}

	public void setMembershipDays(Integer membershipDays) {
		MembershipDays = membershipDays;
	}

	
	
	
	

}
