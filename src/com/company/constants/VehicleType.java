package com.company.constants;

public enum VehicleType {

    motorcycle("Motorcycle"),
    car("Car");

    private final String text;

    VehicleType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
