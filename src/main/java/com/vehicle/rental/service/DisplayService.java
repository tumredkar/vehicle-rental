package com.vehicle.rental.service;

import java.util.List;
import java.util.StringJoiner;

import com.vehicle.rental.data.BranchRepository;
import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.Vehicle;

/**
 * @author Tushar Umredkar
 *
 */
public class DisplayService {

	public static void displayVehicles(String data) {
		String[] lineElements = data.split("\\s+");
		int i = 1;

		// Branch id, start time, end time
		String branchName = lineElements[i++];
		int startTime = Integer.parseInt(lineElements[i++]);
		int endTime = Integer.parseInt(lineElements[i++]);

		// get branch by branchName
		Branch branch = BranchRepository.getInstance().getBranchByBranchName(branchName);

		// get vehicles associated with branch
		List<Vehicle> vehicles = BranchRepository.getInstance().getVehiclesOfBranch(branch);
		StringJoiner result = new StringJoiner(",");
		for (Vehicle vehicle : vehicles) {
			if (BookingService.isVehicleAvailableForBooking(vehicle, startTime, endTime)) {
				result.add(vehicle.getVehicleId());
			}
		}
		System.out.println(result.toString());
	}
}
