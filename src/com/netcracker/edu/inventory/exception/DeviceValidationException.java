package com.netcracker.edu.inventory.exception;

import com.netcracker.edu.inventory.model.Device;

public class DeviceValidationException extends RuntimeException {

    private static final String MESSAGE = "Device is not valid for operation";
    private Device device;

    public DeviceValidationException() {
        super();
    }

    public DeviceValidationException(final String operation) {
        super(getMsg() + (operation == null ? "" : " " + operation));
    }

    public DeviceValidationException(final String operation, Device device) {
        super(getMsg() + (operation == null ? "" : " " + operation));
        this.device = device;
    }

    private static String getMsg() {
        return MESSAGE;
    }

    public Device getDevice() {
        return device;
    }
}
