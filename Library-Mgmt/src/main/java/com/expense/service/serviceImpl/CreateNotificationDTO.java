package com.expense.service.serviceImpl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.expense.ModalDto.NotificationDto;
import com.expense.ModalDto.PasswordTokenResponse;
import com.expense.dao.entity.NotificationTemplates;
import com.expense.dao.entity.Users;

@Service
public class CreateNotificationDTO {
	
	@Value("${baseUrl}")
	String PasswordUrl;
	
	public NotificationDto createNotificationDto(NotificationTemplates notifications,String emailId, Users user, PasswordTokenResponse tokenResponse) {		
		 NotificationDto dto = new NotificationDto();
		    dto.setDestinationTo(emailId);
		    dto.setContent(notifications.getContent());
		    dto.setContentType(notifications.getContentType());
		    dto.setSubject(notifications.getSubject());
		    dto.setDestinationFrom(notifications.getDestinationFrom());

		    String userName = user.getUserFName() + " " + user.getUserLName();
		    String tokenEncoded = URLEncoder.encode(tokenResponse.getToken(), StandardCharsets.UTF_8);
		    String setPasswordLink = PasswordUrl + "?token=" + tokenEncoded;

		    String finalHtml = String.format(dto.getContent(), userName, setPasswordLink);
		    dto.setContent(finalHtml);

		    return dto;
		
		
		
	}

}
