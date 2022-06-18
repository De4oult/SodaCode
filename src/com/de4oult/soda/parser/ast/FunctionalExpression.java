package com.de4oult.soda.parser.ast;

import java.util.ArrayList;
import java.util.List;

import com.de4oult.soda.lib.Function;
import com.de4oult.soda.lib.Functions;
import com.de4oult.soda.lib.UserDefineFunction;
import com.de4oult.soda.lib.Value;
import com.de4oult.soda.lib.Variables;

public final class FunctionalExpression implements Expression {

	private final String name;
	private final List<Expression> args;
	
	public FunctionalExpression(String name) {
		this.name = name;
		args = new ArrayList<>();
	}
	
	public FunctionalExpression(String name, List<Expression> args) {
		this.name = name;
		this.args = args;
	}
	
	public void addArgument(Expression arg) {
		args.add(arg);
	}
	
	@Override
	public Value eval() {
		final int size = args.size();
		final Value[] values = new Value[size];
		for(int i = 0; i < size; i++) {
			values[i] = args.get(i).eval();
		}
		
		final Function function = Functions.get(name);
		if(function instanceof UserDefineFunction) {
			final UserDefineFunction userFunction = (UserDefineFunction) function;
			if(size != userFunction.getArgsCount()) throw new RuntimeException("Количество переданных аргументов не совпадает с количеством запрашиваемых!");
			
			Variables.push();
			for(int i = 0; i < size; i++) {
				Variables.set(userFunction.getArgsName(i), values[i]);
			}
			final Value result = userFunction.execute(values);
			Variables.pop();
			return result;
		}		
		return function.execute(values);
	}
	
	@Override
	public String toString() {
		return name + "(" + args.toString() + ")";
	}
}