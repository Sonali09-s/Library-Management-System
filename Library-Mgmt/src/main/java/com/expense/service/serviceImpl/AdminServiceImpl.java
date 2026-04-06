package com.expense.service.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.expense.ModalDto.AccessControlRequestDTO;
import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.UploadBooksRequestDTO;
import com.expense.dao.entity.UploadedBooks;
import com.expense.dao.entity.UserAccessControl;
import com.expense.dao.repo.UploadedBooksRepo;
import com.expense.dao.repo.UserAccessControlRepo;
import com.expense.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	UploadedBooksRepo uploadBookRepo;
	
	@Autowired
	UserAccessControlRepo userAccessRepo;

	@Override
	public ResponseDtO uploadBookService(UploadBooksRequestDTO requestDto) {
		
		ResponseDtO responseObj = new ResponseDtO();
		UploadedBooks uploadBoooks = new UploadedBooks();
		
		String fileName = null;
		String fileUrl = null;
		
		try {
			fileName = saveFile(requestDto.getFile());
			fileUrl = "http://localhost:9090/files/"+fileName;
			uploadBoooks.setAuthorName(requestDto.getAuthorName());
			uploadBoooks.setBookId(requestDto.getBookId());
			uploadBoooks.setBookName(requestDto.getBookName());
			uploadBoooks.setDescription(requestDto.getDescription());
			uploadBoooks.setFileUrl(fileUrl);
			
			//
			LocalDateTime now = LocalDateTime.now();
			
			uploadBoooks.setUploadedAt(now);
			try {
			uploadBookRepo.save(uploadBoooks);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
			responseObj.setSatus(1);
			responseObj.setResponseMsg("Pdf Saved Successfully ");
		
		} catch (IOException e) {
			responseObj.setSatus(0);
			responseObj.setResponseMsg("Some error in saving the pdf ");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseObj;
	}

	
	public String saveFile(MultipartFile file) throws IOException {
	    String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
	    String uploadDir = "uploads/";

	    File dir = new File(uploadDir);
	    if (!dir.exists()) {
	        dir.mkdirs();
	    }

	    Path filePath = Paths.get(uploadDir + fileName);
	    Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	   
	    return fileName;
	}

	@Override
	public ResponseDtO accesControlService(AccessControlRequestDTO requestDto) {
		
		ResponseDtO responseObj = new ResponseDtO();
		
		UserAccessControl userAccessObj = new UserAccessControl();
		
		userAccessObj.setBookid(requestDto.getBookId());
		userAccessObj.setUploadedAt(requestDto.getUploadedAt());
		userAccessObj.setUserId(requestDto.getUserId());
		userAccessObj.setAccessStatu(requestDto.getAccessStatus());
		userAccessObj.setRemarks(requestDto.getRemarks());
		
		try {
		userAccessRepo.save(userAccessObj);
		responseObj.setSatus(1);
		responseObj.setResponseMsg("User Access Updated");
		}
		catch(Exception e) {
			responseObj.setSatus(0);
			responseObj.setResponseMsg("Error in Updating Access user: "+e);
		}
		
		
		
		return responseObj;
	}

}
