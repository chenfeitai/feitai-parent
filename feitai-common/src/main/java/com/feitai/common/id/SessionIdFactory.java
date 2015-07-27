package com.feitai.common.id;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class SessionIdFactory implements IdFactory {
	private static Random _randomNumberGenerator;
	private static int counter = 0;
	private static long lastTimeVal = 0L;
	protected static final int DIGIT_BASE = 36;
	protected static final long MAX_RANDOM_LEN = 2176782336L;
	protected static final long MAX_SESSION_LIFESPAN_TICS = 46656L;
	protected static final long TIC_DIFFERENCE = 2000L;

	static {
		try {
			_randomNumberGenerator = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			_randomNumberGenerator = new SecureRandom();
		}
	}

	public Object generate() {
		long n = _randomNumberGenerator.nextLong();
		if (n < 0L) {
			n = -n;
		}
		StringBuffer id = new StringBuffer();

		n %= 2176782336L;
		n += 2176782336L;
		id.append(Long.toString(n, 36).substring(1).toUpperCase());

		long timeVal = System.currentTimeMillis() / 2000L;
		timeVal %= 46656L;
		timeVal += 46656L;
		id.append(Long.toString(timeVal, 36).substring(1).toUpperCase());

		synchronized (SessionIdFactory.class) {
			if (lastTimeVal != timeVal) {
				lastTimeVal = timeVal;
				counter = 0;
			}
			counter += 1;
		}

		id.append(Long.toString(counter, 36));

		return id.toString();
	}
}