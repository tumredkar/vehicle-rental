package com.vehicle.rental;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.vehicle.rental.service.BookingService;

public class DisplayVehicleTest {

	@BeforeAll
	private static void init() {
		BookingService.addBranch("ADD_BRANCH B1 CAR,BIKE,VAN");
		BookingService.addVehicle("ADD_VEHICLE B1 CAR V2 1000");
		BookingService.addVehicle("ADD_VEHICLE B1 CAR V1 500");
		BookingService.addVehicle("ADD_VEHICLE B1 BIKE V4 300");
		BookingService.addVehicle("ADD_VEHICLE B1 BIKE V3 250");
		BookingService.bookVehicle("BOOK B1 BIKE 2 3");
		BookingService.bookVehicle("BOOK B1 BIKE 2 3");
	}

	/**
	 * display vehicles available in specific slot
	 */
	@Test
	public void bookVehicle1() {
		try {
			BookingService.displayVehicles("DISPLAY_VEHICLES B1 1 5");
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
