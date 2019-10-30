package com.netcracker.edu.inventory.model.impl;

import com.netcracker.edu.inventory.exception.DeviceValidationException;
import com.netcracker.edu.inventory.logger.Log;
import com.netcracker.edu.inventory.model.Device;
import com.netcracker.edu.inventory.model.Rack;
import com.netcracker.edu.inventory.service.Service;
import com.netcracker.edu.inventory.service.impl.ServiceImpl;

import java.util.logging.Logger;

public class RackArrayImpl implements Rack {

    private final int size;
    private Device[] devices;
    private static final Logger log = Log.getLogger(RackArrayImpl.class.getName());
    private final Class clazz;

    public RackArrayImpl(final int size, Class clazz) {
        Log.initLogger();

        if (!isClassValid(clazz) || isSizeNotValid(size)) {
            logAndThrowIllegalArgument("Type cannot be null or not Device");
        }
            this.size = size;
            this.clazz = clazz;
            devices = new Device[this.size];
    }

    public RackArrayImpl(final int size) {
        Log.initLogger();
        this.clazz = Device.class;

        if (isSizeNotValid(size)) {
            logAndThrowIllegalArgument("Size of rack can not be 0 or less");
        }
        this.size = size;
        devices = new Device[this.size];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getFreeSize() {

        int freeSize = 0;

        for (Device dev : devices) {
            freeSize = (dev == null) ? freeSize + 1 : freeSize;
        }
        return freeSize;
    }

    @Override
    public Device getDevAtSlot(int index) {
        Device device = null;
        if (isSlotExist(index)) {
            device = devices[index];
        } else {
            logAndThrowIndexOutOfBounds(index);
        }
        return device;
    }

    @Override
    public boolean insertDevToSlot(Device device, int index) {
        Service service = new ServiceImpl();
        boolean isInserted = false;

        if (!isSlotExist(index)) {
            logAndThrowIndexOutOfBounds(index);
        } else if (!service.isValidDeviceForInsertToRack(device)) {
            throw new DeviceValidationException("Rack.insertDevToSlot", device);
        } else if (!isDeviceTypeCompatible(device)) {
            logAndThrowIllegalArgument("Type of Device is't compatible with type that rack can store");
        }

        if (isSlotAvailable(index)) {
            devices[index] = device;
            isInserted = (devices[index] != null);
        } else {
            log.warning("Slot" + index + " is not available");
        }

        return isInserted;
    }

    @Override
    public Device removeDevFromSlot(int index) {

        Device device = null;

        if (isSlotExist(index)) {
            if (!isSlotAvailable(index)) {
                device = devices[index];
                devices[index] = null;
            } else {
                log.warning("Can not remove from empty slot " + index);
            }
        } else {
            logAndThrowIndexOutOfBounds(index);
        }

        return device;
    }

    @Override
    public Device getDevByIN(int in) {
        Device returenedDevice = null;
        for (Device device : devices) {
            if (device != null && device.getIn() == in) {
                returenedDevice =  device;
                break;
            }
        }
        return returenedDevice;
    }

    @Override
    public Class getTypeOfDevices() {
        return clazz;
    }

    @Override
    public Device[] getAllDeviceAsArray() {
        Device[] filedDevices = new Device[getNonEmptyAmount(devices)];
        int i = 0;

        for (Device device : devices) {
            if (device != null) {
                filedDevices[i++] = device;
            }
        }
        return filedDevices;
    }

    private boolean isSizeNotValid(final int size) {
        return size <= 0;
    }

    private boolean isClassValid(final Class clazz) {
        return clazz != null && Device.class.isAssignableFrom(clazz);
    }

    private boolean isRackEmpty() {
        return devices.length == 0;
    }

    private boolean isSlotExist(final int index) {
        return !isRackEmpty() && index >= 0 && index <= getSize() - 1;
    }

    private boolean isSlotEmpty(final int index) {
        return isSlotExist(index) && devices[index] == null;
    }

    private boolean isSlotAvailable(final int index) {
        return isSlotEmpty(index);
    }

    private void logAndThrowIndexOutOfBounds(final int index) {
        final String errMsg ="Wrong slot index["
                + index + "], should be in the range:0-" + (getSize() - 1);
        log.severe(errMsg);
        throw new IndexOutOfBoundsException(errMsg);
    }

    private void logAndThrowIllegalArgument(final String errorMessage) {
        log.severe(errorMessage);
        throw new IllegalArgumentException(errorMessage);
    }

    private boolean isDeviceTypeCompatible(final Device device) {
        return clazz.isAssignableFrom(device.getClass());
    }

    private int getNonEmptyAmount(final Device[] devices) {
        int counter = 0;
        for (Device device : devices) {
            if (device != null) counter++;
        }
        return counter;
    }

}
