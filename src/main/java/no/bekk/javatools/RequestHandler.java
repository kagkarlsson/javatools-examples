package no.bekk.javatools;

import no.bekk.javatools.service.Filestore;
import no.bekk.javatools.service.MyCache;
import no.bekk.javatools.service.MyOtherCache;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class RequestHandler extends AbstractHandler {
	private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

	private Filestore filestore = new Filestore();
	private MyCache myCache = new MyCache();
	private MyOtherCache myOtherCache = new MyOtherCache();

	@Override
	public void handle(String target, Request baseRequest,
					   HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();

		if (target.equals("/slow")) {
			filestore.doSomeHeavyWritingToDisk();
			writer.print("Done slowly!");

		} else if (target.equals("/slowing")) {
			myCache.update(randomAlphabetic(40));

		} else if (target.equals("/caching")) {
			myOtherCache.update(randomAlphabetic(40));
			sleep(10);
		}

		response.flushBuffer();
	}

	private void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
		}
	}
}
