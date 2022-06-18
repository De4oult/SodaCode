package com.de4oult.soda.parser.ast;

import java.util.List;

import com.de4oult.soda.lib.ArrayValue;
import com.de4oult.soda.lib.Value;

public final class ArrayExpression implements Expression {

	private final List<Expression> elements;
	
	public ArrayExpression(List<Expression> args) {
		this.elements = args;
	}
		
	@Override
	public Value eval() {
		final int size = elements.size();
		final ArrayValue array = new ArrayValue(size);
		for(int i = 0; i < size; i++) {
			array.set(i, elements.get(i).eval());
		}
		return array;
	}
	
	@Override
	public String toString() {
		return elements.toString();
	}
}