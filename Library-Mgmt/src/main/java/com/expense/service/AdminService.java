package com.expense.service;

import com.expense.ModalDto.AccessControlRequestDTO;
import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.UploadBooksRequestDTO;

public interface AdminService {

	public ResponseDtO uploadBookService(UploadBooksRequestDTO requestDto);
	
	public ResponseDtO accesControlService(AccessControlRequestDTO requestDto);
}
