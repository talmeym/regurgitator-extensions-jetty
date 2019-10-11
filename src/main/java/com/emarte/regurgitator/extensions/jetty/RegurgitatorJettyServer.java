package com.emarte.regurgitator.extensions.jetty;

import com.emarte.regurgitator.extensions.web.GlobalMetadataServlet;
import com.emarte.regurgitator.extensions.web.RegurgitatorServlet;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

import static java.lang.Integer.parseInt;
import static org.eclipse.jetty.servlet.ServletContextHandler.SESSIONS;

public class RegurgitatorJettyServer {
    public static void main(String[] args) throws Exception {
        if (args.length < 3 || args.length == 4 || args.length > 5) {
            System.err.println("Usage: RegurgitatorJettyServer port config-location config-context-path [global-properties-location, global-context-path]");
            System.exit(1);
        }

        runRegurgitatorJettyServer(parseInt(args[0]), args[1], args[2], args.length > 3 ? args[3] : null, args.length > 4 ? args[4] : null);
    }

    private static void runRegurgitatorJettyServer(int port, String configLocation, String configContextPath, String globalLocation, String globalContextPath) throws Exception {
        Server server = new Server();

        ServletContextHandler context = new ServletContextHandler(null, "/", SESSIONS);
        server.setHandler(context);

        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        context.setSessionHandler(sessions);

        loadServlet(context, RegurgitatorServlet.class, "config-location", configLocation, configContextPath);

        if(globalLocation != null && globalContextPath != null) {
            loadServlet(context, GlobalMetadataServlet.class, "global-location", globalLocation, globalContextPath);
        }

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);

        server.setConnectors(new Connector[]{connector});

        server.start();
        server.join();
    }

    private static void loadServlet(ServletContextHandler handler, Class<? extends Servlet> servlet, String initParamName, String InitParamValue, String contextPath) {
        ServletHolder regurgServletHolder = new ServletHolder(servlet);
        regurgServletHolder.setInitParameter(initParamName, InitParamValue);
        handler.addServlet(regurgServletHolder, contextPath);
    }
}