package com.company.util;

import com.company.constants.VehicleType;
import com.company.model.CarparkLots;
import com.company.model.Lot;
import com.company.model.Vehicle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CarparkLotsServices {

    public static Lot checkin(CarparkLots carparkLots, String vehicleNumber, VehicleType vehicleType) throws Exception {
        List<Lot> lots = carparkLots.getLots();
        Lot foundLot = null;
        // find the first empty lot.
        for (Lot lot : lots) {
            if (lot.getVehicle() == null) {
                // only check in if there is an empty lot.
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleNumber(vehicleNumber);
                vehicle.setVehicleType(vehicleType);
                lot.setVehicle(vehicle);
                foundLot = lot;
                break;
            }
        }
        if (foundLot == null) {
            throw new Exception("LOT NOT FOUND");
        }
        return foundLot;
    }

    public static Lot checkout(CarparkLots carparkLots, String vehicleNumber) throws Exception {
        List<Lot> lots = carparkLots.getLots();
        Lot foundLot = null;
        for (Lot lot: lots) {
            if (lot.getVehicle() != null && lot.getVehicle().getVehicleNumber().contentEquals(vehicleNumber)) {
                foundLot = new Lot(lot.getPosition());
                foundLot.setVehicle(lot.getVehicle());
                lot.setVehicle(null);
            }
        }

        if (foundLot == null) {
            throw new Exception("LOT NOT FOUND");
        }
        return foundLot;

    }


}
