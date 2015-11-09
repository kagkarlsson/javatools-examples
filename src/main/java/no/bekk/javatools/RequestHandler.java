package no.bekk.javatools;

import no.bekk.javatools.service.*;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestHandler extends AbstractHandler {
	private static final Logger LOG = LoggerFactory.getLogger(RequestHandler.class);

	private SlowTokenStore slowTokenStore = new SlowTokenStore();
	private FastTokenStore fastTokenStore = new FastTokenStore();

	private MyCache myCache = new MyCache();
	private MyOtherCache myOtherCache = new MyOtherCache();

	@Override
	public void handle(String target, Request baseRequest,
					   HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/plain");

		long id = Long.parseLong(request.getParameter("id"));

		if (target.contains("/get_token1")) {
			Token value = slowTokenStore.get(id);
			//.doSomeHeavyWritingToDisk()
			response.getWriter().write(value.get());


		} else if (target.contains("/get_token2")) {
			Token value = myCache.get(id);
			if (value == null) {
				value = fastTokenStore.get(id);
				myCache.put(id, value);
			}
			response.getWriter().write(value.get());


		} else if (target.contains("/get_token3")) {
			Token value = myOtherCache.get(id);
			if (value == null) {
				value = fastTokenStore.get(id);
				myOtherCache.put(id, value);
			}
			response.getWriter().write(value.get());


		} else {
			throw new IllegalStateException("unknown target " + target);
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
