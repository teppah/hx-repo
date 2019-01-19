package com.yfy.hx.micreaumanagement.controller;

import com.yfy.hx.micreaumanagement.dao.DataDao;
import com.yfy.hx.micreaumanagement.dataaccess.PhDataAccessor;
import com.yfy.hx.micreaumanagement.dataaccess.TemperatureDataAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
public class MainApiController {
    private static final Logger log = LoggerFactory.getLogger(MainApiController.class);

    private PhDataAccessor phRand;
    private TemperatureDataAccessor tRand;
    private DataDao dao;

    private boolean nextPhSpike;
    private boolean nextTempSpike;

    @Autowired
    public MainApiController(PhDataAccessor phRand, TemperatureDataAccessor tRand,
                             DataDao dao) {
        this.phRand = phRand;
        this.tRand = tRand;
        this.dao = dao;
    }

    @GetMapping("getPastPh")
    public String[] getPastPh() {
        return dao.getPastPhData().toArray(new String[0]);
    }

    @GetMapping("getPastTemp")
    public String[] getPastTemp() {
        return dao.getPastPhData().toArray(new String[0]);
    }



    // get the next ph
    @GetMapping("nextPh")
    public String getNextPh() {
        String nextPh = genNextPh(nextPhSpike);
        log.info("generated next ph: {}", nextPh);
        dao.savePhData(nextPh);
        return nextPh;
    }

    // get the next temp
    @GetMapping("nextTemp")
    public String getNextTemp() {
        String nextTemp = genNextTemp(nextTempSpike);
        log.info("generated next temp: {}", nextTemp);
        dao.saveTempData(nextTemp);
        return nextTemp;
    }

    // spike ph on next icon.png
    @PostMapping("spikePh")
    public void spikePh() {
        log.info("spiking ph");
        nextPhSpike = true;
    }

    // spike temp on next icon.png
    @PostMapping("spikeTemp")
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
