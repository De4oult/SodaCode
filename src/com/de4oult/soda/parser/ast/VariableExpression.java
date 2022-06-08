package com.de4oult.soda.parser.ast;

import com.de4oult.soda.lib.Value;
import com.de4oult.soda.lib.Variables;

public final class VariableExpression implements Expression {

	private final String name;
	
	public VariableExpression(String name) {
		this.name = name; 
	}
	
	@Override
	public Value eval() {
		if(!Variables.isExists(name)) throw new RuntimeException("Данной константы не существует");
		return Variables.get(name);
	}

	@Override
	public String toString() {
		return String.format("%s", name);
	}
}
