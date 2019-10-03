package com.netcracker.edu.inventory.model.impl;

import java.util.Date;

public class Router extends AbstractDevice {

    private int dataRate;

    public Router() {
    }

    public Router(int in, String type, String manufacturer, String model, Date productionDate, int dataRate) {
        super(in, type, manufacturer, model, productionDate);
        this.dataRate = dataRate;
    }

    public int getDataRate() {
        return dataRate;
    }

    public void setDataRate(int dataRate) {
        this.dataRate = dataRate;
    }

    @Override
    public String toString() {
        return super.toString() + " Router{" +
                "dataRate=" + dataRate +
                '}';
    }
}
