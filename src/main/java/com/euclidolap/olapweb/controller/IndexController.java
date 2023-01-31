package com.euclidolap.olapweb.controller;

import com.euclidolap.olapweb.olap.OLAPAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private OLAPAdapter olapAdapter;

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("page/index");
        mv.addObject("wasAlreadyConnected", olapAdapter.getTerminal() != null ? "YES" : "NO");
        return mv;
    }

}
