package com.moonlight.util;

import org.springframework.util.StringUtils;

/**
 * 全局唯一ID生成器
 * 
 * @author sunan
 * @date 2020-04-26
 */
public class IdGenerator {

	//当前自增数
	private Integer autoincrement = 0;

	//自增数最大数值 比如4位自增位最大数值为9999 由构造器赋值
	private Integer maxAutoIncrement = 0;

	//自增位最大位数
	private final Integer maxAutoIncrementLength = 4;

	//最近一次获取的时间戳
	private Long lastCurrentTimeMillis;
	
	private IdGenerator() {
		//初始化自增数最大数值
		for (int i = 0; i < maxAutoIncrementLength; i++) {
			Double d = 9*(Math.pow(10, i));
			maxAutoIncrement += d.intValue();
		}
	}

	/**
	 * 获取当前可使用的自增数
	 * 
	 * @return
	 */
	private String getAutoincrementStr() {
		if (autoincrement > maxAutoIncrement) {
			return "";
		}
		String autoincrementStr = autoincrement.toString();
		int fillNum = maxAutoIncrementLength - autoincrementStr.length();
		if (fillNum > 0) {
			StringBuffer fillStr = new StringBuffer();
			for (int j = 0; j < fillNum; j++) {
				fillStr.append("0");
			}
			return fillStr.toString() + autoincrementStr;
		}
		return autoincrementStr;
	}

	/**
	 * 获取全局唯一ID 限制每毫秒${maxAutoIncrement}个请求
	 * 
	 * @return
	 */
	public synchronized Long nextId() {
		long currentTimeMillis = System.currentTimeMillis();
		if (lastCurrentTimeMillis != null && currentTimeMillis != lastCurrentTimeMillis) {
			autoincrement = 0;
		}
		lastCurrentTimeMillis = currentTimeMillis;
		String autoincrementStr = getAutoincrementStr();
		if (StringUtils.isEmpty(autoincrementStr)) {
			return 0L;
		}
		String idStr = currentTimeMillis + autoincrementStr;
		autoincrement++;
		return Long.parseLong(idStr);
	}

	/**
	 * 获取单例
	 * 
	 * @return
	 */
	public static IdGenerator getInstance() {
		return SingletonHolder.instance;
	}

	/**
	 * 单例模式
	 * 
	 * @author sunan
	 *
	 */
	private static class SingletonHolder {
		private static final IdGenerator instance = new IdGenerator();
	}
}
