package com.expense.service;

import com.expense.ModalDto.PasswordTokenResponse;

public interface TokenService {
	
	public PasswordTokenResponse createPasswordToken();

}
