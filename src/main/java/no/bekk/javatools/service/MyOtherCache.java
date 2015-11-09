package no.bekk.javatools.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MyOtherCache {

	private static final Map<Long, Token> cache = new ConcurrentHashMap<>();

	public Token get(long id) {
		return cache.get(id);
	}

	public void put(long id, Token value) {
		cache.put(id, value);
	}

}
