package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.SetPasswordRequestDto;
import com.expense.service.ValidatorService;
import com.expense.service.UpdatePasswordServcie;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/userPassowrd")
public class SetPasswordCntrl {
	
	@Autowired
	UpdatePasswordServcie updateService;
	
	@Autowired
	ValidatorService passwordValidation;

	@PostMapping("/setPassword")
	public ResponseDtO setPasword( @RequestBody SetPasswordRequestDto requestBody,@RequestHeader("X-Password-Token") String token) {
		
		passwordValidation.validatePassword(requestBody.getPassword());
		return updateService.setPassword(requestBody,token);
		
	}
	
	
}
