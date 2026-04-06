//package com.expense.service.serviceImpl;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.expense.EntityModal.Expenses;
//import com.expense.ModalDto.AddExpenseRequestDto;
//import com.expense.repo.ExpensesRepo;
//import com.expense.service.AddExpenseService;
//
//
//@Service
//public class AddExpenseServiceImpl implements AddExpenseService {
//	
//	@Autowired
//	ExpensesRepo expenseRepo;
//
//	@Override
//	public Object addExpenseService(AddExpenseRequestDto requestObj) {
//		
//		Optional<Expenses> expenseObj = expenseRepo.findById(requestObj.getId());
//		
//		Expenses savee = null;
//		
//		Expenses expenseOb1j = new Expenses();
//		
//		if(expenseObj.isEmpty()) {
//			return "No previous data";
//			
//		}
//		expenseOb1j.setMmount(requestObj.getSpentAmount());
//		expenseOb1j.setName(requestObj.getExpenseName());
//		
//		try{
//		savee = expenseRepo.save(expenseOb1j);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//			return "failure in try";
//		}
//		
//		
//		return "Success";
//	}
//
//}
