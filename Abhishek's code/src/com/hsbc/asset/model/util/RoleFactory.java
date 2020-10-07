package com.hsbc.asset.model.util;

import com.hsbc.asset.model.beans.Admin;
import com.hsbc.asset.model.beans.Borrower;

public class RoleFactory {
	public static Object getInstance(UserType type) {
		Object obj = null;
		switch(type) {
			case ADMIN:
				obj = new Admin();
				break;
			case BORROWER:
				obj = new Borrower();
				break;
		}
		return obj;
	}
}
