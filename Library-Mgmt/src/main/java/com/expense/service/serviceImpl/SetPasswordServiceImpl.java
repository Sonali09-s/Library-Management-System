package com.expense.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.SetPasswordRequestDto;
import com.expense.dao.entity.Users;
import com.expense.dao.repo.UsersRepo;
import com.expense.service.UpdatePasswordServcie;

@Service
public class SetPasswordServiceImpl implements UpdatePasswordServcie {
	
	@Autowired
	UsersRepo userrepo;

	@Override
	public ResponseDtO setPassword(SetPasswordRequestDto setPasswordRequest,String token) {
		
		ResponseDtO responseObj = new ResponseDtO();
		Optional<Users> users = userrepo.findByToken(token);
		
		if(users.isEmpty()) {
			responseObj.setSatus(0);
			responseObj.setBody(token);
			responseObj.setResponseMsg("No User found for the follwoing token! :"+token+ " kindly check again ");
			return responseObj;
		}
		
		else {
			
			Users userObj = users.get();
			LocalDateTime localDateTime =LocalDateTime.now();
			
			if(localDateTime.isAfter(userObj.getTokenExpiry())) {
				responseObj.setSatus(0);
				responseObj.setBody(token);
				responseObj.setResponseMsg("Token has been expired, kindly create the user again : ");
				return responseObj;
			}
			
			
			
			String password = BCrypt.hashpw(setPasswordRequest.getPassword(), BCrypt.gensalt(10));
			userObj.setUserPassword(password);
			
			userrepo.save(userObj);
			
			responseObj.setSatus(1);
			responseObj.setBody(userObj);
			responseObj.setResponseMsg("Password has been successfuly set for user :"+userObj.getUserEmail());
			return responseObj;
		}
		
	}

}
