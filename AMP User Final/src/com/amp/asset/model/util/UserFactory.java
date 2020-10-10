package com.amp.asset.model.util;

import com.amp.asset.model.business.AssetServiceImpl;
import com.amp.asset.model.dao.AssetDaoImpl;

public class UserFactory {

	public static Object getInstance(LayerType type) {
		Object obj = null;
		switch(type) {
			case SERVICE:
				obj = new AssetServiceImpl();
				break;
			case DAO:
				obj = new AssetDaoImpl();
				break;
		}
		return obj;
	}
}
