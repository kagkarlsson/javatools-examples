package no.bekk.javatools.service;

import org.apache.commons.lang3.RandomUtils;

import java.util.Map;

public class MyCache {

	private static final Map<String, String> cache = new MaxSizeHashMap<>(1000);

	public void update(String key, String value) {
		synchronized (cache) {
			cache.put(key, value);
			doSomethingElse();
		}

	}

	private void doSomethingElse() {
		try {
			Thread.sleep(RandomUtils.nextInt(50, 150));
		} catch (InterruptedException e) {
		}
	}

}
