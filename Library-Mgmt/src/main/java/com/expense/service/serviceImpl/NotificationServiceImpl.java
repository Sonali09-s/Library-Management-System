package com.expense.service.serviceImpl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.NotificationDto;
import com.expense.dao.entity.NotificationTemplates;
import com.expense.service.DumpService;
import com.expense.service.NotificationService;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class NotificationServiceImpl implements NotificationService {
	private static final Logger log = LogManager.getLogger(NotificationService.class);

    @Value("${sendgrid.api.key}")
    private String sendGridApiKey;
    
    @Autowired
    DumpService dumpService;

	@Override
	public Integer emailService(NotificationDto notificationDto,Long userId)  {
		
		log.info(" -- Inside email Service Impl for userId :"+userId);
		
		Email from = new Email(notificationDto.getDestinationFrom()); // must be verified in SendGrid
	    Email toEmail = new Email(notificationDto.getDestinationTo());
	    Content content =null;
	    
	    if(notificationDto.getContentType().equalsIgnoreCase("html")) {
	     content = new Content("text/html", notificationDto.getContent()); // <-- use text/html for HTML email
	    }
	    Mail mail = new Mail(from, notificationDto.getSubject(), toEmail, content );

	    SendGrid sg = new SendGrid(sendGridApiKey);
	    Request request = new Request();

	    try {
	        request.setMethod(Method.POST);
	        request.setEndpoint("mail/send");
	        request.setBody(mail.build());
	       
	        log.info("-- After setting the request for userId --:"+userId+ " "+request.getBody());
	        
	        
	        // settinng the Api Request in the dump table 
	        Long id = dumpService.dumpService(userId, request.getBody(), 1, null);
	        
	        
	        Response response = sg.api(request);
	        
	        log.info("-- Response recieved from Send Grid -- "+response.getStatusCode()+"  For userId :"+userId);
	        
	        //settint the apiResponse in the dump table :
	        dumpService.dumpService(userId, response.getBody().toString(), 2, id);
	        if(response.getStatusCode()==202) {
	        	return 200; // sucessful response from send grid API
	        }
	        else {
	        	return 400; // some error in response
	        }
	       
	    } catch (IOException ex) {
	    	ex.printStackTrace();
	    }
		return 200;
		
	}

}
