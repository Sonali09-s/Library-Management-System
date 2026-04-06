//package com.expense.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//@RequestMapping("/userpPassowrd")
//public class SetPasswordViewCntrl {
//	
//	@GetMapping("/setPassword")
//	public ModelAndView showSetPasswordPage(@RequestParam String token) {
//	    ModelAndView mav = new ModelAndView("set-Password"); // This should match your Thymeleaf template name (set-password.html)
//	    mav.addObject("token", token); // Add token to the model
//	    return mav;
//	    
//	}
//
//}
