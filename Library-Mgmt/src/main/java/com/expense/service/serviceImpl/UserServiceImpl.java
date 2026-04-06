package com.expense.service.serviceImpl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.AuthenticationRequestDTO;
import com.expense.ModalDto.CreateUserRequestDto;
import com.expense.ModalDto.NotificationDto;
import com.expense.ModalDto.PasswordTokenResponse;
import com.expense.ModalDto.RequestBookAccessDto;
import com.expense.ModalDto.ResponseDtO;
import com.expense.ModalDto.UserHistoryDTO;
import com.expense.ModalDto.ValidationException;
import com.expense.dao.entity.NotificationTemplates;
import com.expense.dao.entity.Roles;
import com.expense.dao.entity.UserAccessControl;
import com.expense.dao.entity.Users;
import com.expense.dao.repo.NotificationTemplateRepo;
import com.expense.dao.repo.RolesRepo;
import com.expense.dao.repo.UserAccessControlRepo;
import com.expense.dao.repo.UsersRepo;
import com.expense.service.NotificationService;
import com.expense.service.TokenService;
import com.expense.service.UserService;

import LibrarayManagemnt.entity.dao.Books;
import LibrarayManagemnt.entity.dao.IssueBookDetails;
import LibrarayManagemnt.entity.repo.IssueBookDetailsRepo;
import LibrarayManagemnt.entity.repo.bookRepo;
import LibrarayManagemnt.modalDto.BookInfo;
import search.GenericSpecification;
import search.RequestDTO;
import search.SpecificationUtils;



@Service
public class UserServiceImpl  implements UserService{
	
	private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
	
	@Autowired
	UsersRepo userRepo;
	
	@Autowired
	NotificationService notificatioonService;
	
	@Autowired
	TokenService tokenservice;
	
	@Value("${baseUrl}")
	String PasswordUrl;
	
	@Autowired
	NotificationTemplateRepo notificationRepo;
	
	@Value("${NotificationTemplate}")
	Long templateId;
	
	@Autowired
	CreateNotificationDTO createNotificationDto;
	
	@Autowired
	RolesRepo roleRepo;
	
	@Autowired
	UserAccessControlRepo userAccessRepo;
	
	@Autowired
	IssueBookDetailsRepo issueBookRepo;
	
	@Autowired
	bookRepo bookrepo;

