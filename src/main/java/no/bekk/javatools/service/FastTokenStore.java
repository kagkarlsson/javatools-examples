package no.bekk.javatools.service;

import org.apache.commons.lang3.RandomStringUtils;

public class FastTokenStore {

	public Token get(long id) {
		return new Token(RandomStringUtils.randomAlphanumeric(10000));
	}
}
