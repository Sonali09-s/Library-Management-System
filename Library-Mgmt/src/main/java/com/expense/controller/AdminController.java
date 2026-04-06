package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.ModalDto.AccessControlRequestDTO;
import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.UploadBooksRequestDTO;
import com.expense.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminservice;
	
	@PostMapping("/uploadBooks")
	public ResponseDtO uploadPdf(@ModelAttribute UploadBooksRequestDTO requestDto) {
		
		ResponseDtO reposne = adminservice.uploadBookService(requestDto);
		return reposne;
		
	}
	
	@PostMapping("/accessControl")
	public ResponseDtO accessControl(@RequestBody AccessControlRequestDTO requestDto) {
		
		ResponseDtO reposne = adminservice.accesControlService(requestDto);
		return reposne;
	}

}
