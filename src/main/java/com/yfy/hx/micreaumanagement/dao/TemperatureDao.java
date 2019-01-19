package com.yfy.hx.micreaumanagement.dao;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class TemperatureDao {
    public static final int LOWER = 20;
    public static final int HIGHER = 30;

    private final Random r = new Random();
    private final DecimalFormat db = new DecimalFormat("#.#");

    public double getNextTempNormal() {
        double d = (LOWER + (HIGHER - LOWER) * r.nextDouble());
        return Double.parseDouble(db.format(d));
    }

    public double getNextTempSpike() {
        double d = Double.parseDouble(db.format(r.nextDouble() * 14));
        if (d >= LOWER && d <= HIGHER) {
            return getNextTempNormal();
        }
        return d;
    }
}
