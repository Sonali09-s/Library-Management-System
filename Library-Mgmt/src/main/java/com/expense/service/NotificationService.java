package com.expense.service;

import com.expense.ModalDto.NotificationDto;
import com.expense.dao.entity.NotificationTemplates;

public interface NotificationService {
	
	public Integer emailService(NotificationDto notificationDto, Long userId);

}
