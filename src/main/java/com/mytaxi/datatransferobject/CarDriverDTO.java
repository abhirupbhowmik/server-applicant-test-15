package com.mytaxi.datatransferobject;

public class CarDriverDTO {
	
	private CarDTO carDetails;
	private DriverDTO driverDetails;
	
	public CarDTO getCarDetails() {
		return carDetails;
	}
	public void setCarDetails(CarDTO carDetails) {
		this.carDetails = carDetails;
	}
	public DriverDTO getDriverDetails() {
		return driverDetails;
	}
	public void setDriverDetails(DriverDTO driverDetails) {
		this.driverDetails = driverDetails;
	}
	
	
}
