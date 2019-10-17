package org.postgresql.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
final class CountingOutputStream extends OutputStream {

  private final OutputStream peer;
	private final AtomicLong counter;

	public CountingOutputStream(OutputStream out, AtomicLong counter) {
		this.peer = out;
		this.counter = counter;
	}

  @Override
  public void write(int b) throws IOException {
    peer.write(b);
    counter.incrementAndGet();
  }

  @Override
  public void write(byte[] b) throws IOException {
    peer.write(b);
    counter.addAndGet(b.length);
  }

  @Override
  public void write(byte[] b, int off, int len) throws IOException {
    peer.write(b, off, len);
    counter.addAndGet(len);
  }

  @Override
  public void flush() throws IOException {
    peer.flush();
  }

  @Override
  public void close() throws IOException {
    peer.close();
  }
}
