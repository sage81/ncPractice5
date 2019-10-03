package com.netcracker.edu.inventory;

import com.netcracker.edu.inventory.model.Device;
import com.netcracker.edu.inventory.model.Rack;
import com.netcracker.edu.inventory.model.impl.Battery;
import com.netcracker.edu.inventory.model.impl.RackArrayImpl;

public class Main {

    public static void main(String[] args) {

        Battery battery = new Battery(1,"bat", "man", "model", null, 45);

        Rack rack = new RackArrayImpl(10);
        rack.insertDevToSlot(battery, 0);

        System.out.println(rack.removeDevFromSlot(0));
        showRack(rack);

    }

    private static void showRack(Rack rack) {
        for (int i = 0; i < rack.getSize(); i++) {
            if (rack.getDevAtSlot(i) != null) {
                System.out.println("slot"+ i + " | " + rack.getDevAtSlot(i));
            } else {
                System.out.println("slot"+ i + " | empty" );
            }
        }
    }

    private static void fillRack(Device[] devices, Rack rack) {
        for (int i = 0; i < devices.length; i++) {
            rack.insertDevToSlot(devices[i], i);
        }
    }

    private static void showDevArray(Device[] devices) {
        for (Device device : devices) {
            if (device != null) {
                System.out.println(device);
            }
        }
    }
}
