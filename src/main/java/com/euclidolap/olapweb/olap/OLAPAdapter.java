package com.euclidolap.olapweb.olap;

import com.euclidolap.sdk.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OLAPAdapter {

    //@Value("${euclidolap.server.host:localhost}")
    //private String olapServerHost;
    //@Value("${euclidolap.server.port:8760}")
    //private int olapServerPort;

    //private Terminal terminal;

    private Map<String, Terminal> terminalsPool = new HashMap<>();

    //@PostConstruct
    //private void initTer() {
    //    terminal = new Terminal(olapServerHost, olapServerPort);
    //    terminal.connect();
    //}

    public Terminal getTerminal() {
        if (terminalsPool.isEmpty())
            return null;

        Terminal terminal = null;

        for (Terminal t : terminalsPool.values()) {
            terminal = t;
        }

        return terminal;
    }

    public Terminal createConnector(String serverHost, int port) {
        Terminal t = terminalsPool.get(serverHost + ":" + port);
        if (t == null) {
            t = new Terminal(serverHost, port);
            t.connect();
            terminalsPool.put(serverHost + ":" + port, t);
        }
        return t;
    }
}
