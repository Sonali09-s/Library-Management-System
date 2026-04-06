package LibrarayManagemnt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.ModalDto.ResponseDtO;

import LibrarayManagemnt.modalDto.IssueReturnBookDTO;
import LibrarayManagemnt.modalDto.addBooksRequestDTO;
import LibrarayManagemnt.service.BooksService;
import jakarta.validation.Valid;
import search.RequestDTO;

@RestController
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	BooksService addBooksService;

	@PostMapping("/addBooks")
	public ResponseDtO addBooks(@RequestBody List<addBooksRequestDTO> addbooksDto) {
		ResponseDtO responseObj = addBooksService.addBooksService(addbooksDto);
		return responseObj;
	}
	
	@PostMapping("/getAllBooks")
	public ResponseDtO getAllBooks(@RequestBody RequestDTO request) {
		ResponseDtO responseObj = addBooksService.getAllBooksService(request);
		return responseObj;
	}
	
	@PostMapping("/issueReturnBooks")
	public ResponseDtO issueReturnBooks(@Valid @RequestBody IssueReturnBookDTO requestDto) {
		ResponseDtO responseObj = addBooksService.issueReturnBookService(requestDto);
		return responseObj;
	}
	
//	@PostMapping("/issueReturnBooks")
//	public ResponseDtO issueReturnBooks(@RequestBody RequestDTO request) {
//		ResponseDtO responseObj = addBooksService.getAllBooksService(request);
//		return responseObj;
//	}
}
