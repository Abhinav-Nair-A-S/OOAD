package com.ilp.service;

import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.CurrentAccount;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.SavingsMaxAccount;

public class AccountManager {

	public static void manageAccount(Customer customer) {
		// TODO Auto-generated method stub

		SavingsMaxAccount savingsMaxAccount = null;
		CurrentAccount currentAccount = null;
		LoanAccount loanAccount = null;

		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEnter Cutomer ID : ");
		String customerCode = scanner.nextLine();
		if (customerCode.compareTo(customerCode) == 0) {
			System.out.println("\n" + customer.getCustomerName() + " has the following accounts : ");
			for (Account account : customer.getAccountList()) {
				if (account != null) {
					if (account.getProduct() instanceof SavingsMaxAccount) {
						savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
						System.out.println(savingsMaxAccount.getProductName());
					} else if (account.getProduct() instanceof CurrentAccount) {
						currentAccount = (CurrentAccount) account.getProduct();
						System.out.println(currentAccount.getProductName());
					} else if (account.getProduct() instanceof LoanAccount) {
						loanAccount = (LoanAccount) account.getProduct();
						System.out.println(loanAccount.getProductName());
					}
				}
			}
			System.out.println("\nEnter your choice : ");
			String productName = scanner.nextLine();
			char continueChoice;
			do {
				System.out.println("\n1. Cash Deposit\n2. Cash Withdrawal\n3. Display Balance\n\nEnter you choice");
				int manageAccountChoice = scanner.nextInt();
				switch (manageAccountChoice) {
				case 1:
					cashDeposit(productName, customer);
					break;
				case 2:
					cashWithdrawal(productName, customer);
					break;
				case 3:
					CustomerAccountConfiguration.displayCustomer(customer);
					break;
				}
				System.out.println("\nDo you want to continue [y/n] : ");
				continueChoice = scanner.next().charAt(0);
			} while (continueChoice == 'y');
		}

	}

	private static void cashDeposit(String productName, Customer customer) {
		// TODO Auto-generated method stub

		LoanAccount loanAccount = null;
		double cashDepositAmount, accountBalance;
		Scanner scanner = new Scanner(System.in);
		for (Account account : customer.getAccountList()) {
			if (account != null) {
				if (account.getProduct().getProductName().compareTo(productName) == 0) {
					if (account.getProduct() instanceof LoanAccount) {
						loanAccount = (LoanAccount) account.getProduct();
						System.out.println("\nEnter the amount you want to deposit : ");
						cashDepositAmount = scanner.nextDouble();
						System.out.println("Is the cash deposit via Cheque [y/n] : ");
						char answer = scanner.next().charAt(0);
						if (answer == 'y') {
							double newDepositAmount = cashDepositAmount
									- (cashDepositAmount * (loanAccount.getServiceRate() / 100));
							accountBalance = account.getAccountBalance();
							accountBalance += newDepositAmount;
							account.setAccountBalance(accountBalance);
						} else {
							accountBalance = account.getAccountBalance();
							accountBalance += cashDepositAmount;
							account.setAccountBalance(accountBalance);
						}
					} else {
						System.out.println("\nEnter the amount you want to deposit : ");
						cashDepositAmount = scanner.nextDouble();
						accountBalance = account.getAccountBalance();
						accountBalance += cashDepositAmount;
						account.setAccountBalance(accountBalance);
					}
				}
			}
		}
	}

	private static void cashWithdrawal(String productName, Customer customer) {
		// TODO Auto-generated method stub

		SavingsMaxAccount savingsMaxAccount = null;
		double cashWithdrawalAmount, accountBalance, newAccountBalance;
		Scanner scanner = new Scanner(System.in);
		for (Account account : customer.getAccountList()) {
			if (account != null) {
				if (account.getProduct().getProductName().compareTo(productName) == 0) {
					if (account.getProduct() instanceof SavingsMaxAccount) {
						savingsMaxAccount = (SavingsMaxAccount) account.getProduct();
						System.out.println("\nEnter the amount you want to withdraw : ");
						cashWithdrawalAmount = scanner.nextDouble();
						accountBalance = account.getAccountBalance();
						newAccountBalance = accountBalance - cashWithdrawalAmount;
						if ((accountBalance >= savingsMaxAccount.getMinimumBalance())
								&& (newAccountBalance >= savingsMaxAccount.getMinimumBalance()))
							account.setAccountBalance(newAccountBalance);
						else
							System.out.println("\nNo sufficient minimum balance in your savings account!");
					} else {
						System.out.println("\nEnter the amount you want to withdraw : ");
						cashWithdrawalAmount = scanner.nextDouble();
						accountBalance = account.getAccountBalance();
						newAccountBalance = accountBalance - cashWithdrawalAmount;
						if ((accountBalance >= 0) && (newAccountBalance >= 0))
							account.setAccountBalance(newAccountBalance);
						else
							System.out.println("\nNo sufficient balance in account!");
					}
				}
			}
		}
	}

}
