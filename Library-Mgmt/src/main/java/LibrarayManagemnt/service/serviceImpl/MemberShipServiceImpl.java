package LibrarayManagemnt.service.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.ResponseDtO;
import com.expense.dao.entity.Users;
import com.expense.dao.repo.UsersRepo;

import LibrarayManagemnt.entity.dao.MembershipDetails;
import LibrarayManagemnt.entity.repo.MembershipDetailsRepo;
import LibrarayManagemnt.modalDto.MembershipRequestDto;
import LibrarayManagemnt.service.MemberShipService;
import jakarta.transaction.Transactional;
import userManagement.dao.repo.UserTableRepo;

@Service
public class MemberShipServiceImpl implements MemberShipService {
	
	@Autowired
	UsersRepo userRepo;
	
	@Autowired
	MembershipDetailsRepo membershipRepo;

	@Override
	@Transactional
	public ResponseDtO addRenewweMembership(MembershipRequestDto membershipDto) {
		ResponseDtO responseObj = new  ResponseDtO();
		
		Users user = userRepo.findById(membershipDto.getUserId())
	            .orElseThrow(() -> new RuntimeException("User not found"));

		Optional<MembershipDetails> existing = membershipRepo.findActiveByUserId(user.getUserId());
		
	    LocalDateTime startDate =null;
	    
	    if (existing.isPresent()) {
	        // If they have one, the new one starts exactly when the old one ends
	        startDate = existing.get().getEndDate().plusDays(1);
	    } else {
	        // If they are a Guest/New, it starts right now
	        startDate = LocalDateTime.now();
	    }
	    
	    
	    LocalDateTime endDate = calculateEndDate(startDate, membershipDto.getPlanName());
		
	 
		
	    
	    MembershipDetails newMember = new MembershipDetails();
	    newMember.setUser(user);
	    newMember.setPlanName(membershipDto.getPlanName().toUpperCase());
	    newMember.setStartDate(startDate);
	    newMember.setEndDate(endDate);
	    newMember.setStatus("ACTIVE");

	    user.setUserType("MEMBER");
	    
	    membershipRepo.save(newMember);
	    userRepo.save(user);
	    
	    responseObj.setSatus(1);
	    responseObj.setResponseMsg("Membership Details Added Successfully ");
	    
	    
		return responseObj;
	}

	private LocalDateTime calculateEndDate(LocalDateTime startDate, String planName) {
	    // We convert to uppercase to handle "monthly", "Monthly", or "MONTHLY"
	    return switch (planName.toUpperCase()) {
	        case "MONTHLY" -> startDate.plusMonths(1).minusDays(1);
	        case "QUARTERLY" -> startDate.plusMonths(3).minusDays(1);
	        case "YEARLY" -> startDate.plusYears(1).minusDays(1);
	        
	        // Always include a default to handle typos or unsupported plans
	        default -> throw new IllegalArgumentException("Unsupported membership plan: " + planName);
	    };
	}
}
