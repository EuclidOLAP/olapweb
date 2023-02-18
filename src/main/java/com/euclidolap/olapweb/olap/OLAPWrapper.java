package com.euclidolap.olapweb.olap;

import com.euclidolap.sdk.Terminal;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OLAPWrapper {

    private static final File euclidolapServicesInfoFile = new File("euclidolapServicesInfo.txt");

    private Map<String, Terminal> terminalMap = new HashMap<>();

    @Value("${euclidolap.predefinedServices:nothing}")
    private String predefinedServices;

    @Value("${euclidolap.initTryConnCount:3}")
    private int initTryConnCount;

    @Value("${euclidolap.server:unknown}")
    private String euclidOlapServer;

    private Terminal terminal;

    @PostConstruct
    private void initialize() throws IOException {
        if ("nothing".equals(predefinedServices))
            return;

        List<String> lines = euclidolapServicesInfoFile.exists() ? Files.readLines(euclidolapServicesInfoFile, StandardCharsets.UTF_8) : new ArrayList<>();
        String[] preSvcList = predefinedServices.split(",");

        for (String preSvc : preSvcList) {
            if (lines.contains(preSvc))
                continue;
            addConnectorInfo(preSvc);
        }
    }

    //@PostConstruct
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

    public Terminal getTerminal(String endpoint) {
        // return terminal;
        return terminalMap.get(endpoint);
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Terminal createConnector(String serverHost, int port) {
        Terminal terminal = new Terminal(serverHost, port);
        terminal.connect();
        terminalMap.put(serverHost + ":" + port, terminal);
        return terminal;

        //if (terminal != null)
        //    return terminal;
        //
        //euclidOlapServer = serverHost + ":" + port;
        //
        //initTer();
        //return terminal;
    }

    public List<String> getServicesInfo() {
        try {
            return euclidolapServicesInfoFile.exists() ? Files.readLines(euclidolapServicesInfoFile, StandardCharsets.UTF_8) : new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void addConnectorInfo(String connInfo) {
        try {
            Files.asCharSink(euclidolapServicesInfoFile, StandardCharsets.UTF_8, FileWriteMode.APPEND).write(connInfo + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
