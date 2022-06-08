package com.de4oult.soda.parser.ast;

import com.de4oult.soda.lib.Variables;

public final class VariablesExpression implements Expression {

	private final String name;
	
	public VariablesExpression(String name) {
		this.name = name; 
	}
	
	@Override
	public double eval() {
		if(!Variables.isExists(name)) throw new RuntimeException("Данной константы не существует");
		return Variables.get(name);
	}

	@Override
	public String toString() {
		return String.format("%s [%f]", name, Variables.get(name));
	}
}
