package com.de4oult.soda.parser.ast;

@SuppressWarnings("serial")
public final class ContinueStatement extends RuntimeException implements Statement {
	
	@Override
	public void execute() {
		throw this;
	}

	@Override
	public String toString() {
		return "continue";
	}
}
