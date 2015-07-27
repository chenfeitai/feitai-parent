package com.feitai.common.id;

import java.io.PrintStream;
import java.net.InetAddress;

public class UUIDFactory implements IdFactory {
	private String sep = "-";
	private static final int IP;
	private static final int JVM;
	private static short counter;

	static {
		int ipadd = 0;
		try {
			byte[] ips = InetAddress.getLocalHost().getAddress();
			for (int i = 0; i < 4; ++i) {
				ipadd = (ipadd << 8) - -128 + ips[i];
			}
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;

		JVM = (int) (System.currentTimeMillis() >>> 8);
		counter = 0;
	}

	public Object generate() {
		return 36 + format(IP) + this.sep + format(JVM) + this.sep
				+ format((short) (int) (System.currentTimeMillis() >>> 32))
				+ this.sep + format((int) System.currentTimeMillis())
				+ this.sep + format(count()).toUpperCase();
	}

	protected String format(int intval) {
		String formatted = Integer.toHexString(intval);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8 - formatted.length(), 8, formatted);
		return buf.toString();
	}

	protected String format(short shortval) {
		String formatted = Integer.toHexString(shortval);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4 - formatted.length(), 4, formatted);
		return buf.toString();
	}

	private short count() {
		synchronized (UUIDFactory.class) {
			if (counter < 0)
				counter = 0;
			short tmp48_45 = counter;
			counter = (short) (tmp48_45 + 1);
			return tmp48_45;
		}
	}

	public static void main(String[] args) {
		UUIDFactory a = new UUIDFactory();

		System.err.println(a.generate());
		System.err.println(a.generate());
		System.err.println(a.generate());
		System.err.println(a.generate());

		SessionIdFactory b = new SessionIdFactory();

		System.err.println(b.generate());
		System.err.println(b.generate());
		System.err.println(b.generate());
		System.err.println(b.generate());
		System.err.println((long) Math.pow(10.0D, 9.0D) - 1L);
	}
}