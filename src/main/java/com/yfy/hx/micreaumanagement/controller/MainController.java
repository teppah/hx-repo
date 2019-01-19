package com.yfy.hx.micreaumanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);


    // home mapping
    @GetMapping("home")
    public String homeView() {
        log.info("rendering home");
        return "home";
    }


}
