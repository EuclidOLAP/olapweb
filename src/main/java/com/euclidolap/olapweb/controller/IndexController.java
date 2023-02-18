package com.euclidolap.olapweb.controller;

import com.euclidolap.olapweb.olap.OLAPWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private OLAPWrapper olapWrapper;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("page/index");
        mv.addObject("wasAlreadyConnected", olapWrapper.getTerminal() != null ? "YES" : "NO");
        mv.addObject("servicesInfo", String.join(",", olapWrapper.getServicesInfo()));
        return mv;
    }

}
