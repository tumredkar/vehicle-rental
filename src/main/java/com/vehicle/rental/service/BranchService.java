package com.vehicle.rental.service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.vehicle.rental.data.BranchRepository;
import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.VehicleType;

/**
 * @author Tushar Umredkar
 *
 */
public class BranchService {

	public static String addBranch(String data) {
		String[] lineElements = data.split("\\s+");
		int i = 1;
		// Branch Name, Vehicle Type
		// ADD_BRANCH B1 CAR,BIKE,VAN
		String branchName = lineElements[i++];
		String[] vehicles = lineElements[i++].split(",");

		// check if branch already exist
		Branch branch = BranchRepository.getInstance().getBranchByBranchName(branchName);
		if (Objects.nonNull(branch)) {
			return Boolean.toString(false).toUpperCase();
		}

		// create availableVehicleSet for branch
		Set<VehicleType> vehicleTypeSet = new HashSet<>();
		for (String vehicleType : vehicles) {
			if (VehicleType.UNKNOWN.equals(VehicleType.get(vehicleType))) {
				return Boolean.toString(false).toUpperCase();
			}
			vehicleTypeSet.add(VehicleType.get(vehicleType));
		}

		// create branch and add to repository
		branch = new Branch(branchName, vehicleTypeSet);
		BranchRepository.getInstance().addVehicleToBranch(branch, null);
		return Boolean.toString(true).toUpperCase();
	}

}
