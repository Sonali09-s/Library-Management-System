package com.expense.ModalDto;

public class AuthResponse {

	private String accessToken;
    private String tokenType = "Bearer";
    private String message;
    private int status; // 1 for Success, 0 for Failure

  
    public AuthResponse(String accessToken, String message, int status) {
        this.accessToken = accessToken;
        this.message = message;
        this.status = status;
    }

    // Constructor for Failure (when no token is generated)
    public AuthResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    
}
