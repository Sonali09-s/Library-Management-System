package com.expense.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.PasswordTokenResponse;
import com.expense.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	private static final Logger log = LogManager.getLogger(TokenServiceImpl.class);
	@Override
	public PasswordTokenResponse createPasswordToken() {
		
		log.info("-- Inside token Service Impl --");
		//generating random number as a token for password 
		PasswordTokenResponse tokenResposne = new PasswordTokenResponse();
		  String token = UUID.randomUUID().toString();
		  LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);
		  
		  tokenResposne.setToken(token);
		  tokenResposne.setTokenExpiry(expiryDate);
		  log.info("-- token generated successfully before returning response --"+tokenResposne);
		  
		return tokenResposne;
	}

}
