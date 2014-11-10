package no.bekk.javatools;

import org.apache.commons.lang3.RandomUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpClientMain {

	public static void main(String[] args) {
		if (args.length != 2) throw new IllegalArgumentException("Mangler argument. Skal vara [url] [threads]");
		CloseableHttpClient httpclient = HttpClientBuilder.create().setMaxConnPerRoute(100).setMaxConnTotal(100).build();

		startRequests(Integer.parseInt(args[1]), httpclient, args[0]);
	}

	private static void startRequests(int nrParallell, final CloseableHttpClient httpclient, final String uri) {
		for (int i = 0; i < nrParallell; i++) {

			new Thread() {
				@Override
				public void run() {
					while(true) {
						try {
							long start = System.currentTimeMillis();
							CloseableHttpResponse response = httpclient.execute(new HttpHost("localhost", 8787), new HttpGet(uri));
							System.out.println(uri + " took " + (System.currentTimeMillis() - start) + "s");
							response.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}
}