	@Override
//	public ResponseDtO createUserService(CreateUserRequestDto requestDto) {	
//		
//		log.info("-- Inside create User Service Impl with request body --"+requestDto);
//		
//		ResponseDtO responseObj = new ResponseDtO();
//		
//		//checking wether the userEmail is already present in the table :
//	    log.info("-- checking wether the Email is already present in the table --"+requestDto.getEmailId());
//		Optional<Users> usersData = userRepo.findByUserEmail(requestDto.getEmailId());
//		
//	
//		LocalDateTime localDate = LocalDateTime.now();
//		if(usersData.isPresent() && localDate.isBefore(usersData.get().getTokenExpiry())) {
//			
//			
//
//			log.info("-- The email Id is already present for the user --"+requestDto.getEmailId() + " "+usersData.get().getUserFName());
//			responseObj.setSatus(0);
//			responseObj.setResponseMsg("User with email Id : "+requestDto.getEmailId()+" is already created");
//			responseObj.setBody(requestDto);
//			return responseObj;
//		}
//		
//		else {
//			
//			log.info("-- The email Id is not already present so inserting new user --"+requestDto.getEmailId() + " "+requestDto.getfName());
//		Users userObj = new Users();
//		//
//		userObj.setUserEmail(requestDto.getEmailId());
//		userObj.setUserType(requestDto.getUserType()); // Make sure DTO has userType ("GUEST" or "NORMAL")
//		
//		userObj.setUserFName(requestDto.getfName() != null ? requestDto.getfName() : "Guest");
//		userObj.setUserLName(requestDto.getlName() != null ? requestDto.getlName() : "User");
//		userObj.setUserMobile(requestDto.getPhone());
//		
//		// create a token for password
//		PasswordTokenResponse passwprdToken = tokenservice.createPasswordToken();
//		
//		//setting token in the databse
//		userObj.setToken(passwprdToken.getToken());
//		userObj.setTokenExpiry(passwprdToken.getTokenExpiry());
//		userObj.setMemberShipDays(requestDto.getMembershipDays());
//		
//		
//		try {
//			if ("GUEST".equalsIgnoreCase(requestDto.getUserType())) {
//		        log.info("-- Inserting new GUEST user -- " + requestDto.getEmailId());
//		        userObj.setIsEmailSent(0);
//		        userRepo.save(userObj);
//		        
//		        responseObj.setSatus(1);
//		        responseObj.setResponseMsg("Guest user session created successfully.");
//		        responseObj.setBody(userObj.getUserId());
//		        return responseObj;
//		    }
//			
//			else {
//				
//		
//			
//			Optional<NotificationTemplates> notificationObj = notificationRepo.findById(templateId);
//			
//			if(notificationObj.isPresent()) {
//				NotificationTemplates notifications =notificationObj.get();
//				
//				NotificationDto notificationDto = createNotificationDto.createNotificationDto(notifications, requestDto.getEmailId(), userObj, passwprdToken);
//						
//				String userName =userObj.getUserFName()+" "+userObj.getUserLName();
//
//				
//				
//				Integer emailResponse = notificatioonService.emailService(notificationDto,userObj.getUserId());
//				
//				 if (emailResponse == 200) {
//			            responseObj.setSatus(1);
//			            responseObj.setResponseMsg("User created successfully for: " + userName + " and email ID: " + userObj.getUserEmail() + ". Password creation email is sent.");
//			            responseObj.setBody(emailResponse);
//			            userObj.setIsEmailSent(1); // email sent successfully
//			        } else {
//			        	userObj.setIsEmailSent(0);// email not sent 
//			            responseObj.setSatus(0);
//			            responseObj.setResponseMsg("User created, but error in sending email to: " + userName + " (" + userObj.getUserEmail() + ")");
//			            responseObj.setBody(emailResponse);
//			        }
//				 
//				 userRepo.save(userObj);
//			}
//					   
//
//		}
//		}
//		catch (Exception e) {
//			// TODO: handle exception
//		}
//		    return responseObj;
//		}
//		
//	}
	
