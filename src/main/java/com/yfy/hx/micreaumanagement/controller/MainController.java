package com.yfy.hx.micreaumanagement.controller;

import com.yfy.hx.micreaumanagement.randomizers.PhRandomizer;
import com.yfy.hx.micreaumanagement.randomizers.TemperatureRandomizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("ph")
    @ResponseBody
    public String getPh() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("normal ph: " + phRand.getNextPhNormal() + " ");
            sb.append("abn ph: " + phRand.getNextPhSpike() + "        ");
        }
        return sb.toString();
    }

    @GetMapping("temp")
    @ResponseBody
    public String getTemp() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("normal t: " + tRand.getNextTempNormal() + " ");
            sb.append("abn t: " + tRand.getNextTempSpike() + "        ");
        }
        return sb.toString();
    }
}
