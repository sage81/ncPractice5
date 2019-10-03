package com.netcracker.edu.inventory.model.impl;

import com.netcracker.edu.inventory.model.Device;

import java.io.IOException;
import java.util.Date;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public abstract class AbstractDevice implements Device {

    private int in = 0;
    private String type;
    private String manufacturer;
    private String model;
    private Date productionDate;
    private Logger log = Logger.getLogger(AbstractDevice.class.getName());

    public AbstractDevice() {
        logReadConfig();
    }

    public AbstractDevice(int in, String type, String manufacturer, String model, Date productionDate) {
        this.in = in;
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.productionDate = productionDate;
        logReadConfig();
    }

    @Override
    public int getIn() {
        return this.in;
    }

    @Override
    public void setIn(int in) throws IllegalArgumentException {

        if (this.in == 0 && in > 0) {
            this.in = in;
        } else if (in < 0) {
            log.severe("IN(" + in + ") can not be negative");
            throw new IllegalArgumentException("IN can not be negative");
        } else if (this.in > 0) {
            log.warning("Can not reset existing Inventory number");
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public Date getProductionDate() {
        return this.productionDate;
    }

    @Override
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    private void logReadConfig() {
        try {
            LogManager.getLogManager().readConfiguration(
                    AbstractDevice.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + e.toString());
        }
    }

    @Override
    public String toString() {
        return "Device{" +
                "in=" + in +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", productionDate=" + productionDate +
                '}';
    }
}
