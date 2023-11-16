package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {

	private double serviceRate;

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList, double serviceRate) {
		super(productCode, productName, serviceList);
		this.serviceRate = serviceRate;
	}

	public double getServiceRate() {
		return serviceRate;
	}
	public void setServiceRate(double serviceRate) {
		this.serviceRate = serviceRate;
	}

}
