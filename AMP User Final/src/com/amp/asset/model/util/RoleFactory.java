package com.amp.asset.model.util;

import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;

public class RoleFactory {
	public static Object getInstance(UserType type) {
		Object obj = null;
		switch(type) {
			case ADMIN:
				obj = new Admin();
				break;
			case EMPLOYEE:
				obj = new Employee();
				break;
		}
		return obj;
	}
}
