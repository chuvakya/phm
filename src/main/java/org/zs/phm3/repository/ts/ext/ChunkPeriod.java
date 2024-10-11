package org.zs.phm3.repository.ts.ext;
/**
 * Copyright © 2019-2020 The CETC PHM Authors
 *
*/

// формирование периода для чанка в числовом и строковом форматах
public class ChunkPeriod {
	
	public enum PERIOD {
		SECOND, MINUTE, HOUR, DAY, WEEK, MONTH
	}
	
	private static final String PERIOD_SECOND = " seconds";
	
	private static final String PERIOD_MINUTE = " minutes";
	
	private static final String PERIOD_HOUR = " hours";
	
	private static final String PERIOD_DAY = " day";
	
	private static final String PERIOD_WEEK = " week";
	
	private static final String PERIOD_MONTH = " 30 days"; // 30 days
	
	private int interval;
	
	private String intervalStr;

	public ChunkPeriod(int interval) {
		this.interval = interval;
	}
	
	public ChunkPeriod(int interval, PERIOD period) {
		this.interval = interval;
		switch(period) {
		case SECOND:
			this.intervalStr = String.valueOf(interval) + PERIOD_SECOND;
			break;
		case MINUTE:
			this.intervalStr = String.valueOf(interval) + PERIOD_MINUTE;
			break;
		case HOUR:
			this.intervalStr = String.valueOf(interval) + PERIOD_HOUR;
			break;
		case DAY:
			this.intervalStr = String.valueOf(interval) + PERIOD_DAY;
			break;
		case WEEK:
			this.intervalStr = String.valueOf(interval) + PERIOD_WEEK;
			break;
		case MONTH:
			this.intervalStr = String.valueOf(interval * 30L) + PERIOD_MONTH;
			break;
		}
	}

	public int getInterval() {
		return this.interval;
	}
	
	public String getIntervalStr() {
		if (this.intervalStr == null) {
			return String.valueOf(this.interval) + PERIOD_DAY;
		}
		return this.intervalStr;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	
	
}
