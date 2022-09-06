package com.vehicle.rental.model;

public class Vehicle {
	private String vehicleId;
	private VehicleType vechicleType;
	private int rentalPrice;

	public Vehicle(String vehicleId, VehicleType vechicleType, int rentalPrice) {
		this.vehicleId = vehicleId;
		this.vechicleType = vechicleType;
		this.rentalPrice = rentalPrice;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public VehicleType getVechicleType() {
		return vechicleType;
	}

	public void setVechicleType(VehicleType vechicleType) {
		this.vechicleType = vechicleType;
	}

	public int getRentalPrice() {
		return rentalPrice;
	}

	public void setRentalPrice(int rentalPrice) {
		this.rentalPrice = rentalPrice;
	}

	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", vechicleType=" + vechicleType + ", rentalPrice=" + rentalPrice
				+ "]";
	}

}
