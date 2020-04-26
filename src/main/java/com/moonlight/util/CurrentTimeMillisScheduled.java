package com.moonlight.util;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 高并发情况下优化System.currentTimeMillis()的效率
 * 
 * @author sunan
 * @date 2020-04-26
 */
public class CurrentTimeMillisScheduled {

	private volatile long now;

	private CurrentTimeMillisScheduled() {
		this.now = System.currentTimeMillis();
		schedule();
	}

	private void schedule() {
		new ScheduledThreadPoolExecutor(1, runnable -> {
			Thread thread = new Thread(runnable, "current-time-millis");
			thread.setDaemon(true);
			return thread;
		}).scheduleAtFixedRate(() -> {
			now = System.currentTimeMillis();
		}, 1, 1, TimeUnit.MILLISECONDS);
	}

	public long now() {
		return now;
	}

	public static CurrentTimeMillisScheduled getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private static class SingletonHolder {
		private static final CurrentTimeMillisScheduled INSTANCE = new CurrentTimeMillisScheduled();
	}
}
