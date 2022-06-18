package com.de4oult.soda.lib;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class Functions {

	private static final NumberValue ZERO = new NumberValue(0);
	private static final Map<String, Function> functions;
	
	static {
		functions = new HashMap<>();
		functions.put("disp", (Function) (Value... args) -> {
			for (Value arg : args) {
				System.out.print(arg.asString());
			}
			return ZERO;
		});
		functions.put("displine", (Function) (Value... args) -> {
			for (Value arg : args) {
				System.out.println(arg.asString());
			}
			return ZERO;
		});
		functions.put("get", (Function) (Value[] args) -> {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			StringValue value = new StringValue(sc.nextLine());
			
			if(value.asString().matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) return new NumberValue(value.asNumber());
			
			return new StringValue(value.asString());					
		});
		functions.put("getline", (Function) (Value[] args) -> {
			if(args.length == 0) throw new RuntimeException("Пустое значение аргумента!"); 
			if(args.length > 1) throw new RuntimeException("Функция принимает лишь один аргумент!"); 
		    for (Value arg : args) {
				System.out.print(arg.asString());
			}
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			StringValue value = new StringValue(sc.nextLine());
			
			if(value.asString().matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+")) return new NumberValue(value.asNumber());
			
			return new StringValue(value.asString());
		});
	}
	
	public static boolean isExists(String key) {
		return functions.containsKey(key);
	}
	
	public static Function get(String key) {
		if(!isExists(key)) throw new RuntimeException("Неизвестная функция " + key + "!");
		return functions.get(key);
	}
	
	public static void set(String key, Function function) {
		functions.put(key, function);
	}
	
}
