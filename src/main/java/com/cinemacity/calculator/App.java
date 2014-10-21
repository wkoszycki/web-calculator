package com.cinemacity.calculator;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.LoggerFactory;

/**
 * Main application class responsible for starting jetty server.
 *
 * @author Wojciech Koszycki <wojciech.koszycki@coi.gov.pl>
 */
public class App {

    private static Server server;
    private static final String[] jettyConfigurationClasses
        = {
            "org.eclipse.jetty.webapp.WebInfConfiguration",
            "org.eclipse.jetty.webapp.WebXmlConfiguration",
            "org.eclipse.jetty.webapp.MetaInfConfiguration",
            "org.eclipse.jetty.webapp.FragmentConfiguration",
            "org.eclipse.jetty.plus.webapp.EnvConfiguration",
            "org.eclipse.jetty.webapp.JettyWebXmlConfiguration"
        };

    /**
     * Main program.
     *
     * @param args cli arguments
     */
    public static void main(String[] args) {
        //TODO: parse  arguments instead hardcoding
        int port = 8080;
        String contextPath = "/calculator-jetty";
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        initServer(port, contextPath);
    }

    /**
     *
     * @param port port on which Jetty will listen
     * @param contextPath path for application e.g /my-app
     */
    private static void initServer(int port, String contextPath) {
        try {
            server = new Server(port);
            HandlerList handlerList = new HandlerList();

            WebAppContext webapp = new WebAppContext();
            webapp.setConfigurationClasses(jettyConfigurationClasses);

            String webappPath = App.class.getClassLoader().getResource("webapp").toExternalForm();

            webapp.setDescriptor(webappPath + "/WEB-INF/web.xml");
            webapp.setContextPath(contextPath);
            webapp.setResourceBase(webappPath);
            webapp.setClassLoader(Thread.currentThread().getContextClassLoader());

            ServletContextHandler servletContextHandler;
            servletContextHandler = new ServletContextHandler(getServer(), "/", ServletContextHandler.SESSIONS);

            ResourceHandler resourceHandler = new ResourceHandler();
            resourceHandler.setResourceBase(webappPath);

            handlerList.addHandler(servletContextHandler);
            handlerList.addHandler(resourceHandler);
            handlerList.addHandler(webapp);
            getServer().setHandler(handlerList);
            getServer().start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the server
     */
    public static Server getServer() {
        return server;
    }
}
