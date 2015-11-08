package no.bekk.javatools.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class MyOtherCache {

	private static final Map<Integer, String> cache = new HashMap<>();
	private AtomicInteger counter = new AtomicInteger(1);

	public void update(String s) {
		for (int i = 0; i < 1_000; i++) {
			cache.put(counter.getAndIncrement(), s);
		}
	}

}
