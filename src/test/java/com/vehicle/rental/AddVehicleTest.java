package com.vehicle.rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.vehicle.rental.service.BranchService;
import com.vehicle.rental.service.VehicleService;

public class AddVehicleTest {

	@BeforeAll
	private static void init() {
		BranchService.addBranch("ADD_BRANCH B1 CAR,BIKE,VAN");
	}

	/**
	 * test if vehicle can be added successfully for existing branch
	 */
	@Test
	public void addVehicle1() {
		try {
			assertEquals(Boolean.toString(true).toUpperCase(), VehicleService.addVehicle("ADD_VEHICLE B1 CAR V1 500"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test if vehicle can be added successfully for non - existing branch
	 */
	@Test
	public void addVehicle2() {
		try {
			assertEquals(Boolean.toString(false).toUpperCase(), VehicleService.addVehicle("ADD_VEHICLE B2 CAR V1 500"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test if duplicate vehicle can be added successfully for existing branch
	 */
	@Test
	public void addVehicle3() {
		try {
			assertEquals(Boolean.toString(false).toUpperCase(), VehicleService.addVehicle("ADD_VEHICLE B1 CAR V1 500"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test if vehicle of non available vehicleType can be added successfully for
	 * existing branch
	 */
	@Test
	public void addVehicle4() {
		try {
			assertEquals(Boolean.toString(false).toUpperCase(), VehicleService	.addVehicle("ADD_VEHICLE B1 BUS V1 500"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
