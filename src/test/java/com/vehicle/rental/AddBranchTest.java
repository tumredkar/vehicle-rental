package com.vehicle.rental;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.vehicle.rental.common.MyConstants;
import com.vehicle.rental.service.BookingService;

public class AddBranchTest {

	/**
	 * test if branch with valid vehicleType can be created
	 */
	@Test
	public void addBranchTest1() {
		try {
			assertEquals(MyConstants.TRUE, BookingService.addBranch("ADD_BRANCH B1 CAR,BIKE,VAN"));
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
			assertEquals(MyConstants.FALSE, BookingService.addBranch("ADD_BRANCH B2 CAR1,BIKE,VAN"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
