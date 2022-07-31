package com.de4oult.soda.lib;

import java.lang.reflect.Array;
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
		functions.put("plenty", new Function() {
			@Override
			public Value execute(Value... args) {
				return createArray(args, 0);
			}
			
			private ArrayValue createArray(Value[] args, int index) {
				final int size = (int) args[index].asNumber();
				final int last = args.length - 1;
				ArrayValue array = new ArrayValue(size);
				if(index == last) {
					for(int i = 0; i < size; i++) {
						array.set(i, NumberValue.ZERO);
					}
				}
				else if(index < last) {
					for(int i = 0; i < size; i++) {
						array.set(i, createArray(args, index + 1));
					}
				}
				return array;
			}
		});
		functions.put("typeOf", (Function) (Value... args) -> {
			if(args.length == 0) throw new RuntimeException("Пустое значение аргумента!");
			if(args.length > 1) throw new RuntimeException("Функция принимает лишь один аргумент!");

			return new StringValue(args[0].getClass().getSimpleName());
		});
		functions.put("len", (Function) (Value... args) -> {
			if(args.length == 0) throw new RuntimeException("Пустое значение аргумента!");
			if(args.length > 1) throw new RuntimeException("Функция принимает лишь один аргумент!");

			int len = 0;

			if(args[0] instanceof StringValue) {
				len = args[0].asString().length();
			}
			return new NumberValue(len);
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
