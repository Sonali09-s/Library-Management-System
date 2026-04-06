package com.expense.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import LibrarayManagemnt.service.serviceImpl.DeactivateOldMembership;

@SpringBootApplication
//@ComponentScan(basePackages = { "com.expense.controller","com.expense.service.serviceImpl","com.expense.config"})
@EnableScheduling
@ComponentScan(basePackages = { "com.expense","com.users","LibrarayManagemnt"})
@EnableJpaRepositories(basePackages = { "com.expense.dao.repo","LibrarayManagemnt.entity.repo" })
@EntityScan(basePackages = { 
	    "com.expense.dao.entity","LibrarayManagemnt.entity,dao" 
	})
public class ExpenseTrackerApplication {
	
//	DeactivateOldMembership demembership;

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
		
		System.out.println("Project started");
	}

}
