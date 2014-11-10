package no.bekk.javatools.service;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicInteger;

public class Filestore {
	private static final AtomicInteger COUNTER = new AtomicInteger(1);
	public static final byte[] BYTES = new byte[10_000_000];

	public void doSomeHeavyWritingToDisk() {
		try {
			Path tempFile = Files.createTempFile(String.valueOf(COUNTER.getAndIncrement()), ".tmp");

			for (int i = 0; i < 10; i++) {
				Files.write(tempFile, BYTES, StandardOpenOption.CREATE);
				Files.deleteIfExists(tempFile);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
