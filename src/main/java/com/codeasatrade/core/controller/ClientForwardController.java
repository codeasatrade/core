package com.codeasatrade.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientForwardController {

    //    https://github.com/wazooinc/spring-boot-packaging-with-react/blob/main/src/main/java/com/wazooinc/springbootwithreact/controllers/ClientForwardController.java
    @GetMapping(value = "/**/{path:[^\\.]*}")
    public String forward() {
        return "forward:/";
    }

}
