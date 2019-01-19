package com.yfy.hx.micreaumanagement.controller;

import com.yfy.hx.micreaumanagement.dao.PhDao;
import com.yfy.hx.micreaumanagement.dao.TemperatureDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainApiController {
    private static final Logger log = LoggerFactory.getLogger(MainApiController.class);

    private PhDao phRand;
    private TemperatureDao tRand;

    @Autowired
    public MainApiController(PhDao phRand, TemperatureDao tRand) {
        this.phRand = phRand;
        this.tRand = tRand;
    }

    private boolean nextPhSpike;
    private boolean nextTempSpike;

    // get the next ph
    @GetMapping("api/nextPh")
    public String getNextPh() {
        String nextPh = genNextPh(nextPhSpike);
        log.info("generated next ph: {}", nextPh);
        return nextPh;
    }

    // get the next temp
    @GetMapping("api/nextTemp")
    public String getNextTemp() {
        String nextTemp = genNextTemp(nextTempSpike);
        log.info("generated next temp: {}", nextTemp);
        return nextTemp;
    }

    // spike ph on next icon.png
    @PostMapping("api/spikePh")
    public void spikePh() {
        log.info("spiking ph");
        nextPhSpike = true;
    }

    // spike temp on next icon.png
    @PostMapping("api/spikeTemp")
    public void spikeTemp() {
        log.info("spiking temp");
        nextTempSpike = true;
    }

    private String genNextPh(boolean abnormal) {
        try {
            return "" + (abnormal ? phRand.getNextPhSpike() : phRand.getNextPhNormal());
        } finally {
            nextPhSpike = false;
        }
    }

    private String genNextTemp(boolean abnormal) {
        try {
            return "" + (abnormal ? tRand.getNextTempSpike() : tRand.getNextTempNormal());
        } finally {
            nextTempSpike = false;
        }
    }


}
