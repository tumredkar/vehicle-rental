package com.vehicle.rental.service;

import java.util.List;

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
			if (isVehicleAvailableForBooking(vehicle, startTime, endTime)) {
				int rentalPrice = getDynamicRentPrice(vehicle.getRentalPrice(),
						VehicleService.getAllVehicleOfBranch(branch));
				for (int j = startTime; j <= endTime; j++) {
					availability.add(j, false);
				}
				return (endTime - startTime) * rentalPrice;
			}
		}
		return -1;
	}

	/**
	 * get dynamic rental price based on vehicle booking status
	 * 
	 * @param rentalPrice
	 * @param vehicles
	 * @return
	 */
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

	/**
	 * check if vehicle is available for mentioned slot
	 * 
	 * @param vehicle
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static boolean isVehicleAvailableForBooking(Vehicle vehicle, int startTime, int endTime) {
		List<Boolean> availability = BookingRepository.getInstance().getVehicleAvailability(vehicle);
		boolean isAvailable = true;
		for (int j = startTime; j <= endTime; j++) {
			isAvailable = isAvailable && availability.get(j);
		}
		return isAvailable;
	}
}
