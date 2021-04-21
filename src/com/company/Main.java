package com.company;
import com.company.constants.VehicleType;
import com.company.model.Carpark;
import com.company.model.CarparkLots;
import com.company.model.Lot;
import com.company.model.VehicleLog;
import com.company.util.CarparkLotsServices;
import com.company.util.FileServices;
import com.company.util.ParkingRateServices;
import com.company.util.VehicleLogServices;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


public class Main {

    public static void main(String[] args) {

        String inputFile = "input.txt";

        List<String[]> fileContents = FileServices.getFileContents(inputFile);

        // we know that the first line is the no. of slots present.
        String[] lots = fileContents.get(0);
        int carLots = Integer.parseInt(lots[0]);
        int motorcycleLots  = Integer.parseInt(lots[1]);

        // now init that carpark and set the variables.
        Carpark carpark = new Carpark();
        carpark.setCarparkLots(new HashSet<>());
        carpark.setVehicleLogs(new HashSet<>());

        CarparkLots carparkLotsCar = new CarparkLots();
        carparkLotsCar.setCapacity(carLots);
        carparkLotsCar.setRate(BigDecimal.valueOf(2));
        carparkLotsCar.setVehicleType(VehicleType.car);

        CarparkLots carparkLotsMotorcycle = new CarparkLots();
        carparkLotsMotorcycle.setCapacity(motorcycleLots);
        carparkLotsMotorcycle.setRate(BigDecimal.valueOf(1));
        carparkLotsMotorcycle.setVehicleType(VehicleType.motorcycle);

        carpark.getCarparkLots().add(carparkLotsMotorcycle);
        carpark.getCarparkLots().add(carparkLotsCar);

        for(String[] stringArray: fileContents.subList(1, fileContents.size())) {
            // in format Enter <motorcycle|car> <vehicle number> <timestamp>
            if (stringArray[0].toLowerCase().contentEquals("enter")) {
                String vehicleTypeString = stringArray[1];
                String vehicleNumber = stringArray[2];
                Date date = new Date(new Timestamp(Long.parseLong(stringArray[3])).getTime());
                VehicleType vehicleType = VehicleType.valueOf(vehicleTypeString);
                VehicleLogServices.log(carpark, vehicleNumber, vehicleType, date);
                Optional<CarparkLots> relevantCarparkLots = carpark.getCarparkLotsByType(vehicleType);
                if (relevantCarparkLots.isEmpty()) {
                    System.out.println("Unexpected vehicle type: " + vehicleType.toString());
                    continue;
                }
                CarparkLots carparkLots = relevantCarparkLots.get();
                // once i've found the carpark lots, can proceed to check in.
                try {
                    Lot lot = CarparkLotsServices.checkin(carparkLots, vehicleNumber, vehicleType);
                    System.out.println("Accept " + lot.toString());
                } catch (Exception e){
                    // cant find a lot then reject
                    System.out.println("Reject");
                }


            } else if (stringArray[0].toLowerCase().contentEquals("exit")) {
                //format:  Exit <vehicle number> <timestamp>
                String vehicleNumber = stringArray[1];
                Date exitDate = new Date(new Timestamp(Long.parseLong(stringArray[2])).getTime());
                // first lets find what kind of vehicle this is from our vehicle log.
                Optional<VehicleLog> vehicleLogOpt = carpark.findVehicleFromVehicleLogs(vehicleNumber);
                if (vehicleLogOpt.isEmpty()) {
                    //invalid entry!
                    System.out.println("Invalid vehicle number provided, this vehicle number is not in this carpark");
                    continue;
                }
                VehicleLog vehicleLog = vehicleLogOpt.get();
                VehicleType vehicleType = vehicleLog.getVehicleType();
                // now check it out from its relevant carpark
                Optional<CarparkLots> relevantCarparkLots = carpark.getCarparkLotsByType(vehicleType);
                if (relevantCarparkLots.isEmpty()) {
                    System.out.println("Unexpected vehicle type: " + vehicleType.toString());
                    continue;
                }
                CarparkLots carparkLots = relevantCarparkLots.get();
                try {
                    BigDecimal charges = ParkingRateServices.calculateParkingCharges(vehicleLog.getDate(), exitDate, carparkLots.getRate());
                    Lot lot = CarparkLotsServices.checkout(carparkLots, vehicleNumber);

                    System.out.println(lot.toString() + " " + charges.toString());

                } catch (Exception e) {
                    System.out.print("Something went wrong with checking out this vehicle");
                }
                VehicleLogServices.logout(carpark, vehicleLog);
            } else {
                System.out.println("Unexpected line in input file: " + String.join(" ", stringArray));
            }
        }
    }
}
