package no.bekk.javatools;

import org.eclipse.jetty.server.Server;

public class WebappMain {

	public static void main(String[] args) throws Exception {
		Server server = new Server(8787);
		server.setHandler(new RequestHandler());
		server.start();
		server.join();
	}


}
