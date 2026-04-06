package LibrarayManagemnt.entity.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import LibrarayManagemnt.entity.dao.IssueBookDetails;
import LibrarayManagemnt.modalDto.CategoryReportDTO;

@Repository
public interface IssueBookDetailsRepo extends JpaRepository<IssueBookDetails, Long> {
	
	Optional<IssueBookDetails> findByUserIdAndBookIdAndActualReturnDateIsNull(Long UserId,Long BookId);
	
	// Current books (still with the user)
    List<IssueBookDetails> findByUserIdAndActualReturnDateIsNull(Long userId);

    // Past books (already returned)
    List<IssueBookDetails> findByUserIdAndActualReturnDateIsNotNull(Long userId);
    
    //% Wise
    
    @Query(value = "SELECT b.book_category AS category, COUNT(i.id) AS totalReads, " +
            "ROUND((COUNT(i.id) * 100.0 / (SELECT COUNT(*) FROM issue_book_details)), 2) AS percentage " +
            "FROM issue_book_details i " +
            "JOIN books b ON i.book_id = b.book_id " +
            "GROUP BY b.book_category " +
            "ORDER BY totalReads DESC", 
            nativeQuery = true)
     List<CategoryReportDTO> getCategoryPopularityReport();
}
