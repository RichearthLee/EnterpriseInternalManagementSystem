package com.mftcc.interior.cred.util;

import java.util.Comparator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.mftcc.interior.cred.bean.AcCredServiceGroup;

public class ServiceGroupComparator implements Comparator<AcCredServiceGroup> {

	// asc/desc
	private String direction;

	public ServiceGroupComparator(String direction) {
		this.direction = direction;
	}

	@Override
	public int compare(AcCredServiceGroup o1, AcCredServiceGroup o2) {
		String level1 = o1.getLevel();
		String level2 = o2.getLevel();
		if ("asc".equals(direction)) {
			if (StringUtils.isEmpty(level1)) {
				if (StringUtils.isEmpty(level2)) {
					return 0;
				} else {
					return -1;
				}
			}
			return level1.compareTo(level2);
		} else {
			if (StringUtils.isEmpty(level1)) {
				if (StringUtils.isEmpty(level2)) {
					return -1;
				} else {
					return 0;
				}
			}
			return -level1.compareTo(level2);
		}
	}

}
