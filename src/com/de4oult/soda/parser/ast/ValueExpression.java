package com.de4oult.soda.parser.ast;

import com.de4oult.soda.lib.NumberValue;
import com.de4oult.soda.lib.StringValue;
import com.de4oult.soda.lib.Value;

public final class ValueExpression implements Expression {

	private final Value value;
	
	public ValueExpression(double value) {
		this.value = new NumberValue(value);
	}
	
	public ValueExpression(String value) {
		this.value = new StringValue(value);
	}
	
	@Override
	public Value eval() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.asString();
	}

}
