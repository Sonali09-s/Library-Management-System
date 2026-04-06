package LibrarayManagemnt.service.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.ResponseDtO;
import com.expense.dao.entity.Users;
import com.expense.utils.Utils;

import LibrarayManagemnt.entity.dao.Books;
import LibrarayManagemnt.entity.dao.IssueBookDetails;
import LibrarayManagemnt.entity.repo.IssueBookDetailsRepo;
import LibrarayManagemnt.entity.repo.MembershipDetailsRepo;
import LibrarayManagemnt.entity.repo.bookRepo;
import LibrarayManagemnt.enums.BookStatus;
import LibrarayManagemnt.modalDto.IssueReturnBookDTO;
import LibrarayManagemnt.modalDto.addBooksRequestDTO;
import LibrarayManagemnt.service.BooksService;
import search.GenericSpecification;
import search.RequestDTO;
import search.SpecificationUtils;

@Service
public class BooksServiceImpl implements BooksService {

	@Autowired
	bookRepo bookrepo;

	@Autowired
	IssueBookDetailsRepo issueBookDetailsRepo;
	
	@Autowired
	Utils utilObj;
	
	@Autowired
	MembershipDetailsRepo membershipRepo;

	@Override
	public ResponseDtO addBooksService(List<addBooksRequestDTO> requestDto) {

		ResponseDtO responseObj = new ResponseDtO();

		try {
			List<Books> bookList = requestDto.stream().map(dto -> {
				Books book = new Books();
				book.setBookName(dto.getBookName());
				book.setBookAuthor(dto.getBookAuthor());
				book.setBookCategory(dto.getBookCategory());
				return book;
			}).toList();

			bookrepo.saveAll(bookList);
			responseObj.setSatus(1);
			responseObj.setResponseMsg("Books Inserted Successfully");
		} catch (Exception e) {
			responseObj.setSatus(0);
			responseObj.setResponseMsg("Error in inserting Books " + e);
		}

		return responseObj;
	}

	@Override
	public ResponseDtO getAllBooksService(RequestDTO requestDto) {

		ResponseDtO responseDto = new ResponseDtO();

		String searchQuery = requestDto.getSearch();
		GenericSpecification<Books> specification = (searchQuery != null && !searchQuery.isEmpty())
				? SpecificationUtils.buildSpecificationFromSearch(searchQuery)
				: new GenericSpecification<>();

		Pageable pageable = SpecificationUtils.createPageable(requestDto);
		Page<Books> page = bookrepo.findAll(specification, pageable);

		responseDto.setSatus(1);
		responseDto.setResponseMsg("Data found");
		responseDto.setBody(page.getContent());

		return responseDto;
	}

	@Override
	public ResponseDtO issueReturnBookService(IssueReturnBookDTO issueReturnRequest) {

		ResponseDtO responseObj = new ResponseDtO();

		Optional<Books> bookObj = bookrepo.findById(issueReturnRequest.getBookId());

		if (bookObj.isPresent()) {
			Books bookNewObj = bookObj.get();
			if ("ISSUE".equalsIgnoreCase(issueReturnRequest.getEventType())) {
				
				//member Validation
				LocalDateTime now = LocalDateTime.now();
		        int isActive = membershipRepo.existsByUserIdAndStatusAndDate(
		                issueReturnRequest.getUserId(), now);
		        
		        
		        if (isActive!=1) {
		            responseObj.setResponseMsg("Access Denied: No active membership found.");
		            return responseObj;
		        }

				if (BookStatus.AVAILABLE.equals(bookNewObj.getBookStatus())) {

					IssueBookDetails issueBookObj = new IssueBookDetails();

					bookNewObj.setBookStatus(BookStatus.TAKEN);

					issueBookObj.setBookId(issueReturnRequest.getBookId());
					issueBookObj.setUserId(issueReturnRequest.getUserId());
					issueBookObj.setEventType(issueReturnRequest.getEventType());


					issueBookObj.setIssueDate(utilObj.DateFormatter(issueReturnRequest.getIssueDate()));

					issueBookObj.setDurationDays(issueReturnRequest.getDurationDays());
					int days = issueReturnRequest.getDurationDays(); // e.g., 7 or 14
				    issueBookObj.setExpectedReturnDate(LocalDateTime.now().plusDays(days));
					
					bookrepo.save(bookNewObj);
					issueBookDetailsRepo.save(issueBookObj);
					responseObj.setResponseMsg("Book Issued Successfully");
				} else {
					responseObj.setResponseMsg("Book is currently unavailable");

				}
			} else if ("RETURN".equalsIgnoreCase(issueReturnRequest.getEventType())) {

				Optional<IssueBookDetails> issueDetails = issueBookDetailsRepo
						.findByUserIdAndBookIdAndActualReturnDateIsNull(issueReturnRequest.getUserId(), issueReturnRequest.getBookId());

				if (issueDetails.isPresent()) {
					IssueBookDetails issueDetailsObj = issueDetails.get();

//					LocalDateTime currentDate = LocalDateTime.now();
					issueDetailsObj.setEventType("RETURN");
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

					LocalDateTime returenDate = LocalDateTime.parse(issueReturnRequest.getActualReturnDate(), formatter);

					issueDetailsObj.setActualReturnDate(returenDate);

					bookNewObj.setBookStatus(BookStatus.AVAILABLE);
					bookrepo.save(bookNewObj);
					issueBookDetailsRepo.save(issueDetailsObj);
					responseObj.setResponseMsg("Book Returned Successfully");
					;
				}
				else {
			        responseObj.setResponseMsg("Error: No active issue found for this book and user.");
			    }

			}
		}

		// TODO Auto-generated method stub
		return responseObj;
	}

}
