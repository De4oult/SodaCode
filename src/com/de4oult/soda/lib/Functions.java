package com.de4oult.soda.lib;

//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
		/*functions.put("get", (Function) (Value[] args) -> {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try {
				return new StringValue(reader.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;						
		});
		functions.put("getline", (Function) (Value[] args) -> {
			if(args.length == 0) throw new RuntimeException("Пустое значение аргумента!"); 
			if(args.length > 1) throw new RuntimeException("Функция принимает лишь один аргумент!"); 
			for (Value arg : args) {
				System.out.print(arg.asString());
			}
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			StringValue value;
			try {
				value = new StringValue(reader.readLine());
				return value;	
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		});*/
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
