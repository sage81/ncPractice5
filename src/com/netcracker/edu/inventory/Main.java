package com.netcracker.edu.inventory;

import com.netcracker.edu.inventory.model.Device;
import com.netcracker.edu.inventory.model.Rack;
import com.netcracker.edu.inventory.model.impl.*;

public class Main {

    public static void main(String[] args) {
        Rack rack = new RackArrayImpl(10);
        Device[] devices = new Device[rack.getSize()];

        Battery battery = new Battery();
        battery.setIn(1);

        Router router = new Router();
        router.setIn(2);

        Switch sw = new Switch();
        sw.setIn(3);

        WifiRouter wifiRouter = new WifiRouter();
        wifiRouter.setIn(4);

        devices[0] = battery;
        devices[1] = router;
        devices[2] = sw;
        devices[3] = wifiRouter;

        rack.insertDevToSlot(devices[0],0);
        rack.insertDevToSlot(devices[1],1);
        rack.insertDevToSlot(devices[2],2);
        rack.insertDevToSlot(devices[3],3);

        Utl.showRack(rack);

        Utl.showDevArray(rack.getAllDeviceAsArray());
    }

}
