package com.de4oult.soda.parser;

import com.de4oult.soda.lib.NumberValue;
import com.de4oult.soda.lib.Value;
import com.de4oult.soda.parser.ast.Expression;

public final class IncrementExpression implements Expression {

	private final Expression expr1;
	private final String operation;
	
	public IncrementExpression(String operation, Expression expr1) {
		this.operation = operation;
		this.expr1 = expr1;
	}
	
	@Override
	public Value eval() {
		switch(operation) {
		case "--": return new NumberValue(expr1.eval().asNumber() - 1);
		case "++":
		default:
			return new NumberValue(expr1.eval().asNumber() + 1);
	}
	}

}
