package com.vehicle.rental.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.vehicle.rental.common.MyConstants;
import com.vehicle.rental.data.BookingRepository;
import com.vehicle.rental.data.BranchRepository;
import com.vehicle.rental.model.Branch;
import com.vehicle.rental.model.Vehicle;
import com.vehicle.rental.model.VehicleType;

/**
 * @author Tushar Umredkar
 *
 */
public class BookingService {

	// PERCENTAGE INCREASE IN RENTAL PRICE
	private static double DYNAMIC_PRICE_MULTIPLIER = 10;

	// THRESHOLD AFTER WHICH DYNAMIC PRICE WILL COME IN EFFECT
	private static double DYNAMIC_PRICE_THREASHOLD = 80;

	private static BookingService instance;

	private BookingService() {
	}

	public static synchronized BookingService getInstance() {
		if (instance == null) {
			instance = new BookingService();
		}
		return instance;
	}

	public static List<Vehicle> getAllVehicleOfBranch(Branch branch) {
		List<Vehicle> vehicles = BranchRepository.getInstance().getVehiclesOfBranch(branch);
		Collections.sort(vehicles, Comparator.comparing(Vehicle::getRentalPrice));
		return vehicles;
	}

	public static String addBranch(String data) {
		String[] lineElements = data.split("\\s+");
		int i = 1;
		// Branch Name, Vehicle Type
		String branchName = lineElements[i++];
		String[] vehicles = lineElements[i++].split(",");
		Set<VehicleType> vehicleTypes = new HashSet<>();
		for (String vehicleType : vehicles) {
			if (VehicleType.UNKNOWN.equals(VehicleType.get(vehicleType))) {
				return MyConstants.FALSE;
			}
			vehicleTypes.add(VehicleType.get(vehicleType));
		}
		Branch branch = new Branch(branchName, vehicleTypes);
		BranchRepository.getInstance().addVehicleToBranch(branch, null);
		return MyConstants.TRUE;
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
		Branch branch = BranchRepository.getInstance().getBranchByBranchName(branchName);
		if (branch == null) {
			return MyConstants.FALSE;
		}
		if (!branch.getAvailableVehicleTypes().contains(type)) {
			return MyConstants.FALSE;
		}
		if (BranchRepository.getInstance().isDuplicateVehicleEntry(branch, vehicleId)) {
			return MyConstants.FALSE;
		}
		Vehicle vehicle = new Vehicle(vehicleId, type, Integer.parseInt(rentalPrice));
		BranchRepository.getInstance().addVehicleToBranch(branch, vehicle);
		BookingRepository.getInstance().addVehicle(vehicle);
		return MyConstants.TRUE;
	}

	public static int bookVehicle(String data) {
		String[] lineElements = data.split("\\s+");
		int i = 1;
		// Branch Name, Vehicle Type, vehicle id, price
		String branchName = lineElements[i++];
		String vehicleType = lineElements[i++];
		int startTime = Integer.parseInt(lineElements[i++]);
		int endTime = Integer.parseInt(lineElements[i++]);
		Branch branch = BranchRepository.getInstance().getBranchByBranchName(branchName);
		List<Vehicle> vehicles = BranchRepository.getInstance().getVehiclesOfBranchByVehicleType(branch,
				VehicleType.get(vehicleType));
		for (Vehicle vehicle : vehicles) {
			List<Boolean> availability = BookingRepository.getInstance().getVehicleAvailability(vehicle);
			if (isAvailable(vehicle, startTime, endTime)) {
				int rentalPrice = getDynamicRentPrice(vehicle.getRentalPrice(), getAllVehicleOfBranch(branch));
				for (int j = startTime; j <= endTime; j++) {
					availability.add(j, false);
				}
				return (endTime - startTime) * rentalPrice;
			}
		}
		return -1;
	}

	// Dynamic pricing
	private static int getDynamicRentPrice(int rentalPrice, List<Vehicle> vehicles) {
		int total = vehicles.size();
		int booked = 0;
		for (Vehicle vehicle : vehicles) {
			List<Boolean> availability = BookingRepository.getInstance().getVehicleAvailability(vehicle);
			for (boolean slotAvailable : availability) {
				if (!slotAvailable) {
					booked++;
					break;
				}
			}
		}
		if (total != 0 && (double) (booked) * 100 / (double) (total) >= DYNAMIC_PRICE_THREASHOLD) {
			rentalPrice = (int) (rentalPrice * (100 + DYNAMIC_PRICE_MULTIPLIER) / 100);
		}
		return rentalPrice;
	}

	public static void displayVehicles(String data) {
		String[] lineElements = data.split("\\s+");
		int i = 1;
		// Branch id, start time, end time
		String branchName = lineElements[i++];
		int startTime = Integer.parseInt(lineElements[i++]);
		int endTime = Integer.parseInt(lineElements[i++]);
		Branch branch = BranchRepository.getInstance().getBranchByBranchName(branchName);
		List<Vehicle> vehicles = BranchRepository.getInstance().getVehiclesOfBranch(branch);
		StringJoiner result = new StringJoiner(",");
		for (Vehicle vehicle : vehicles) {
			if (isAvailable(vehicle, startTime, endTime)) {
				result.add(vehicle.getVehicleId());
			}
		}
		System.out.println(result.toString());
	}

	public static boolean isAvailable(Vehicle vehicle, int startTime, int endTime) {
		List<Boolean> availability = BookingRepository.getInstance().getVehicleAvailability(vehicle);
		boolean isAvailable = true;
		for (int j = startTime; j <= endTime; j++) {
			isAvailable = isAvailable && availability.get(j);
		}
		return isAvailable;
	}
}
