/**
 * 
 */
package com.vehicle.rental.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import com.vehicle.rental.data.BookingRepository;
import com.vehicle.rental.data.BranchRepository;
import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.Vehicle;
import com.vehicle.rental.model.VehicleType;

/**
 * @author Tushar Umredkar
 *
 */
public class VehicleService {

	/**
	 * get all vehicle associated with branch in ascending order of rentalPrice
	 * 
	 * @param branch
	 * @return
	 */
	public static List<Vehicle> getAllVehicleOfBranch(Branch branch) {
		List<Vehicle> vehicles = BranchRepository.getInstance().getVehiclesOfBranch(branch);
		Collections.sort(vehicles, Comparator.comparing(Vehicle::getRentalPrice));
		return vehicles;
	}

	public static String addVehicle(String data) {
		String[] lineElements = data.split("\\s+");
		int i = 1;
		// Branch Name, Vehicle Type, vehicle id, price
		String branchName = lineElements[i++];
		String vehicleType = lineElements[i++];
		String vehicleId = lineElements[i++];
		String rentalPrice = lineElements[i++];
		VehicleType type = VehicleType.get(vehicleType);

		// get branch by branchName
		Branch branch = BranchRepository.getInstance().getBranchByBranchName(branchName);

		// if branch is not found return false
		if (Objects.isNull(branch)) {
			return Boolean.toString(false).toUpperCase();
		}

		// if given vehicleType is not available in branch availableVehicleTypes return
		// false
		if (!branch.getAvailableVehicleTypes().contains(type)) {
			return Boolean.toString(false).toUpperCase();
		}

		// if branch already contains vehicle with same vehicleId return false
		if (BranchRepository.getInstance().isDuplicateVehicleEntry(branch, vehicleId)) {
			return Boolean.toString(false).toUpperCase();
		}

		Vehicle vehicle = new Vehicle(vehicleId, type, Integer.parseInt(rentalPrice));
		BranchRepository.getInstance().addVehicleToBranch(branch, vehicle);
		BookingRepository.getInstance().addVehicle(vehicle);
		return Boolean.toString(true).toUpperCase();
	}
}
