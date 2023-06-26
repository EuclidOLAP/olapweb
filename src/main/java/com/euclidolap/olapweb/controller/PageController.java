package com.euclidolap.olapweb.controller;

import com.euclidolap.olapweb.olap.OLAPWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.Mapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private OLAPWrapper olapWrapper;

    @RequestMapping("/workbench")
    public String workbench(@RequestParam String endpoint) {

        olapWrapper.setCurrentTerminal(endpoint);

        System.out.println("<<< endpoint = " + endpoint + " >>>");
        return "page/workbench";
    }
}
