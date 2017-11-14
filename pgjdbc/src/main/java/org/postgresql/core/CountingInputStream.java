package org.postgresql.core;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
final class CountingInputStream extends FilterInputStream {

	private final AtomicLong counter;

	public CountingInputStream(InputStream in, AtomicLong counter) {
		super(in);
		this.counter = counter;
	}

	@Override
	public int read() throws IOException {
		int readBytes = super.read();

		if (readBytes != -1) {
			counter.addAndGet(readBytes);
		}

		return readBytes;
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		int readBytes = super.read(b, off, len);

		if (readBytes != -1) {
			counter.addAndGet(readBytes);
		}

		return readBytes;
	}
}
