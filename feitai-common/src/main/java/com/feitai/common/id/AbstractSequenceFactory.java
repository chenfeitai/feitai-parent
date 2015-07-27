package com.feitai.common.id;

import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractSequenceFactory implements SequenceFactory {
	protected final Log log = LogFactory.getLog(super.getClass());
	protected String type;
	private IdFormat format;
	protected IdTable table;

	public AbstractSequenceFactory() {
		this.type = null;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setFormat(String pattern) {
		this.format = IdFormat.getInstance(pattern);
	}

	public void setTable(IdTable t) {
		this.table = t;
	}

	public Object generate() {
		return generate(null, 1);
	}

	public Object generate(int count) {
		return generate(null, count);
	}

	public Object generate(String range) {
		return generate(range, 1);
	}

	public Object generate(String range, int count) {
		if (this.type == null)
			throw new RuntimeException(
					"you must setType(String) in tableIdFactory.");
		long start = System.currentTimeMillis();
		long seq = internalGenerate(range, count);
		if (this.log.isDebugEnabled()) {
			this.log.debug("new a seq no spend: "
					+ (System.currentTimeMillis() - start));
		}

		if (count == 1) {
			if (this.format == null) {
				return new Long(seq);
			}
			return format(seq);
		}
		if (this.format == null) {
			String[] ret = new String[count];
			int i = 0;
			while (true) {
				ret[i] = String.valueOf(seq + i);
				++i;
				if (i >= count) {
					return ret;
				}
			}
		}
		String[] ret = new String[count];
		for (int i = 0; i < count; ++i) {
			ret[i] = format(seq + i);
		}
		return ret;
	}

	protected String format(long l) {
		return this.format.format(this, l, new Date());
	}

	protected abstract long internalGenerate(String paramString, int paramInt);
}