package com.euclidolap.olapweb.controller;

import com.euclidolap.olapweb.olap.OLAPAdapter;
import com.euclidolap.sdk.MultiDimResult;
import com.euclidolap.sdk.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private OLAPAdapter olapAdapter;

    @PostMapping("/execMdx")
    public Object execMdx(@RequestBody Map<String, Object> param) {

        Terminal terminal = olapAdapter.getTerminal();
        MultiDimResult result = (MultiDimResult) terminal.exec((String) param.get("mdx"));
        result.show(System.out);

        Map<String, String> dto = new HashMap<>();
        dto.put("status", "successful");
        return dto;
    }
}