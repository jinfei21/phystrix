package com.guard.phystrix.infoboard;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JettyServer {

	private static final int port = 3000;

	private Server server;

	private volatile boolean running = false;
	
	
	private static JettyServer instance;
	
	
	public static JettyServer instance(){
		if(instance == null){
			synchronized (JettyServer.class) {
				if(instance == null){
					instance = new JettyServer();
				}
			}
			
		}
		return instance;
	}
	
	

	private JettyServer() {
		this.server = new Server(port);

		try {
			ServletContextHandler handler = new ServletContextHandler();
			handler.setContextPath("/");
			handler.setSessionHandler(new SessionHandler());
			handler.addServlet(PropertiesServlet.class, "/api/props");
            handler.addServlet(StaticContentServlet.class, "/*");
            handler.addServlet(HystrixMetricsStreamServlet.class, "/hystrix.stream");
            server.setHandler(handler);
		} catch (Exception e) {
			log.error("Exception in building AdminResourcesContainer ", e);
		}
	}
	


	public void start() {

		if (!running) {
			try {
				server.start();
				running = true;
			} catch (Exception e) {
				log.error("Exception in Starting " + this.getClass().getSimpleName(), e);
			}
		}
	}

	public void shutdown() {
		if (running) {
			try {
				server.stop();
				running = false;
			} catch (Exception e) {
				log.error("Exception in Stopping " + this.getClass().getSimpleName(), e);
				if (!server.isStarted() && !server.isStarting()){
					running = false;
				}
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

}
