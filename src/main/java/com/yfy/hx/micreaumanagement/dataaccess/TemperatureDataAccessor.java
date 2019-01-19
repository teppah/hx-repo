package com.yfy.hx.micreaumanagement.dataaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class TemperatureDataAccessor {
    private static final Logger log = LoggerFactory.getLogger(TemperatureDataAccessor.class);
    public static final int LOWER = 20;
    public static final int HIGHER = 30;

    private final Random r = new Random();
    private final DecimalFormat db = new DecimalFormat("#.#");

    public double getNextTempNormal() {
        double d = (LOWER + (HIGHER - LOWER) * r.nextDouble());
        return Double.parseDouble(db.format(d));
    }

    public double getNextTempSpike() {
        double d = Double.parseDouble(db.format(r.nextDouble() * 40));
        log.info("spike temp:" + d);
        if (d >= LOWER && d <= HIGHER) {
            return getNextTempNormal();
        }
        return d;
    }
}
