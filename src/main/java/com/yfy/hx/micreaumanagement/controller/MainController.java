package com.yfy.hx.micreaumanagement.controller;

import com.yfy.hx.micreaumanagement.randomizers.PhRandomizer;
import com.yfy.hx.micreaumanagement.randomizers.TemperatureRandomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private PhRandomizer phRand;
    private TemperatureRandomizer tRand;

    @Autowired
    public MainController(PhRandomizer phRand, TemperatureRandomizer tRand) {
        this.phRand = phRand;
        this.tRand = tRand;
    }

    private boolean nextPhSpike;
    private boolean nextTempSpike;

    @GetMapping("phTest")
    @ResponseBody
    public String phTest() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("normal ph: " + phRand.getNextPhNormal() + " ");
            sb.append("abn ph: " + phRand.getNextPhSpike() + "        ");
        }
        return sb.toString();
    }

    @GetMapping("tempTest")
    @ResponseBody
    public String tempTest() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("normal t: " + tRand.getNextTempNormal() + " ");
            sb.append("abn t: " + tRand.getNextTempSpike() + "        ");
        }
        return sb.toString();
    }

    // home mapping
    @GetMapping("home")
    public String homeView() {
        return "home";
    }

    // get the next ph
    @GetMapping("api/nextPh")
    @ResponseBody
    public String getNextPh() {
        String nextPh = genNextPh(nextPhSpike);
        log.info("generated next ph: {}", nextPh);
        return nextPh;
    }

    // get the next temp
    @GetMapping("api/nextTemp")
    @ResponseBody
    public String getNextTemp() {
        String nextTemp = genNextTemp(nextTempSpike);
        log.info("generated next temp: {}", nextTemp);
        return nextTemp;
    }

    // spike ph on next icon.png
    @PostMapping("api/spikePh")
    @ResponseBody
    public void spikePh() {
        log.info("spiking ph");
        nextPhSpike = true;
    }

    // spike temp on next icon.png
    @PostMapping("api/spikeTemp")
    @ResponseBody
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
