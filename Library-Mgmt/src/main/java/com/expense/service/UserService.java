package com.expense.service;

import com.expense.ModalDto.AuthenticationRequestDTO;
import com.expense.ModalDto.CreateUserRequestDto;
import com.expense.ModalDto.RequestBookAccessDto;
import com.expense.ModalDto.ResponseDtO;

import search.RequestDTO;

public interface UserService {
	
	public ResponseDtO createUserService(CreateUserRequestDto requestDto);
	
	public ResponseDtO getUsresService(RequestDTO requestDto);
	
	public ResponseDtO authenticateUser(AuthenticationRequestDTO requestDto);
	
	public ResponseDtO requestBookAccess(RequestBookAccessDto requestDto);

	public ResponseDtO userHistory(Long userId);

}
