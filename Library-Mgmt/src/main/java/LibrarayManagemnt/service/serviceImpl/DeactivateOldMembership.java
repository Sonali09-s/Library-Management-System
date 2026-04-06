package LibrarayManagemnt.service.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import LibrarayManagemnt.entity.repo.MembershipDetailsRepo;

@Service
public class DeactivateOldMembership {

	@Autowired
	MembershipDetailsRepo membershipRepo;
	
	@Scheduled(cron = "0 52 20 * * *")
	public void deactivateExpiredMemberships() {
	
	try {
	int count = membershipRepo.deactivateExpired(LocalDateTime.now());
    System.out.println("Successfully deactivated {} memberships."+ count);
} catch (Exception e) {
    // If one row fails (e.g. database lock), the whole operation fails 
    // and you can log the error to check it in the morning.
	System.out.println("Failed to run membership cleanup: {}"+ e);
}
	}
}