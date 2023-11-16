package com.ilp.entity;

public class Service {

	private String serviceCode;
	private String serviceName;
	private float serviceRate;

	public Service(String serviceCode, String serviceName, float serviceRate) {
		this.serviceCode = serviceCode;
		this.serviceName = serviceName;
		this.serviceRate = serviceRate;
	}

	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public float getServiceRate() {
		return serviceRate;
	}
	public void setServiceRate(float serviceRate) {
		this.serviceRate = serviceRate;
	}

}