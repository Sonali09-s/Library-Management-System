package userManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.ModalDto.ResponseDtO;

import userManagement.modal.CreateUserDTO;
import userManagement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService ;

	@PostMapping("/LoginUser")
	public ResponseDtO  createUser(@RequestBody CreateUserDTO requestDto ) {
		
		ResponseDtO responseDto =	userService.createUserService(requestDto);
		
		
		return responseDto;
		
	}
	
}
