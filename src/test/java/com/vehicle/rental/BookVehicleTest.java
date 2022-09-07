package com.vehicle.rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.vehicle.rental.service.BookingService;
import com.vehicle.rental.service.BranchService;
import com.vehicle.rental.service.VehicleService;

class BookVehicleTest {

	@BeforeAll
	private static void init() {
		BranchService.addBranch("ADD_BRANCH B1 CAR,BIKE,VAN");
		VehicleService.addVehicle("ADD_VEHICLE B1 CAR V2 1000");
		VehicleService.addVehicle("ADD_VEHICLE B1 CAR V1 500");
		VehicleService.addVehicle("ADD_VEHICLE B1 BIKE V4 300");
		VehicleService.addVehicle("ADD_VEHICLE B1 BIKE V3 250");
		VehicleService.addVehicle("ADD_VEHICLE B1 VAN V5 2000");
	}

	/**
	 * book cheapest bike from list of vehicles
	 */
	@Test
	public void bookVehicle1() {
		try {
			assertEquals(250, BookingService.bookVehicle("BOOK B1 BIKE 2 3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * book bike for same slot as previous list of vehicles next bike will be booked
	 */
	@Test
	public void bookVehicle2() {
		try {
			assertEquals(300, BookingService.bookVehicle("BOOK B1 BIKE 2 3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * book bike for same slot as previous list of vehicles error will occur
	 */
	@Test
	public void bookVehicle3() {
		try {
			assertEquals(-1, BookingService.bookVehicle("BOOK B1 BIKE 2 3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * book unknown vehicle list of vehicles next bike will be booked
	 */
	@Test
	public void bookVehicle4() {
		try {
			assertEquals(-1, BookingService.bookVehicle("BOOK B1 BUS 2 3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * dynamic rental price test as 80% vehicles were booked new rental price for
	 * vehicles is increased by 10%
	 */
	@Test
	public void bookVehicle5() {
		try {
			BookingService.bookVehicle("BOOK B1 CAR 1 5");
			BookingService.bookVehicle("BOOK B1 CAR 4 8");
			assertEquals(2200, BookingService.bookVehicle("BOOK B1 VAN 2 3"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
