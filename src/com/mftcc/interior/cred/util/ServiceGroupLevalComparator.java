package com.mftcc.interior.cred.util;

import java.util.Comparator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ServiceGroupLevalComparator implements Comparator<Map<String, String>> {

	@Override
	public int compare(Map<String, String> o1, Map<String, String> o2) {
		String level1=o1.get("level");
		String level2=o2.get("level");
		if(StringUtils.isEmpty(level1)){
			if(StringUtils.isEmpty(level2)){
				return 0;
			}else{
				return -1;
			}
		}
		return level1.compareTo(level2);
	}

}
