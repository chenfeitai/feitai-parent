package com.feitai.common.id;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.log4j.Logger;

class RollingCalendar extends GregorianCalendar {
	static final Logger logger = Logger.getLogger(RollingCalendar.class);

	static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");
	public static final int TOP_OF_TROUBLE = -1;
	public static final int TOP_OF_SECOND = 0;
	public static final int TOP_OF_MINUTE = 1;
	public static final int TOP_OF_HOUR = 2;
	public static final int HALF_DAY = 3;
	public static final int TOP_OF_DAY = 4;
	public static final int TOP_OF_WEEK = 5;
	public static final int TOP_OF_MONTH = 6;
	int type = -1;

	public RollingCalendar() {
	}

	public RollingCalendar(TimeZone tz, Locale locale) {
		super(tz, locale);
	}

	public void init(String datePattern) {
		this.type = computeTriggeringPeriod(datePattern);
	}

	public void setType(int type) {
		this.type = type;
	}

	public long getNextCheckMillis(Date now) {
		return getNextCheckDate(now).getTime();
	}

	public int computeTriggeringPeriod(String datePattern) {
		RollingCalendar rollingCalendar = new RollingCalendar(GMT_TIMEZONE,
				Locale.getDefault());

		Date epoch = new Date(0L);

		if (datePattern != null) {
			for (int i = 0; i <= 6; ++i) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						datePattern);
				simpleDateFormat.setTimeZone(GMT_TIMEZONE);

				String r0 = simpleDateFormat.format(epoch);
				rollingCalendar.setType(i);

				Date next = new Date(rollingCalendar.getNextCheckMillis(epoch));
				String r1 = simpleDateFormat.format(next);

				if ((r0 != null) && (r1 != null) && (!(r0.equals(r1)))) {
					return i;
				}
			}
		}

		return -1;
	}

	public void printPeriodicity() {
		switch (this.type) {
		case 0:
			logger.debug("Rollover every second.");

			break;
		case 1:
			logger.debug("Rollover every minute.");

			break;
		case 2:
			logger.debug("Rollover at the top of every hour.");

			break;
		case 3:
			logger.debug("Rollover at midday and midnight.");

			break;
		case 4:
			logger.debug("Rollover at midnight.");

			break;
		case 5:
			logger.debug("Rollover at the start of week.");

			break;
		case 6:
			logger.debug("Rollover at start of every month.");

			break;
		default:
			logger.warn("Unknown periodicity.");
		}
	}

	public Date getNextCheckDate(Date now) {
		setTime(now);

		switch (this.type) {
		case 0:
			set(14, 0);
			add(13, 1);
			break;
		case 1:
			set(13, 0);
			set(14, 0);
			add(12, 1);
			break;
		case 2:
			set(12, 0);
			set(13, 0);
			set(14, 0);
			add(11, 1);
			break;
		case 3:
			set(12, 0);
			set(13, 0);
			set(14, 0);
			int hour = get(11);
			if (hour < 12) {
				set(11, 12);
			}
			set(11, 0);
			add(5, 1);
			break;
		case 4:
			set(11, 0);
			set(12, 0);
			set(13, 0);
			set(14, 0);
			add(5, 1);

			break;
		case 5:
			set(7, getFirstDayOfWeek());
			set(11, 0);
			set(13, 0);
			set(14, 0);
			add(3, 1);

			break;
		case 6:
			set(5, 1);
			set(11, 0);
			set(13, 0);
			set(14, 0);
			add(2, 1);

			break;
		default:
			throw new IllegalStateException("Unknown periodicity type.");
		}

		return getTime();
	}
}