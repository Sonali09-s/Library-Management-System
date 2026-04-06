package com.expense.service;

import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.SetPasswordRequestDto;

public interface UpdatePasswordServcie {
	
	public ResponseDtO setPassword(SetPasswordRequestDto setPasswordRequest,String token); 

}
