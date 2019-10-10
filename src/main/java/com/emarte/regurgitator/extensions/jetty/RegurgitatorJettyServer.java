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
        if(args.length < 3 || args.length == 4 || args.length > 5) {
            System.err.println("Usage: RegurgitatorJettyServer port config-location config-context-path [global-properties-location, global-context-path]");
        }

        Server server = new Server();

        ServletContextHandler context = new ServletContextHandler(null, "/", SESSIONS);
        server.setHandler(context);

        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessions = new SessionHandler(manager);
        context.setSessionHandler(sessions);

        loadServlet(context, RegurgitatorServlet.class, "config-location", args[1], args[2]);

        if(args.length == 5) {
            loadServlet(context, GlobalMetadataServlet.class, "global-location", args[3], args[4]);
        }

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(parseInt(args[0]));

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