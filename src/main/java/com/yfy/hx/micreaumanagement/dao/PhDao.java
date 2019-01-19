package com.yfy.hx.micreaumanagement.dao;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class PhDao {
    public static final double LOWER = 6.5;
    public static final double HIGHER = 7.5;

    private final Random r = new Random();
    private final DecimalFormat db = new DecimalFormat("#.#");

    public double getNextPhNormal() {
        double d = (LOWER + (HIGHER - LOWER) * r.nextDouble());
        return Double.parseDouble(db.format(d));
    }

    public double getNextPhSpike() {
        double d = Double.parseDouble(db.format(r.nextDouble() * 14));
        if (d >= LOWER && d <= HIGHER) {
            return getNextPhSpike();
        }
        return d;
    }

}
