package com.vehicle.rental.data;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.Vehicle;
import com.vehicle.rental.model.VehicleType;

/**
 * @author Tushar Umredkar
 *
 */
public class BranchRepository {

	private Map<Branch, List<Vehicle>> vehicleMap;
	private static BranchRepository instance;

	private BranchRepository() {
		vehicleMap = new HashMap<>();
	}

	public static synchronized BranchRepository getInstance() {
		if (instance == null) {
			instance = new BranchRepository();
		}
		return instance;
	}

	/**
	 * associate vehicle with branch
	 * 
	 * @param branch
	 * @param vehicle
	 */
	public void addVehicleToBranch(Branch branch, Vehicle vehicle) {
		vehicleMap.putIfAbsent(branch, new ArrayList<>());
		if (Objects.nonNull(vehicle)) {
			vehicleMap.get(branch).add(vehicle);
		}
	}

	/**
	 * get All vehicle associated with branch
	 * 
	 * @param branch
	 * @return
	 */
	public List<Vehicle> getVehiclesOfBranch(Branch branch) {
		return vehicleMap.getOrDefault(branch, new ArrayList<>());
	}

	/**
	 * get All vehicle associated with branch by vehicleType
	 * 
	 * @param branch
	 * @param vehicleType
	 * @return
	 */
	public List<Vehicle> getVehiclesOfBranchByVehicleType(Branch branch, VehicleType vehicleType) {
		return vehicleMap.getOrDefault(branch, new ArrayList<>()).stream()
				.filter(vehicle -> vehicle.getVechicleType().equals(vehicleType))
				.sorted(Comparator.comparing(Vehicle::getRentalPrice)).collect(Collectors.toList());
	}

	/**
	 * get branch by branchName
	 * 
	 * @param branchName
	 * @return
	 */
	public Branch getBranchByBranchName(String branchName) {
		return vehicleMap.keySet().stream().filter(branch -> branch.getBranchName().equals(branchName)).findFirst()
				.orElse(null);
	}

	/**
	 * check if branch contains vehicle with same vehicleId
	 * 
	 * @param branch
	 * @param vehicleId
	 * @return
	 */
	public boolean isDuplicateVehicleEntry(Branch branch, String vehicleId) {
		Vehicle vehicle = vehicleMap.get(branch).stream()
				.filter(tempVehicle -> tempVehicle.getVehicleId().equals(vehicleId)).findAny().orElse(null);
		return Objects.nonNull(vehicle);
	}
}
