package com.yfy.hx.micreaumanagement.randomizers;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Random;

@Component
public class PhRandomizer {
    private static final double lowerBound = 6.5;
    private static final double higherBound = 7.5;

    private final Random r = new Random();
    private final DecimalFormat db = new DecimalFormat("#.#");

    public double getNextPhNormal() {
        double d = (lowerBound + (higherBound - lowerBound) * r.nextDouble());
        return Double.parseDouble(db.format(d));
    }

    public double getNextPhSpike() {
        double d = Double.parseDouble(db.format(r.nextDouble() * 14));
        if (d >= lowerBound && d <= higherBound) {
            return getNextPhSpike();
        }
        return d;
    }

}
