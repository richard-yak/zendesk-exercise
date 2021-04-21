package com.company.util;

import com.company.constants.VehicleType;
import com.company.model.Carpark;
import com.company.model.VehicleLog;
import java.util.Date;
import java.util.Optional;

public class VehicleLogServices {

    public static void log(Carpark carpark, String vehicleNumber, VehicleType vehicleType, Date date) {

        Optional<VehicleLog> existingLogOpt = carpark.getVehicleLogs().stream().filter(vehicleLog -> vehicleLog.getVehicleNumber().contentEquals(vehicleNumber)).findAny();
        if (existingLogOpt.isPresent()) {
            System.out.println("Possible double entry, log already exists.");
        }

        VehicleLog vehicleLog = new VehicleLog();
        vehicleLog.setVehicleNumber(vehicleNumber);
        vehicleLog.setVehicleType(vehicleType);
        vehicleLog.setDate(date);
        carpark.getVehicleLogs().add(vehicleLog);
    }

    public static void logout(Carpark carpark, VehicleLog vehicleLog) {
        carpark.getVehicleLogs().remove(vehicleLog);
    }
}
