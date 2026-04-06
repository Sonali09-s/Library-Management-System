package com.expense.dao.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.dao.entity.NotificationTemplates;

@Repository
public interface NotificationTemplateRepo  extends JpaRepository<NotificationTemplates, Long>{

}
