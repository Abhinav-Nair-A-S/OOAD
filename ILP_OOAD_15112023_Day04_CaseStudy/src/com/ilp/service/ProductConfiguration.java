package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Product;
import com.ilp.entity.Service;

public class ProductConfiguration {

	public static ArrayList<Service> createServices() {
		// TODO Auto-generated method stub
		
		ArrayList<Service> serviceList = new ArrayList<Service>();
		String serviceCode,serviceName;
		float serviceRate;
		char continueChoice;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("\nEnter Service Code : ");
			serviceCode = scanner.nextLine();
			System.out.println("Enter Service Name : ");
			serviceName = scanner.nextLine();	
			System.out.println("Enter Service Rate : ");
			serviceRate = scanner.nextFloat();
			serviceList.add(new Service(serviceCode,serviceName,serviceRate));
			System.out.println("\nWould you like to add another service [y/n] : ");
			continueChoice = scanner.next().charAt(0);
			scanner.nextLine();
		}while(continueChoice == 'y');		
		return serviceList;
		
	}

	public static ArrayList<Product> createProducts(ArrayList<Service> serviceList) {
		// TODO Auto-generated method stub
		
		ArrayList<Product> productList = new ArrayList<Product>();
		char productContinueChoice;
		do {
		ArrayList<Service> selectedServiceList = new ArrayList<Service>();
		Scanner scanner = new Scanner(System.in);		
		System.out.println("\nEnter Product Code : ");
		String productCode = scanner.nextLine();
		System.out.println("Enter Product Name : ");
		String productName = scanner.nextLine();
		System.out.println("\nThe Services Available are : ");
		System.out.println("Service Code " + " Service Name " + " Service Rate");
		for(Service service : serviceList)
			System.out.println(service.getServiceCode() + "        " + service.getServiceName() + "        " + service.getServiceRate());
		char serviceContinueChoice;
		do {
			System.out.println("\nEnter the Service Code you want to add : ");
			String serviceCode = scanner.nextLine();
			for(Service service : serviceList)
				if(service.getServiceCode().compareTo(serviceCode)==0)
					selectedServiceList.add(service);
			System.out.println("Service Added. Do you want to add more services [y/n] : ");
			serviceContinueChoice = scanner.next().charAt(0);
			scanner.nextLine();
		}while(serviceContinueChoice == 'y');
		System.out.println("\nThe services selected for this product are : ");
		System.out.println("Service Code " + " Service Name " + " Service Rate");
		for(Service service : selectedServiceList)
			System.out.println(service.getServiceCode() + "        " + service.getServiceName() + "        " + service.getServiceRate());
		productList.add(new Product(productCode,productName,selectedServiceList));
		System.out.println("\nDo you want to add more products [y/n] : ");
		productContinueChoice = scanner.next().charAt(0);
		}while(productContinueChoice == 'y');		
		return productList;
	}

}
