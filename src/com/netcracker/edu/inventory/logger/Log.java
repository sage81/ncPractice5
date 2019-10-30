package com.netcracker.edu.inventory.logger;

import com.netcracker.edu.inventory.model.impl.RackArrayImpl;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Log {
    private static Logger logger;

    private static void setLogger(final Logger logger) {
        Log.logger = logger;
    }

    public static Logger getLogger(final String loggerName) {
        setLogger(Logger.getLogger(loggerName));
        return logger;
    }

    public static void initLogger() {
        try {
            LogManager.getLogManager().readConfiguration(
                    RackArrayImpl.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
