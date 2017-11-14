package org.postgresql.core;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
final class CountingOutputStream extends FilterOutputStream {

	private final AtomicLong counter;

	public CountingOutputStream(OutputStream out, AtomicLong counter) {
		super(out);
		this.counter = counter;
	}

	@Override
	public void write(int b) throws IOException {
		super.write(b);
		counter.addAndGet(1);
	}
}
