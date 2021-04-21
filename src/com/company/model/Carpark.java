package com.company.model;

import com.company.constants.VehicleType;

import java.util.Optional;
import java.util.Set;

public class Carpark {

    private String location;
    private String name;
    private String id;

    private Set<CarparkLots> carparkLots;

    // probably need another container to track the full vehicle list that is current in the carpark.
    private Set<VehicleLog> vehicleLogs;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<CarparkLots> getCarparkLots() {
        return carparkLots;
    }

    public void setCarparkLots(Set<CarparkLots> carparkLots) {
        this.carparkLots = carparkLots;
    }

    public Set<VehicleLog> getVehicleLogs() {
        return vehicleLogs;
    }

    public void setVehicleLogs(Set<VehicleLog> vehicleLogs) {
        this.vehicleLogs = vehicleLogs;
    }

    public Optional<CarparkLots> getCarparkLotsByType (VehicleType vehicleType) {
        return this.getCarparkLots().stream().filter(carparkLot -> carparkLot.getVehicleType().equals(vehicleType)).findAny();
    }
    public Optional<VehicleLog> findVehicleFromVehicleLogs(String vehicleNumber) {
        return this.getVehicleLogs().stream().filter(vehicleLog -> vehicleLog.getVehicleNumber().contentEquals(vehicleNumber)).findAny();
    }
}
