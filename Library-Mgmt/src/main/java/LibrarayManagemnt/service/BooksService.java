package LibrarayManagemnt.service;

import java.util.List;

import com.expense.ModalDto.ResponseDtO;

import LibrarayManagemnt.modalDto.IssueReturnBookDTO;
import LibrarayManagemnt.modalDto.addBooksRequestDTO;
import search.RequestDTO;

public interface BooksService {

	public ResponseDtO addBooksService(List<addBooksRequestDTO> requestDto);
	public ResponseDtO getAllBooksService(RequestDTO request);
	public ResponseDtO issueReturnBookService(IssueReturnBookDTO issueReturnRequest );
}
