package com.expense.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.expense.dao.repo.UsersRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	UsersRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userRepo.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
	}

}
