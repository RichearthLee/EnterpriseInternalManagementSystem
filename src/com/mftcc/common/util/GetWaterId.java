/**
 * Copyright (C) DXHM 版权所有
 * @文件名 GetWaterId.java
 * @包名 com.dxcc.common.dao
 * @说明 TODO(描述文件做什么)
 * @作者 rjq
 * @时间 Oct 28, 2010 12:42:36 PM
 * @版本 V1.0
 */
package com.mftcc.common.util;

/**
 * @类名 GetWaterId
 * @描述 TODO(用途说明) 获得唯一标示
 * @作者 rjq
 * @日期 Oct 28, 2010 12:42:36 PM ========修改日志=======
 * 
 */
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GetWaterId {

	private static Log log=LogFactory.getLog(GetWaterId.class);
	private static int currIndex = 0;

	/**
	 * 
	 * @名称 getWaterId
	 * @描述 TODO(方法描述)获得唯一标示
	 * @作者 rjq
	 * @时间 Oct 28, 2010 12:46:28 PM
	 * @@return
	 */
	public static synchronized String getWaterId() {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			log.error("wows201406090857462920", e);
			e.printStackTrace();
		}
		Date now = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		if (currIndex > 9) {
			currIndex = 0;
		}
		String l_waterid = f.format(now) + currIndex;
		currIndex++;
		return l_waterid;
	}
	

	private static int _suffix = 0;

	public static synchronized String getPK(String _prefix) {
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			log.error("wows201406090857463491", e);
			e.printStackTrace();
		}
		// 获得按照年月日生成的流水号
		String strTmp = formatDate(new Date(), "yyyyMMddHHmmssSSS");
		if (_suffix > 9){
			_suffix = 0;
		}
		strTmp = _prefix + strTmp + _suffix;
		_suffix++;
		return strTmp;
	}

	public static String formatDate(Date date, String aformat) {
		if (date == null) {
			return "------";
		} else {
			String s1 = "";
			// 设置日期格式
			SimpleDateFormat simpledateformat = new SimpleDateFormat(aformat);
			s1 = simpledateformat.format(date);
			return s1;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			System.out.println(getWaterId());
		}
	}
}
