package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class CustomerAccountConfiguration {

	static ArrayList<Account> accountList = new ArrayList<Account>();
	static String customerName = null;

	public static Customer createCustomer(ArrayList<Product> productList, Customer customerOld) {
		// TODO Auto-generated method stub

		Product selectedProduct = null;
		String customerCode = null;
		Customer customer = null;
		System.out.println("\nThe Availabe Products are : ");
		int i = 1;
		for (Product product : productList) {
			System.out.println(i + ". " + product.getProductName());
			i++;
		}
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter the product you want : ");
		String productName = scanner.nextLine();
		for (Product product : productList)
			if (product.getProductName().compareTo(productName) == 0)
				selectedProduct = product;
		if (customerOld == null) {
			System.out.println("\nEnter the customer code : ");
			customerCode = scanner.nextLine();
			System.out.println("Enter the customer name : ");
			customerName = scanner.nextLine();
			accountList.add(createAccount(selectedProduct));
			customer = new Customer(customerCode, customerName, accountList);
		} else
			accountList.add(createAccount(selectedProduct));
		if (customerOld == null)
			return customer;
		else
			return customerOld;

	}

	public static Account createAccount(Product product) {
		// TODO Auto-generated method stub

		SavingsMaxAccount savingsMaxAccount = null;
		CurrentAccount currentAccount = null;
		LoanAccount loanAccount = null;
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter the Account No : ");
		String accountNo = scanner.nextLine();
		System.out.println("Enter the Opening Balance : ");
		double balance = scanner.nextDouble();
		if (product.getProductName().compareTo("Savings Max Account") == 0) {
			if (balance < 1000)
				System.out.println("\nAmount less than minimum balance. Try Again");
			else {
				savingsMaxAccount = new SavingsMaxAccount(product.getProductCode(), product.getProductName(),
						product.getServiceList(), 1000);
				product = savingsMaxAccount;
				displayservices(product);
			}
		} else if (product.getProductName().compareTo("Current Account") == 0) {
			currentAccount = new CurrentAccount(product.getProductCode(), product.getProductName(),
					product.getServiceList());
			product = currentAccount;
			displayservices(product);
		} else if (product.getProductName().compareTo("Loan Account") == 0) {
			loanAccount = new LoanAccount(product.getProductCode(), product.getProductName(), product.getServiceList(),
					0.3);
			product = loanAccount;
			displayservices(product);
		}
		if (product.getProductName().compareTo("Savings Max Account") == 0) {
			if (balance >= 1000)
				return new Account(accountNo, balance, product);
			else
				return null;
		} else
			return new Account(accountNo, balance, product);
		
	}

	private static void displayservices(Product product) {
		// TODO Auto-generated method stub

		SavingsMaxAccount savingsMaxAccount = null;
		CurrentAccount currentAccount = null;
		LoanAccount loanAccount = null;

		if (product instanceof SavingsMaxAccount) {
			savingsMaxAccount = (SavingsMaxAccount) product;
			System.out.println("\n" + customerName + " has created " + savingsMaxAccount.getProductName()
					+ " with the followong services : ");
			for (Service service : savingsMaxAccount.getServiceList())
				System.out.println(service.getServiceName());
			System.out.println("\nAccount Active!!");
		} else if (product instanceof CurrentAccount) {
			currentAccount = (CurrentAccount) product;
			System.out.println("\n" + customerName + " has created " + currentAccount.getProductName()
					+ " with the followong services : ");
			for (Service service : currentAccount.getServiceList())
				System.out.println(service.getServiceName());
			System.out.println("\nAccount Active!!");
		} else if (product instanceof LoanAccount) {
			loanAccount = (LoanAccount) product;
			System.out.println("\n" + customerName + " has created " + loanAccount.getProductName()
					+ " with the followong services : ");
			for (Service service : loanAccount.getServiceList())
				System.out.println(service.getServiceName());
			System.out.println("\nAccount Active!!");
		}

	}

	public static void displayCustomer(Customer customer) {
		// TODO Auto-generated method stub

		SavingsMaxAccount savingsMaxAccount = null;
		CurrentAccount currentAccount = null;
		LoanAccount loanAccount = null;

		System.out.println("\n\n*************************Customer-Account Details****************");
		System.out.println("CustomerId " + " CustomerName " + " AccountType " + " Balance");
		System.out.println("***************************************************************");
		for (Account account : accountList) {
			if (account != null) {
				if (account.getProduct() instanceof SavingsMaxAccount) {
					savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
					System.out.println("\n" + customer.getCustomerCode() + "     " + customer.getCustomerName()
							+ "     " + savingsMaxAccount.getProductName() + "     " + account.getAccountBalance());
					System.out.println("Services Provided");
					for (Service service : savingsMaxAccount.getServiceList())
						System.out.print(service.getServiceName() + "   ");
				} else if (account.getProduct() instanceof CurrentAccount) {
					currentAccount = (CurrentAccount) account.getProduct();
					System.out.println("\n" + customer.getCustomerCode() + "     " + customer.getCustomerName()
							+ "     " + currentAccount.getProductName() + "     " + account.getAccountBalance());
					System.out.println("Services Provided");
					for (Service service : currentAccount.getServiceList())
						System.out.print(service.getServiceName() + "   ");
				} else if (account.getProduct() instanceof LoanAccount) {
					loanAccount = (LoanAccount) account.getProduct();
					System.out.println("\n" + customer.getCustomerCode() + "     " + customer.getCustomerName()
							+ "     " + loanAccount.getProductName() + "     " + account.getAccountBalance());
					System.out.println("Services Provided");
					for (Service service : loanAccount.getServiceList())
						System.out.print(service.getServiceName() + "   ");
				}
			}
		}
		
	}

	public static void transaction(Customer customer) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter Customer Code : ");
		String customerCode = scanner.nextLine();
		if (customerCode.compareTo(customer.getCustomerCode()) == 0) {
			System.out.println("\n" + customer.getCustomerName() + " has the following accounts : ");
			int i = 1;
			for (Account account : accountList) {
				if (account != null) {
					System.out.println(i + ". " + account.getProduct().getProductCode() + "   "
							+ account.getProduct().getProductName());
					i++;
				}
			}
			System.out.println("\nEnter the product code you want to choose : ");
			String accountChoice = scanner.nextLine();
			for (Account account : accountList) {
				if (account != null) {
					if (account.getProduct().getProductCode().compareTo(accountChoice) == 0) {
						int j = 1;
						System.out.println("\nThe Services available are : ");
						for (Service service : account.getProduct().getServiceList()) {
							System.out.println(j + ". " + service.getServiceCode() + "    " + service.getServiceName());
							j++;
						}
						System.out.println("\nEnter the service code you want to choose : ");
						String serviceChoice = scanner.nextLine();
						for (Service service : account.getProduct().getServiceList())
							if (service.getServiceCode().compareTo(serviceChoice) == 0) {
								System.out.println("\nEnter the number of transactions : ");
								int noOfTransactions = scanner.nextInt();
								System.out.println(
										"\nTotal Service Amount : " + service.getServiceRate() * noOfTransactions);
							}
					}
				}
			}
		}
	
	}
	
}
