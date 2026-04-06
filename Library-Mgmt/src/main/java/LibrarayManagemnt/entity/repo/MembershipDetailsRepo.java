package LibrarayManagemnt.entity.repo;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import LibrarayManagemnt.entity.dao.MembershipDetails;
import jakarta.transaction.Transactional;

@Repository
public interface MembershipDetailsRepo extends JpaRepository<MembershipDetails, Long> {
	
	@Query("SELECT m FROM MembershipDetails m " +
		       "WHERE m.user.id = :userId AND m.status = 'ACTIVE' " +
		       "ORDER BY m.endDate DESC")
		Optional<MembershipDetails> findActiveByUserId(@Param("userId") Long userId);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE MembershipDetails m SET m.status = 'EXPIRED' " +
	       "WHERE m.status = 'ACTIVE' AND m.endDate < :now")
	int deactivateExpired(@Param("now") LocalDateTime now);

	
	@Query(value = "SELECT EXISTS(SELECT 1 FROM membership_details " +
            "WHERE user_id = :userId AND status = 'ACTIVE' " +
            "AND :now BETWEEN start_date AND end_date)", 
    nativeQuery = true)
int existsByUserIdAndStatusAndDate(@Param("userId") Long userId, 
                                    @Param("now") LocalDateTime now);
}
