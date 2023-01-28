package com.euclidolap.olapweb.olap;

import com.euclidolap.sdk.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
//import java.util.HashMap;
//import java.util.Map;

@Component
public class OLAPAdapter {

    //@Value("${euclidolap.server.host:localhost}")
    //private String olapServerHost;
    //@Value("${euclidolap.server.port:8760}")
    //private int olapServerPort;

    @Value("${euclidolap.server:unknown}")
    private String euclidOlapServer;

    private Terminal terminal;

    //private Map<String, Terminal> terminalsPool = new HashMap<>();

    @PostConstruct
    private void initTer() {
        if ("unknown".equals(euclidOlapServer))
            return;

        String[] split = euclidOlapServer.split(":");
        String olapServerHost = split[0];
        int olapServerPort = Integer.parseInt(split[1]);

        terminal = new Terminal(olapServerHost, olapServerPort);
        try {
            terminal.connect();
        } catch (RuntimeException e) {
            terminal = null;
            e.printStackTrace();
        }
    }

    public Terminal getTerminal() {
        //if (terminalsPool.isEmpty())
        //    return null;
        //
        //Terminal terminal = null;
        //
        //for (Terminal t : terminalsPool.values()) {
        //    terminal = t;
        //}

        return terminal;
    }

    public Terminal createConnector(String serverHost, int port) {
        if (terminal != null)
            return terminal;

        euclidOlapServer = serverHost + ":" + port;

        initTer();
        return terminal;

        //Terminal t = terminalsPool.get(serverHost + ":" + port);
        //if (t == null) {
        //    t = new Terminal(serverHost, port);
        //    t.connect();
        //    terminalsPool.put(serverHost + ":" + port, t);
        //}
        //return t;
    }
}
