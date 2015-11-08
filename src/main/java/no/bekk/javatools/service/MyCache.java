package no.bekk.javatools.service;

import org.apache.commons.lang3.RandomUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyCache {

	private static final Map<Integer, String> cache = new MaxSizeHashMap<>(1000);
	private AtomicInteger counter = new AtomicInteger(1);

	public void update(String value) {
		synchronized (cache) {
			cache.put(counter.incrementAndGet(), value);
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
