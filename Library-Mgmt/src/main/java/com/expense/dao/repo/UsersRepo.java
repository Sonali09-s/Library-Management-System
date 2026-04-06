package com.expense.dao.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.expense.dao.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Long> , JpaSpecificationExecutor<Users> {
	
	Optional<Users> findByUserEmail(String userEmail);
	
	Optional<Users> findByToken(String token);
	
	Optional<Users> findByUserEmailAndIsEmailSent(String userEmail,Integer emailSent);

}
