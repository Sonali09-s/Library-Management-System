package com.expense.service;

import org.springframework.stereotype.Service;

import com.expense.ModalDto.CreateUserRequestDto;
import com.expense.ModalDto.ValidationException;

@Service
public class ValidatorService {
	public void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new ValidationException("password", "Password must not be empty");
        }
        if (password.length() < 8) {
            throw new ValidationException("password", "Password must be at least 8 characters");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new ValidationException("password", "Password must contain at least one uppercase letter");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new ValidationException("password", "Password must contain at least one digit");
        }
        // Add more rules as needed
    }
	
	public void validateEmailAndMobile(CreateUserRequestDto requestDto) {
		 if (requestDto.getEmailId() == null || !requestDto.getEmailId().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
		        throw new ValidationException("emailId", "Invalid email format");
		    }

		    if (requestDto.getPhone() == null || !requestDto.getPhone().matches("^[6-9]\\d{9}$")) {
		        throw new ValidationException("phone", "Phone number must be 10 digits and start with 6-9");
		    }
	}

}
