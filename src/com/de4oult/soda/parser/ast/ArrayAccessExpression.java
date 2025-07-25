package com.de4oult.soda.parser.ast;

import java.util.List;

import com.de4oult.soda.lib.ArrayValue;
import com.de4oult.soda.lib.Value;
import com.de4oult.soda.lib.Variables;

public final class ArrayAccessExpression implements Expression {

	private final String variable;
	private final List<Expression> indices;

	public ArrayAccessExpression(String variable, List<Expression> indices) {
		this.variable = variable;
		this.indices = indices;
	}

	@Override
	public Value eval() {
		return getArray().get(lastIndex());
	}
	
	public ArrayValue getArray() {
		ArrayValue array = consumeArray(Variables.get(variable));
		final int last = indices.size() - 1;
		for(int i = 0; i < last; i++) {
			array = consumeArray(array.get(index(i)));
		}
		return array;
	}
	
	public int lastIndex() {
		return index(indices.size() - 1);
	}
	
	private int index(int index) {
		return (int) indices.get(index).eval().asNumber();
	}
	
	private ArrayValue consumeArray(Value value) {
		if(value instanceof ArrayValue) {
			return (ArrayValue) value;
		}
		else {
			throw new RuntimeException("�������� ������");
		}
	}
	
	@Override
	public String toString() {
		return variable + indices;
	}
}
