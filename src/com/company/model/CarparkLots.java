package com.company.model;

import com.company.constants.VehicleType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarparkLots {

    private VehicleType vehicleType;
    private BigDecimal rate;
    private int capacity;
    private int reserved;
    private List<Lot> lots;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
        // everytime capacity is set, generate the lots
        this.lots =new ArrayList<>();
        for (int i = 0; i < capacity; i ++) {
            this.lots.add(new Lot(i));
        }
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }


    public List<Lot> getLots() {
        return lots;
    }

    public void setLots(List<Lot> lots) {
        this.lots = lots;
    }


}
