package com.de4oult.soda.lib;

import java.util.HashMap;
import java.util.Map;

public final class Variables {

	private static final Map<String, Value> variables;
	
	static {
		variables = new HashMap<>();
		variables.put("PI", new NumberValue(Math.PI));
		variables.put("E", new NumberValue(Math.E));
		variables.put("SODA", new NumberValue(0.5));
		variables.put("CHRISTINE", new NumberValue(16));
	}
	
	public static boolean isExists(String key) {
		return variables.containsKey(key);
	}
	
	public static Value get(String key) {
		if(!isExists(key)) return new NumberValue(0);
		return variables.get(key);
	}
	
	public static void set(String key, Value value) {
		variables.put(key, value);
	}
}
