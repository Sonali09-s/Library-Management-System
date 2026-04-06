package LibrarayManagemnt.entity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


import LibrarayManagemnt.entity.dao.Books;
@Repository
public interface bookRepo extends JpaRepository<Books, Long>,JpaSpecificationExecutor<Books> {

}
