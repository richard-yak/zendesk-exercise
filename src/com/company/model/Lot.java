package com.company.model;

public class Lot {

    private int position;
    private Vehicle vehicle;

    public Lot(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public String toString() {
        return vehicle != null ? vehicle.getVehicleType().toString()+"Lot"+(position + 1) : "Empty Lot" + (position+1);
    }
}
