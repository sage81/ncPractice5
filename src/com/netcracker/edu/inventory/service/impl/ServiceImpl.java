package com.netcracker.edu.inventory.service.impl;

import com.netcracker.edu.inventory.logger.Log;
import com.netcracker.edu.inventory.model.Device;
import com.netcracker.edu.inventory.service.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Logger;

public class ServiceImpl implements Service {
    public ServiceImpl() {
        Log.initLogger();
    }

    private static final Logger log = Log.getLogger(Service.class.getName());

    private final Comparator<Device> inComparator = new Comparator<Device>() {
        @Override
        public int compare(final Device device1, final Device device2) {
            Integer comparatorResult = compareToNull(device1, device2);
            if (comparatorResult != null) return comparatorResult;
            if (device2.getIn() == 0) return -1;
            if (device1.getIn() == 0) return 1;
            return device1.getIn() - device2.getIn();
        }
    };

    @Override
    public void sortByIN(Device[] devices) {
        Arrays.sort(devices, inComparator);
    }

    private final Comparator<Device> productionDateComparator = new Comparator<Device>() {
        @Override
        public int compare(final Device device1, final Device device2) {
            Integer comparatorResult = compareToNull(device1, device2);
            if (comparatorResult != null) return comparatorResult;

            comparatorResult = compareToNull(device1.getProductionDate(), device2.getProductionDate());
            if (comparatorResult != null) return comparatorResult;

            return device1.getProductionDate().compareTo(device2.getProductionDate());
        }
    };

    @Override
    public void sortByProductionDate(Device[] devices) {
        Arrays.sort(devices, productionDateComparator);
    }

    private Integer compareToNull(final Object device1, final Object device2) {
        if (device2 == null) return -1;
        if (device1 == null) return 1;
        return null;
    }

    @Override
    public void filtrateByType(Device[] devices, final String type) {
        filterBy(devices, "getType", type);
    }

    @Override
    public void filtrateByManufacturer(Device[] devices, final String manufacturer) {
        filterBy(devices, "getManufacturer", manufacturer);
    }

    @Override
    public void filtrateByModel(Device[] devices, final String model) {
        filterBy(devices, "getModel", model);
    }

    @Override
    public boolean isValidDeviceForInsertToRack(final Device device) {
        return device != null && device.getIn() > 0;
    }

    private void filterBy(Device[] devices, final String methodName, final String comparableValue) {
        Method method;
        String value = null;
        for (int i = 0; i < devices.length; i++) {
            if (devices[i] != null) {
                try {
                    method = devices[i].getClass().getMethod(methodName);
                    value = (String) method.invoke(devices[i]);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    log.severe("Bed method or Invocation exception");
                    e.printStackTrace();
                }
                if (value != null) {
                    if (!value.equals(comparableValue)) {
                        devices[i] = null;
                    }
                } else if (comparableValue != null) {
                    devices[i] = null;
                }
            }
        }
    }

}
