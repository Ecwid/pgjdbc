package org.postgresql.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Vasily Vasilkov (vgv@ecwid.com)
 */
final class CountingInputStream extends InputStream {

  private final InputStream peer;
	private final AtomicLong counter;

	public CountingInputStream(InputStream in, AtomicLong counter) {
		this.peer = in;
		this.counter = counter;
	}

  @Override
  public int read() throws IOException {
    int readByte = peer.read();

    if (readByte != -1) {
      counter.incrementAndGet();
    }

    return readByte;
  }

  @Override
  public int read(byte[] b) throws IOException {
    int readBytes = peer.read(b);

    if (readBytes != -1) {
      counter.addAndGet(readBytes);
    }

    return readBytes;
  }

  @Override
  public int read(byte[] b, int off, int len) throws IOException {
    int readBytes = peer.read(b, off, len);

    if (readBytes != -1) {
      counter.addAndGet(readBytes);
    }

    return readBytes;
  }

  @Override
  public long skip(long n) throws IOException {
    return peer.skip(n);
  }

  @Override
  public int available() throws IOException {
    return peer.available();
  }

  @Override
  public void close() throws IOException {
    peer.close();
  }

  @Override
  public void mark(int readlimit) {
    peer.mark(readlimit);
  }

  @Override
  public void reset() throws IOException {
    peer.reset();
  }

  @Override
  public boolean markSupported() {
    return peer.markSupported();
  }

}
