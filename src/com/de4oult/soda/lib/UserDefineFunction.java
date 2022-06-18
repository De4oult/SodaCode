package com.de4oult.soda.lib;

import java.util.List;

import com.de4oult.soda.parser.ast.ReturnStatement;
import com.de4oult.soda.parser.ast.Statement;

public final class UserDefineFunction implements Function {

	private final List<String> argNames;
	private final Statement body;
	
	public UserDefineFunction(List<String> argNames, Statement body) {
		this.argNames = argNames;
		this.body = body;
	}

	public int getArgsCount() {
		return argNames.size();
	}
	
	public String getArgsName(int index) {
		if(index < 0 || index >= getArgsCount()) return "";
		return argNames.get(index);
	}

	@Override
	public Value execute(Value... args) {
		try {
			body.execute();		
			return NumberValue.ZERO;	
		}
		catch(ReturnStatement rt) {
			return rt.getResult();
		}
	}

}
