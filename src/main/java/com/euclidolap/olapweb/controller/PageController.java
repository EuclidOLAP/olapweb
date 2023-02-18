package com.euclidolap.olapweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
public class PageController {

    @PostMapping("/workbench")
    public String workbench(@RequestParam String endpoint) {
        System.out.println("<<< endpoint = " + endpoint + " >>>");
        return "page/workbench";
    }
}
