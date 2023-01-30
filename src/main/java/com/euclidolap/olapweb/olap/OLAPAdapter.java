package com.euclidolap.olapweb.olap;

import com.euclidolap.sdk.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OLAPAdapter {

    @Value("${euclidolap.server:unknown}")
    private String euclidOlapServer;

    private Terminal terminal;

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
        return terminal;
    }

    public Terminal createConnector(String serverHost, int port) {
        if (terminal != null)
            return terminal;

        euclidOlapServer = serverHost + ":" + port;

        initTer();
        return terminal;
    }
}
