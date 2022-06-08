package com.de4oult.soda.parser.ast;

public final class DisplayStatement implements Statement {

	private final Expression expression;
	
	public DisplayStatement(Expression expression) {
		this.expression = expression;
	}
	
	@Override
	public void execute() {
		System.out.print(expression.eval().asString());
	}
	
	@Override
	public String toString() {
		return "display " + expression; 
	}

}
