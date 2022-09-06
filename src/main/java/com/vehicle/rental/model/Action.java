package com.vehicle.rental.model;

/**
 * @author Tushar Umredkar
 *
 */
public enum Action {
	ADD_BRANCH("ADD_BRANCH"), ADD_VEHICLE("ADD_VEHICLE"), BOOK("BOOK"), DISPLAY_VEHICLES("DISPLAY_VEHICLES"),
	UNKNOWN("UNKNOWN");

	private String action;

	Action(String action) {
		this.action = action;
	}

	public String getText() {
		return this.action;
	}

	public static Action get(String type) {
		for (Action Action : Action.values()) {
			if (Action.action.equalsIgnoreCase(type)) {
				return Action;
			}
		}
		return UNKNOWN;
	}
}
