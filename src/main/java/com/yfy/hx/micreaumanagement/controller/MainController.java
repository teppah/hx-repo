package com.yfy.hx.micreaumanagement.controller;

import com.yfy.hx.micreaumanagement.randomizers.PhRandomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    @Autowired
    private PhRandomizer rand;

    @GetMapping("/")
    @ResponseBody
    public String getRand() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append("normal value: " + rand.getNextPhNormal() + " ");
            sb.append("abn value: " + rand.getNextPhSpike() + "        ");
        }
        return sb.toString();
    }
}
