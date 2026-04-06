package com.expense.dao.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.dao.entity.UserAccessControl;

@Repository
public interface UserAccessControlRepo extends JpaRepository<UserAccessControl, Long>{
	
	Optional<UserAccessControl> findByUserIdAndBookid(Long userId,Long bookId);


}
