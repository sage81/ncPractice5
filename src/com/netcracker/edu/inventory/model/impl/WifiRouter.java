package com.netcracker.edu.inventory.model.impl;

import java.util.Date;

public class WifiRouter extends Router {

    private String securityProtocol;

    public WifiRouter() {
    }

    public WifiRouter(int in, String type, String manufacturer, String model, Date productionDate, int dataRate, String securityProtocol) {
        super(in, type, manufacturer, model, productionDate, dataRate);
        this.securityProtocol = securityProtocol;
    }

    public String getSecurityProtocol() {
        return securityProtocol;
    }

    public void setSecurityProtocol(String securityProtocol) {
        this.securityProtocol = securityProtocol;
    }

    @Override
    public String toString() {
        return super.toString() + " WifiRouter{" +
                "securityProtocol='" + securityProtocol + '\'' +
                '}';
    }
}
