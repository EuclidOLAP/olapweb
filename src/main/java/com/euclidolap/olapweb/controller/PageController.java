package com.euclidolap.olapweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/index")
    public String index() {
        return "page/index";
    }

    @PostMapping("/workbench")
    public String workbench() {
        return "page/workbench";
    }
}
