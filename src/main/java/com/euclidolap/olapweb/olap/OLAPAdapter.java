package com.euclidolap.olapweb.olap;

import com.euclidolap.sdk.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OLAPAdapter {

    @Value("${euclidolap.server.host:localhost}")
    private String olapServerHost;
    @Value("${euclidolap.server.port:8760}")
    private int olapServerPort;

    private Terminal terminal;

    @PostConstruct
    private void initTer() {
        terminal = new Terminal(olapServerHost, olapServerPort);
        terminal.connect();
    }

    public Terminal getTerminal() {
        return terminal;
    }
}
