package LibrarayManagemnt.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.expense.ModalDto.ResponseDtO;

import LibrarayManagemnt.entity.repo.IssueBookDetailsRepo;
import LibrarayManagemnt.modalDto.CategoryReportDTO;
import LibrarayManagemnt.service.CategoryWiseReports;

@Service
public class CategoryWiserReportsImpl implements CategoryWiseReports {
	
	@Autowired
	IssueBookDetailsRepo issueBookDetailsRepo;

	@Override
	public ResponseDtO getCategoryWiseReponse() {
		
		ResponseDtO response = new ResponseDtO();
		
		try {
		List<CategoryReportDTO> reportData = issueBookDetailsRepo.getCategoryPopularityReport();
		
		if (reportData.isEmpty()) {
            response.setSatus(0);
            response.setResponseMsg("No data available for reporting.");
            response.setBody(new ArrayList<>()); // Return empty list instead of null
        } else {
            response.setSatus(1);
            response.setResponseMsg("Category popularity report generated successfully.");
            response.setBody(reportData); // Put the list into the 'Object' body field
        }
    } catch (Exception e) {
        response.setSatus(500);
        response.setResponseMsg("Error generating report: " + e.getMessage());
    }
		
		
		
		return response;
	}

}
