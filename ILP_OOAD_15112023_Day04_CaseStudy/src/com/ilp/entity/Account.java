package com.ilp.entity;

public class Account {

	private String accountNo;
	private double accountBalance;
	private Product product;

	public Account(String accountNo, double accountBalance, Product product) {
		this.accountNo = accountNo;
		this.accountBalance = accountBalance;
		this.product = product;
	}

	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double balance) {
		this.accountBalance = balance;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

}