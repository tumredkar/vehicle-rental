package com.vehicle.rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.vehicle.rental.service.BranchService;

public class AddBranchTest {

	/**
	 * test if branch with valid vehicleType can be created
	 */
	@Test
	public void addBranchTest1() {
		try {
			assertEquals(Boolean.toString(true).toUpperCase(), BranchService.addBranch("ADD_BRANCH B1 CAR,BIKE,VAN"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test if branch with invalid vehicleType can be created
	 */
	@Test
	public void addBranchTest2() {
		try {
			assertEquals(Boolean.toString(false).toUpperCase(), BranchService.addBranch("ADD_BRANCH B2 CAR1,BIKE,VAN"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * test if existing branch can be created again
	 */
	@Test
	public void addBranchTest3() {
		try {
			assertEquals(Boolean.toString(false).toUpperCase(), BranchService.addBranch("ADD_BRANCH B1 CAR,BIKE,VAN"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
