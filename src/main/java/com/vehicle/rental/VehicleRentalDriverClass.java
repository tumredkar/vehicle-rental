package com.vehicle.rental;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.vehicle.rental.model.Action;
import com.vehicle.rental.service.BookingService;

/**
 * @author Tushar Umredkar
 *
 */
public class VehicleRentalDriverClass {

	public static void main(String[] args) {
		String filePath = args[0];
//		String filePath = "D:\\test-workspace\\vehicle-rental\\src\\main\\resources\\input.txt";
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(filePath)));) {
			// Parse the file and call your code
			// Print the output
			String line = null;
			while ((line = reader.readLine()) != null) {
				try {
//					System.out.println();
//					System.out.println(line);
					String[] lineElements = line.split("\\s+");
					Action action = Action.get(lineElements[0]);
					switch (action) {
					case ADD_BRANCH:
						System.out.println(BookingService.getInstance().addBranch(line));
						break;
					case ADD_VEHICLE:
						System.out.println(BookingService.getInstance().addVehicle(line));
						break;
					case BOOK:
						System.out.println(BookingService.getInstance().bookVehicle(line));
						break;
					case DISPLAY_VEHICLES:
						BookingService.getInstance().displayVehicles(line);
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
