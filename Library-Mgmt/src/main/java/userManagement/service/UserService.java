package userManagement.service;

import com.expense.ModalDto.ResponseDtO;

import userManagement.modal.CreateUserDTO;

public interface UserService {
	
	public ResponseDtO createUserService(CreateUserDTO userCreateDto);

}
