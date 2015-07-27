package com.feitai.common.id;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IdFormat {
	private String pattern;
	private String datePattern;
	private Format[] format;

	public static IdFormat getInstance(String pattern) {
		return new IdFormat(pattern);
	}

	public String getPattern() {
		return this.pattern;
	}

	public String getDatePattern() {
		return this.datePattern;
	}

	private IdFormat(String pattern) {
		this.pattern = pattern;
		compile();
	}

	public String format(IdFactory idf, long id, Date d) {
		StringBuffer buf = new StringBuffer(32);
		for (int i = 0; i < this.format.length; ++i) {
			Format f = this.format[i];
			if (f instanceof FieldFormat)
				f.format(idf, buf);
			else if (f instanceof NumberFormat)
				f.format(new Long(id), buf);
			else if (f instanceof DateTimeFormat)
				f.format(d, buf);
			else {
				f.format(null, buf);
			}
		}
		return buf.toString();
	}

	protected void compile() {
		char[] chars = this.pattern.toCharArray();
		boolean bStart = false;
		StringBuffer buf = new StringBuffer();
		List formats = new ArrayList();
		for (int i = 0; i < chars.length; ++i) {
			if ('{' == chars[i]) {
				if (bStart)
					throw new IllegalArgumentException("invalid pattern: "
							+ this.pattern);
				bStart = true;
				if (buf.length() > 0) {
					formats.add(new StringFormat(buf.toString()));
					buf.setLength(0);
				}
			} else if ('}' == chars[i]) {
				if (!(bStart))
					throw new IllegalArgumentException("invalid pattern: "
							+ this.pattern);
				bStart = false;
				if (buf.length() > 0) {
					formats.add(parse(buf.toString()));
					buf.setLength(0);
				}
			} else {
				buf.append(chars[i]);
			}
		}

		if (bStart) {
			throw new IllegalArgumentException("invalid pattern: "
					+ this.pattern);
		}
		if (buf.length() > 0) {
			formats.add(new StringFormat(buf.toString()));
			buf.setLength(0);
		}

		this.format = ((Format[]) formats.toArray(new Format[formats.size()]));
	}

	protected Format parse(String t) {
		char z = t.charAt(0);
		switch (z) {
		case '#':
			return new NumberFormat(t.length());
		case '$':
			return new FieldFormat(t.substring(1));
		}
		this.datePattern = t;
		return new DateTimeFormat(t);
	}

	abstract interface Format {
		public abstract void format(Object paramObject,
				StringBuffer paramStringBuffer);
	}

	class StringFormat implements IdFormat.Format {
		private String val;

		StringFormat(String paramString) {
			this.val = paramString;
		}

		public void format(Object data, StringBuffer buf) {
			if (this.val == null)
				throw new IllegalArgumentException("input is null.");
			buf.append(this.val);
		}
	}

	class NumberFormat implements IdFormat.Format {
		private int length;
		private long SEED = 100000000L;

		NumberFormat(int paramInt)
    {
      this.length = paramInt;
      this.SEED = (long) Math.pow(10.0D, paramInt);
    }

		public void format(Object data, StringBuffer buf) {
			if ((data == null) || (!(data instanceof Number))) {
				throw new IllegalArgumentException("input is not a number: "
						+ data);
			}
			String str = new Long(this.SEED + ((Number) data).longValue())
					.toString();

			buf.append(str.substring(str.length() - this.length));
		}
	}

	class DateTimeFormat implements IdFormat.Format {
		private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

		DateTimeFormat(String paramString) {
			this.dateFormat = new SimpleDateFormat(paramString);
		}

		public void format(Object data, StringBuffer buf) {
			if ((data != null) && (!(data instanceof Date)))
				throw new IllegalArgumentException("input is not a date: "
						+ data);
			Date d = (Date) data;
			if (d == null) {
				d = new Date();
			}
			buf.append(this.dateFormat.format(d));
		}
	}

	class FieldFormat implements IdFormat.Format {
		private String fName;

		FieldFormat(String paramString) {
			this.fName = Character.toUpperCase(paramString.charAt(0))
					+ paramString.substring(1);
		}

		public void format(Object data, StringBuffer buf) {
			if ((data == null) || (!(data instanceof IdFactory))) {
				throw new IllegalArgumentException(
						"input is not a id factory: " + data);
			}
			Class c = data.getClass();
			try {
				Method get = c.getMethod("get" + this.fName, null);
				Object o = get.invoke(data, null);
				if (o == null)
					throw new IllegalArgumentException("field '" + this.fName
							+ "'s value is null: " + data);
				buf.append(o);
			} catch (Exception e) {
				throw new IllegalArgumentException("cannot get field '"
						+ this.fName + "'s value: " + data);
			}
		}
	}
}
