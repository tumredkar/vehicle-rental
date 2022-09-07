package com.vehicle.rental.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vehicle.rental.model.Vehicle;

/**
 * @author Tushar Umredkar
 *
 */
public class BookingRepository {

	private Map<Vehicle, List<Boolean>> vehicleAvailabilityMap;
	public static BookingRepository instance;
	public static final Integer SLOTS_PER_DAY = 24;

	private BookingRepository() {
		vehicleAvailabilityMap = new HashMap<>();
	}

	public static synchronized BookingRepository getInstance() {
		if (instance == null) {
			instance = new BookingRepository();
		}
		return instance;
	}

	/**
	 * add vehicle to vehicleAvailabilityMap to keep track of vehicles available
	 * slots
	 * 
	 * @param vehicle
	 */
	public void addVehicle(Vehicle vehicle) {
		vehicleAvailabilityMap.putIfAbsent(vehicle, new ArrayList<>());
		for (int i = 0; i < SLOTS_PER_DAY; i++) {
			vehicleAvailabilityMap.get(vehicle).add(true);
		}
	}

	/**
	 * get availability data of vehicle
	 * 
	 * @param vehicle
	 * @return
	 */
	public List<Boolean> getVehicleAvailability(Vehicle vehicle) {
		return vehicleAvailabilityMap.get(vehicle);
	}

}
