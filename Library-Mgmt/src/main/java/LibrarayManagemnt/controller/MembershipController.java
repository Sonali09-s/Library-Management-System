package LibrarayManagemnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.ModalDto.ResponseDtO;

import LibrarayManagemnt.modalDto.MembershipRequestDto;
import LibrarayManagemnt.service.MemberShipService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/members")
public class MembershipController {
	
	@Autowired
	MemberShipService membershipService;

	@PostMapping("/addRenewMember")
	public ResponseDtO addMembership(@Valid @RequestBody MembershipRequestDto requestBody) {
		
		ResponseDtO  responseObj = membershipService.addRenewweMembership(requestBody);
		return responseObj;
		
	}
	
}
