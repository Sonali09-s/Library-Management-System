package userManagement.dao.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import userManagement.dao.entity.UserTable;

@Repository
public interface UserTableRepo extends JpaRepository<UserTable, Long>,JpaSpecificationExecutor<UserTable> {
	
	Optional<UserTable> findByEmail(String emailId);
	

}
