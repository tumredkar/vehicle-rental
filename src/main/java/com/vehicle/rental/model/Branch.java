package com.vehicle.rental.model;

import java.util.Set;

/**
 * @author Tushar Umredkar
 *
 */
public class Branch {

	private String branchName;
	private Set<VehicleType> availableVehicleTypes;

	public Branch(String branchName, Set<VehicleType> availableVehicleTypes) {
		this.branchName = branchName;
		this.availableVehicleTypes = availableVehicleTypes;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public Set<VehicleType> getAvailableVehicleTypes() {
		return availableVehicleTypes;
	}

	public void setAvailableVehicleTypes(Set<VehicleType> availableVehicleTypes) {
		this.availableVehicleTypes = availableVehicleTypes;
	}

}