	public ResponseDtO createUserService(CreateUserRequestDto requestDto) {	
	    log.info("-- Inside create User Service Impl with request body --" + requestDto);
	    ResponseDtO responseObj = new ResponseDtO();
	    
	    // --- 1. MANUAL VALIDATION LAYER ---
	    // Always validate email for both types
	    if (requestDto.getEmailId() == null || requestDto.getEmailId().isEmpty()) {
	        throw new ValidationException("emailId", "Email is mandatory for all users.");
	    }
	    
	    // Validate UserType (Optional but recommended)
	    if (requestDto.getUserType() == null || requestDto.getUserType().isEmpty()) {
	        throw new ValidationException("userType", "User Type (GUEST/NORMAL) must be specified.");
	    }

	    // Strict validation ONLY for Normal Users
	    if ("NORMAL".equalsIgnoreCase(requestDto.getUserType())) {
	        String phone = requestDto.getPhone();
	        
	        // 1. Check if empty
	        if (phone == null || phone.isEmpty()) {
	            throw new ValidationException("phone", "Phone number is required for Normal registration.");
	        }
	        
	        // 2. Check the 10-digit pattern manually
	        if (!phone.matches("^[6-9]\\d{9}$")) {
	            throw new ValidationException("phone", "Phone number must be 10 digits and start with 6-9");
	        }
	    }

	    // --- 2. DUPLICATE CHECK ---
	    log.info("-- Checking whether the Email is already present in the table -- " + requestDto.getEmailId());
	    Optional<Users> usersData = userRepo.findByUserEmail(requestDto.getEmailId());
	    
	    LocalDateTime localDate = LocalDateTime.now();
	    if (usersData.isPresent()) {
	        // Only return "already created" if they aren't a guest or if their token is still active
	        if (usersData.get().getTokenExpiry() != null && localDate.isBefore(usersData.get().getTokenExpiry())) {
	            log.info("-- The email Id is already present and token is valid -- " + requestDto.getEmailId());
	            responseObj.setSatus(0);
	            responseObj.setResponseMsg("User with email Id: " + requestDto.getEmailId() + " is already created and active.");
	            responseObj.setBody(requestDto);
	            return responseObj;
	        }
	    }
	    
	    // --- 3. PREPARE THE USER OBJECT ---
	    Users userObj = new Users();
	    userObj.setUserEmail(requestDto.getEmailId());
	    userObj.setUserType(requestDto.getUserType()); 
	    
	    // Null-safe name handling
	    userObj.setUserFName(requestDto.getfName() != null ? requestDto.getfName() : "Guest");
	    userObj.setUserLName(requestDto.getlName() != null ? requestDto.getlName() : "User");
	    userObj.setUserMobile(requestDto.getPhone());

	    try {
	        // --- CASE 1: GUEST USER LOGIC ---
	        if ("GUEST".equalsIgnoreCase(requestDto.getUserType())) {
	            log.info("-- Inserting new GUEST user -- " + requestDto.getEmailId());
	            userObj.setIsEmailSent(0);
	            userRepo.save(userObj);
	            
	            responseObj.setSatus(1);
	            responseObj.setResponseMsg("Guest user session created successfully.");
	            responseObj.setBody(userObj.getUserId());
	            return responseObj; 
	        }
	        
	        // --- CASE 2: NORMAL USER LOGIC ---
	        log.info("-- Processing NORMAL user registration -- " + requestDto.getEmailId());
	        
	        PasswordTokenResponse passwprdToken = tokenservice.createPasswordToken();
	        userObj.setToken(passwprdToken.getToken());
	        userObj.setTokenExpiry(passwprdToken.getTokenExpiry());
	        userObj.setMemberShipDays(requestDto.getMembershipDays());
	        
	        userRepo.save(userObj);
	        
	        Optional<NotificationTemplates> notificationObj = notificationRepo.findById(templateId);
	        if (notificationObj.isPresent()) {
	            NotificationTemplates notifications = notificationObj.get();
	            NotificationDto notificationDto = createNotificationDto.createNotificationDto(notifications, requestDto.getEmailId(), userObj, passwprdToken);
	            
	            String userName = userObj.getUserFName() + " " + userObj.getUserLName();
//	            Integer emailResponse = notificatioonService.emailService(notificationDto, userObj.getUserId());
	            
	            Integer emailResponse =200;
	            
	            if (emailResponse == 200) {
	                responseObj.setSatus(1);
	                responseObj.setResponseMsg("User created successfully. Password email sent.");
	                responseObj.setBody(emailResponse);
	                userObj.setIsEmailSent(1); 
	            } else {
	                userObj.setIsEmailSent(0);
	                responseObj.setSatus(0);
	                responseObj.setResponseMsg("User created, but email failed.");
	                responseObj.setBody(emailResponse);
	            }
	            userRepo.save(userObj);
	        }

	    } catch (Exception e) {
	        log.error("-- Error in createUserService -- ", e);
	        responseObj.setSatus(0);
	        responseObj.setResponseMsg("An internal error occurred: " + e.getMessage());
	        responseObj.setBody(null);
	    }
	    
	    return responseObj;
	}

