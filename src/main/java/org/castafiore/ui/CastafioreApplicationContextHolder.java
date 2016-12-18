/*
 * Copyright (C) 2007-2010 Castafiore
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */
 package org.castafiore.ui;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.NamedThreadLocal;

public abstract class CastafioreApplicationContextHolder {
	
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal castafioreApplicationContextHolderThread = new NamedThreadLocal("CastafioreApplicationContextHolder");
	
	private static final ThreadLocal<Map<String,Object>> CTX = new ThreadLocal<Map<String,Object>>();

	@SuppressWarnings("unchecked")
	public static void setApplicationContext(Application application)
	{
		castafioreApplicationContextHolderThread.set(application);
	}
	
	public static void addInContext(String key, Object data){
		Map<String, Object> datas = CTX.get();
		if(datas == null){
			datas = new LinkedHashMap<String, Object>();
			CTX.set(datas);
		}
		
		datas.put(key, data);
	}
	
	
	@SuppressWarnings("unchecked")
	public static void resetApplicationContext() {
		castafioreApplicationContextHolderThread.set(null);
		CTX.set(null);
	}
	
	public static Object getContextValue(String key){
		Map<String, Object> datas = CTX.get();
		if(datas != null){
			return datas.get(key);
		}
		return null;
	}
	
	public static Application getCurrentApplication()
	{
		return (Application)castafioreApplicationContextHolderThread.get();
	}

}
