package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.expense.ModalDto.AuthResponse;
import com.expense.ModalDto.AuthenticationRequestDTO;
import com.expense.ModalDto.CreateUserRequestDto;
import com.expense.ModalDto.RequestBookAccessDto;
import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.ValidationException;
import com.expense.auth.service.JwtService;
import com.expense.service.UserService;
import com.expense.service.ValidatorService;

import search.RequestDTO;

@RestController
@RequestMapping("/User")
public class UserCntrl {

	@Autowired
	UserService service;

	@Autowired
	ValidatorService validatorService;
	
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

	@PostMapping("/addUser")
	public ResponseDtO createUser(@RequestBody CreateUserRequestDto requestDto) {

//		validatorService.validateEmailAndMobile(requestDto);

		return service.createUserService(requestDto);
	}

	@PostMapping("/getUser")
	public ResponseDtO getUser(@RequestBody RequestDTO requestDto) {
		return service.getUsresService(requestDto);
//		return createUser.createUserService(requestDto);
	}

	@PostMapping("/authenticateUser")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthenticationRequestDTO loginRequest) {
	    try {
	        // 1. Attempt Authentication
	        authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                loginRequest.getEmailId(), 
	                loginRequest.getPassword()
	            )
	        );

	        // 2. If it reaches here, Authentication was successful
	        String token = jwtService.generateToken(loginRequest.getEmailId());
	        
	        return ResponseEntity.ok(new AuthResponse(
	            token, 
	            "Login Successful", 
	            1
	        ));

	    } catch (BadCredentialsException e) {
	        // 3. Handle wrong password/email
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	            .body(new AuthResponse("Invalid Email or Password", 0));
	            
	    } catch (Exception e) {
	        // 4. Handle other errors (Account disabled, etc.)
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .body(new AuthResponse("An error occurred during login", 0));
	    }
	}

	
	@GetMapping ("/{id}/profile")
	public ResponseDtO userHistory(@PathVariable("id") Long userId) {
		return service.userHistory(userId);
		
	}
//	@PostMapping("/requestAccess")
//	public ResponseDtO requestBookAccess(@RequestBody RequestBookAccessDto requestBody) {
//		return service.requestBookAccess(requestBody);
//
//	}
	
	
}
