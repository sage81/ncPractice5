package com.netcracker.edu.inventory.service.impl;

import com.netcracker.edu.inventory.model.Device;
import com.netcracker.edu.inventory.service.Service;

public class ServiceImpl implements Service {
    @Override
    public void sortByIN(Device[] devices) {
        for (int i = 0; i < devices.length - 1; i++) {
            for (int j = i + 1; j < devices.length; j++) {
                if (isSwapNeeded(devices[i], devices[j])) {
                    swap(devices, i, j);
                }
            }
        }
    }

    private boolean isSwapNeeded(Device dev1, Device dev2) {
        boolean result = false;

        if (dev1 != null && dev2 != null) {
            if (dev1.getIn() > dev2.getIn() && dev2.getIn() != 0) {
                result = true;
            } else if (dev1.getIn() == 0 && dev2.getIn() != 0) {
                result = true;
            }
        } else if (dev1 == null && dev2 != null) {
            result = true;
        }
        return result;
    }

    private static void swap(Device[] inp, int position1, int position2) {
        Device first = inp[position1];

        inp[position1] = inp[position2];
        inp[position2] = first;
    }

    @Override
    public void filtrateByType(Device[] devices, String type) {
        for (int i = 0; i < devices.length; i++) {
            if (devices[i] != null) {
                if (devices[i].getType() != null) {
                    if (!devices[i].getType().equals(type)) {
                        devices[i] = null;
                    }
                } else if (type != null) {
                    if (!type.equals(devices[i].getType())) {
                        devices[i] = null;
                    }
                }
            }
        }
    }
}
