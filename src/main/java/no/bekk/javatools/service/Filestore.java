package no.bekk.javatools.service;

import org.apache.commons.lang3.RandomUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.atomic.AtomicInteger;

public class Filestore {
	private static final AtomicInteger COUNTER = new AtomicInteger(1);

	public void doSomeHeavyWritingToDisk() {
		try {
			Path tempFile = Files.createTempFile(String.valueOf(COUNTER.getAndIncrement()), ".tmp");

			long start = System.currentTimeMillis();
			int randomBytesLength = 1000;
			long writtenBytes = 0;
			while (durationFrom(start) < 1000 && writtenBytes < 100_000_000) {
				Files.write(tempFile, RandomUtils.nextBytes(randomBytesLength), StandardOpenOption.APPEND);
				writtenBytes += randomBytesLength;
			}
			System.out.println("wrote " + writtenBytes + " bytes");
			Files.deleteIfExists(tempFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private long durationFrom(long millis) {
		return System.currentTimeMillis() - millis;
	}
}
