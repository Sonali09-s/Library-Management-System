package com.expense.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumberSeries {

//	
//	public static void main(String args[]) {
//		
////		System.out.println("herllo");
//		
//		Integer newAra[] = {1,2,3,4};
//		
////		List<String> arraylist = new ArrayList<>();
////		for(String S :arraylist)
//		boolean number =false;
//		
//		for(Integer n :newAra) {
//			
//			if(n%n==0 && n%1==0) {
//				System.out.println("its prime "+n);
//			}
//			
//			
//			if(n<=1) {
//				continue;
//			}
//			
//			boolean isprime =true;
//			
//			for(int i=2;i<=n/2;i++) {
//				if(n%i==0) {
//					 isprime = false;
////					System.out.println("n is prime "+n);
//					break;
//				}
//			
//			}
//	
//			if(isprime) {
//				System.out.println("its prime number "+n);
//			}
//			
//			else {
//				System.out.println("not prime "+n);
//			}
//		}
//	}
	
	public static void main (String []args) {
		String name ="Hello";
		
		String reversed ="";
		
		for(int i= name.length()-1; i>=0; i--) {
			reversed+= name.charAt(i);
			
		}
		
		System.out.println(reversed);
	}
	
}
