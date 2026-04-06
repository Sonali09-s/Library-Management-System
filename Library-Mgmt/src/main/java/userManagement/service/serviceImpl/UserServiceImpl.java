package userManagement.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.ResponseDtO;

import userManagement.dao.entity.UserTable;
import userManagement.dao.repo.UserTableRepo;
import userManagement.modal.CreateUserDTO;
import userManagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserTableRepo userTableRepo;

	@Override
	public ResponseDtO createUserService(CreateUserDTO userCreateDto) {
		
		ResponseDtO responnseDto = new ResponseDtO();
		
		
		Optional<UserTable>  userTableObjCheck = userTableRepo.findByEmail(userCreateDto.getEmail());
		
		if(userTableObjCheck.isPresent()) {
			responnseDto.setSatus(0);
			responnseDto.setBody(null);
			responnseDto.setResponseMsg("User Already Registered "+userCreateDto.getEmail());
			return responnseDto;
		}
		
//		UserTable  userTableObj = new UserTable();
		else {
			UserTable userTableObj = new UserTable();
		userTableObj.setEmail(userCreateDto.getEmail());
		userTableObj.setMembershipDays(userCreateDto.getMembershipDays());
		userTableObj.setPassword(userCreateDto.getPassword());
		userTableObj.setPhoneNo(userCreateDto.getPhoneNo());
		userTableObj.setUserFName(userCreateDto.getUserFName());
		userTableObj.setUserLName(userCreateDto.getUserLName());
		userTableObj.setUserRole("USER");
		
		userTableRepo.save(userTableObj);
		
		responnseDto.setSatus(1);
		responnseDto.setBody(userTableObj);
		responnseDto.setResponseMsg("User Login Suucessfully");
		}
		
		
		
		return null;
	}

}
