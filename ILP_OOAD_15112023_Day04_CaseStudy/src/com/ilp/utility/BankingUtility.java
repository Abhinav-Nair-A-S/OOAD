package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.AccountManager;
import com.ilp.service.CustomerAccountConfiguration;
import com.ilp.service.ProductConfiguration;

public class BankingUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		Customer customer = null;
		Scanner scanner = new Scanner(System.in);
		int mainMenuChoice;
		do {
			System.out.println("\n******Welcome To Bank************\n1. Create Services\n2. Create Products\n3. Create Account\n4. Manage Accounts\n5. Transaction Bill\n6. Exit");
			System.out.println("\nEnter your choice : ");
			mainMenuChoice = scanner.nextInt();
			switch(mainMenuChoice) {
			case 1 : 
				serviceList = ProductConfiguration.createServices();
				break;
			case 2 :
				productList = ProductConfiguration.createProducts(serviceList);
				break;
			case 3 :				
				customer = CustomerAccountConfiguration.createCustomer(productList,customer);
				break;
			case 4 : 
				AccountManager.manageAccount(customer);
				break;
			case 5 : 
				CustomerAccountConfiguration.transaction(customer);
				break;				
			}
		}while(mainMenuChoice!=6);
		
	}

}
