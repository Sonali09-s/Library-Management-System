package LibrarayManagemnt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.ModalDto.ResponseDtO;

import LibrarayManagemnt.service.CategoryWiseReports;

@RestController
@RequestMapping("/reports")
public class ReportsController {
	
	@Autowired
	CategoryWiseReports cateogoryService;
	
	@GetMapping("/category")
	public ResponseDtO getCategoryPecentWise() {
		
		return cateogoryService.getCategoryWiseReponse();
		
	}

}
