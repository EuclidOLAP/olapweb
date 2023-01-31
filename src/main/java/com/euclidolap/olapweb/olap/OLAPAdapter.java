package com.euclidolap.olapweb.olap;

import com.euclidolap.sdk.Terminal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class OLAPAdapter {

    @Value("${euclidolap.initTryConnCount:3}")
    private int initTryConnCount;

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

        boolean successful = false;

        for (int i = 0; i < initTryConnCount; i++) {
            try {
                terminal.connect();
                successful = true;
                break;
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!successful)
            terminal = null;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Terminal createConnector(String serverHost, int port) {
        if (terminal != null)
            return terminal;

        euclidOlapServer = serverHost + ":" + port;

        initTer();
        return terminal;
    }
}
