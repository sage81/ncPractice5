package com.netcracker.edu.inventory.model.impl;

import java.util.Date;

public class Switch extends Router {

    private int numberOfPorts;

    public Switch() {
    }

    public Switch(int in, String type, String manufacturer, String model, Date productionDate, int dataRate, int numberOfPorts) {
        super(in, type, manufacturer, model, productionDate, dataRate);
        this.numberOfPorts = numberOfPorts;
    }

    public int getNumberOfPorts() {
        return numberOfPorts;
    }

    public void setNumberOfPorts(int numberOfPorts) {
        this.numberOfPorts = numberOfPorts;
    }

    @Override
    public String toString() {
        return super.toString() + " Switch{" +
                "numberOfPorts=" + numberOfPorts +
                '}';
    }
}
