package com.euclidolap.olapweb.controller;

import com.euclidolap.olapweb.olap.OLAPWrapper;
import com.euclidolap.sdk.MultiDimResult;
import com.euclidolap.sdk.Terminal;
import com.google.gson.Gson;
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
    private OLAPWrapper olapWrapper;

    @PostMapping("/execMdx")
    public Object execMdx(@RequestBody Map<String, Object> param) {

        Terminal terminal = olapWrapper.getTerminal();
        MultiDimResult result = (MultiDimResult) terminal.exec((String) param.get("mdx"));

        //ByteArrayOutputStream bytesArr = new ByteArrayOutputStream();
        //result.show(bytesArr);

        Map<String, String> dto = new HashMap<>();
        dto.put("status", "successful");
        //dto.put("result", bytesArr.toString());
        dto.put("result", new Gson().toJson(result));
        return dto;
    }

    @PostMapping("/connect")
    public String connect(@RequestBody Map<String, String> param) {

        String svrHostPort = param.get("svrHostPort");
        String serverHost = svrHostPort.split(":")[0];
        int port = Integer.parseInt(svrHostPort.split(":")[1]);

        Terminal terminal = new Terminal(serverHost, port);
        try {
            terminal.connect();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "failure";
        }

        Terminal t = olapWrapper.createConnector(serverHost, port);
        if (t != null) {
            olapWrapper.getTerminal().close();
        }
        olapWrapper.setTerminal(terminal);

        return "successful";
    }

    @RequestMapping("/wasConnectorExisted")
    public String wasConnectorExisted() {
        return olapWrapper.getTerminal() == null ? "NO" : "YES";
    }

}