package com.de4oult.soda.parser.ast;

import com.de4oult.soda.lib.Value;
import com.de4oult.soda.lib.Variables;

public final class AssignmentStatement implements Statement {

	private final String variable;
	private final Expression expression;
	
	public AssignmentStatement(String variable, Expression expression) {
		this.variable = variable;
		this.expression = expression;
	}
	
	@Override
	public void execute() {
		final Value result = expression.eval();
		Variables.set(variable, result);
	}
	
	@Override
	public String toString() {
		return String.format("%s = %s", variable, expression);
	}
}
