package com.netcracker.edu.inventory;

import com.netcracker.edu.inventory.model.Device;
import com.netcracker.edu.inventory.model.Rack;
import com.netcracker.edu.inventory.model.impl.Battery;
import com.netcracker.edu.inventory.model.impl.Router;
import com.netcracker.edu.inventory.service.impl.ServiceImpl;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

class Utl {
    static Date addHoursToJavaUtilDate(Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    static void testObject(Object o) {
        if (o.getClass() == Integer.class) {
            System.out.println("Integer");
        } else {
            System.out.println("Other");
        }
    }

    static void showRack(Rack rack) {
        for (int i = 0; i < rack.getSize(); i++) {
            if (rack.getDevAtSlot(i) != null) {
                System.out.println("slot"+ i + " | " + rack.getDevAtSlot(i));
            } else {
                System.out.println("slot"+ i + " | empty" );
            }
        }
    }

    static void fillRack(Device[] devices, Rack rack) {
        for (int i = 0; i < devices.length; i++) {
            rack.insertDevToSlot(devices[i], i);
        }
    }

    static void showDevArray(Device[] devices) {
        for (Device device : devices) {
//            if (device != null) {
                System.out.println(device);
//            }
        }
    }

    static boolean test(Class clazz) {
        return clazz != null && Router.class.isAssignableFrom(clazz);
    }

    static void filterMethods() {

        Device[] devices = new Device[15];
        ServiceImpl service = new ServiceImpl();

        devices[0] = null;
        devices[1] = new Battery(34, "bat", "magnum", "model",
                Utl.addHoursToJavaUtilDate(new Date(), -48), 100);
        devices[2] = null;
        devices[3] = new Router();
        devices[4] = new Battery(7753, "bat1", "google", "model1",
                Utl.addHoursToJavaUtilDate(new Date(), -24), 101);
        devices[5] = new Battery(118, "bat2", "magnum", "model",
                new Date(), 100);
        devices[6] = null;
        devices[7] = new Router();
        devices[8] = new Router();
        devices[9] = new Battery(1, "bat1", "robotic", "model1",
                Utl.addHoursToJavaUtilDate(new Date(), 48), 101);
        devices[10] = new Battery(118, "bat", "magnum", "model",
                new Date(), 100);
        devices[11] = null;
        devices[12] = null;
        devices[13] = new Router();
        devices[14] = new Battery(115, "bat2", "technoCorp", "model1",
                Utl.addHoursToJavaUtilDate(new Date(), -124), 101);

//        devices[0] = new Battery(34, "bat", "magnum", "model",
//                Utl.addHoursToJavaUtilDate(new Date(), -48), 100);
//        devices[1] = new Router(35, "router", "qwerty", "modelRouter1",
//                Utl.addHoursToJavaUtilDate(new Date(), -23), 456);

        service.filtrateByModel(devices, null);
        Arrays.stream(devices).forEach(System.out::println);
    }
}
