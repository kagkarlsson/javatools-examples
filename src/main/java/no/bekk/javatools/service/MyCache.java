package no.bekk.javatools.service;

import org.apache.commons.lang3.RandomUtils;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MyCache {

	private static final Map<Long, Token> cache = new MaxSizeHashMap<>(1000);

	public Token get(long id) {
		synchronized (cache) {
			return cache.get(id);
		}
	}

	public void put(long id, Token value) {
		synchronized (cache) {
			cache.put(id, value);
			doSomethingElse();
		}
	}

	private void doSomethingElse() {
		try {
			Thread.sleep(RandomUtils.nextInt(10, 150));
		} catch (InterruptedException e) {
		}
	}
}
