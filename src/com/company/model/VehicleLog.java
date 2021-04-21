package com.company.model;

import com.company.constants.VehicleType;

import java.sql.Timestamp;
import java.util.Date;

public class VehicleLog {

    private String vehicleNumber;
    private VehicleType vehicleType;

    private Date date;

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
