package com.vehicle.rental.model;

/**
 * @author Tushar Umredkar
 *
 */
public enum VehicleType {
	CAR("CAR"), BIKE("BIKE"), VAN("VAN"), BUS("BUS"), UNKNOWN("UNKNOWN");

	private String type;

	VehicleType(String text) {
		this.type = text;
	}

	public String getText() {
		return this.type;
	}

	public static VehicleType get(String type) {
		for (VehicleType vehicleType : VehicleType.values()) {
			if (vehicleType.type.equalsIgnoreCase(type)) {
				return vehicleType;
			}
		}
		return UNKNOWN;
	}
}
