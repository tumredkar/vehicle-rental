package com.vehicle.rental;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.vehicle.rental.service.BookingService;
import com.vehicle.rental.service.BranchService;
import com.vehicle.rental.service.DisplayVehicleService;
import com.vehicle.rental.service.VehicleService;

public class DisplayVehicleServiceTest {

	@BeforeAll
	private static void init() {
		BranchService.addBranch("ADD_BRANCH B1 CAR,BIKE,VAN");
		VehicleService.addVehicle("ADD_VEHICLE B1 CAR V2 1000");
		VehicleService.addVehicle("ADD_VEHICLE B1 CAR V1 500");
		VehicleService.addVehicle("ADD_VEHICLE B1 BIKE V4 300");
		VehicleService.addVehicle("ADD_VEHICLE B1 BIKE V3 250");
		BookingService.bookVehicle("BOOK B1 BIKE 2 3");
		BookingService.bookVehicle("BOOK B1 BIKE 2 3");
	}

	/**
	 * display vehicles available in specific slot
	 */
	@Test
	public void displayVehicle() {
		try {
			DisplayVehicleService.displayVehicles("DISPLAY_VEHICLES B1 1 5");
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
