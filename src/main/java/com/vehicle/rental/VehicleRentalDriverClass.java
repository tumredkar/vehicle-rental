package com.vehicle.rental;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.vehicle.rental.model.Action;
import com.vehicle.rental.service.BookingService;
import com.vehicle.rental.service.BranchService;
import com.vehicle.rental.service.DisplayService;
import com.vehicle.rental.service.VehicleService;

/**
 * @author Tushar Umredkar
 *
 */
public class VehicleRentalDriverClass {

	public static void main(String[] args) {
		String filePath = args[0];
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));) {
			// Parse the file and call your code
			// Print the output
			String line = null;
			while ((line = reader.readLine()) != null) {
				try {
					String[] lineElements = line.split("\\s+");
					Action action = Action.get(lineElements[0]);
					switch (action) {
					case ADD_BRANCH:
						System.out.println(BranchService.addBranch(line));
						break;
					case ADD_VEHICLE:
						System.out.println(VehicleService.addVehicle(line));
						break;
					case BOOK:
						System.out.println(BookingService.bookVehicle(line));
						break;
					case DISPLAY_VEHICLES:
						DisplayService.displayVehicles(line);
						break;
					default:
						System.out.println("Invalid action");
						break;
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