	@Override
	public ResponseDtO getUsresService(RequestDTO requestDto) {
		
		ResponseDtO responseDto = new ResponseDtO();
		
		String searchQuery = requestDto.getSearch();
		GenericSpecification<Users> specification = (searchQuery != null && !searchQuery.isEmpty())
				? SpecificationUtils.buildSpecificationFromSearch(searchQuery)
				: new GenericSpecification<>();
		
		Pageable pageable = SpecificationUtils.createPageable(requestDto);
		Page<Users> page = userRepo.findAll(specification, pageable);
		
		responseDto.setSatus(1);
		responseDto.setResponseMsg("Data found");
		responseDto.setBody(page.getContent());
		
		
		return responseDto;
	}

	@Override
	public ResponseDtO authenticateUser(AuthenticationRequestDTO requestDto) {
		
		log.info(" -- Inside Authentication Sercice with Request -- "+requestDto);
	
		ResponseDtO responseDto = new ResponseDtO();
		
		//checing if email is present 
		Optional<Users> usresObj = userRepo.findByUserEmail(requestDto.getEmailId());
		
		if(usresObj.isPresent()) {
			
			Users userdata = usresObj.get();
			
			boolean passwordMatches = BCrypt.checkpw(requestDto.getPassword(), userdata.getUserPassword());
			
			if(passwordMatches==true) {
				responseDto.setSatus(1);
				responseDto.setResponseMsg("Login successfully for !! "+requestDto.getEmailId());
				return responseDto;
			}
			
			else {
				responseDto.setSatus(0);
				responseDto.setResponseMsg("Invalid Password !! "+requestDto.getEmailId());
				return responseDto;
			}


		}
		
		else {
			responseDto.setSatus(0);
			responseDto.setResponseMsg("Invalid Email !! "+requestDto.getEmailId());
			return responseDto;
			
		}
		
			}

	@Override
	public ResponseDtO requestBookAccess(RequestBookAccessDto requestDto) {
		
		ResponseDtO responseObj = new ResponseDtO();
		
		
		Optional<UserAccessControl> accessControl = userAccessRepo.findByUserIdAndBookid(requestDto.getUserId(), requestDto.getBookId());
		
		UserAccessControl userAccessObj = new UserAccessControl();
		
		if(accessControl.isPresent()) {
			responseObj.setSatus(0);
			responseObj.setResponseMsg("The book Access is already given for book : "+accessControl.get().getBookid());
		}
		else {
			userAccessObj.setBookid(requestDto.getBookId());
			userAccessObj.setUserId(requestDto.getUserId());
			  
		}

		
		return null;
	}

	@Override
	public ResponseDtO userHistory(Long userId) {
		UserHistoryDTO history = new UserHistoryDTO();
		
		ResponseDtO responseObj = new ResponseDtO();
		
		Users user = userRepo.findById(userId).orElseThrow();
		history.setUserId(user.getUserId());
	    history.setUserName(user.getUserFName());
	    
	    List<IssueBookDetails> current = issueBookRepo.findByUserIdAndActualReturnDateIsNull(userId);
	    history.setCurrentBooks(current.stream().map(this::mapToBookInfo).collect(Collectors.toList()));
	    
	    List<IssueBookDetails> past = issueBookRepo.findByUserIdAndActualReturnDateIsNotNull(userId);
	    history.setEarlierBooks(past.stream().map(this::mapToBookInfo).collect(Collectors.toList()));
		
	    responseObj.setBody(history);
	    
		return responseObj;
	}
	
	private BookInfo mapToBookInfo(IssueBookDetails details) {
		BookInfo info = new BookInfo();
	    
	    // Fetch the book title using the bookId from the record
	    Optional<Books> book = bookrepo.findById(details.getBookId());
	    
	    if (book.isPresent()) {
	        info.setTitle(book.get().getBookName());
	    } else {
	        info.setTitle("Unknown Book (ID: " + details.getBookId() + ")");
	    }

	    info.setIssueDate(details.getIssueDate());
	    info.setReturnDate(details.getActualReturnDate()); // Will be null for current books
	    
	    return info;
	}

}
