package com.de4oult.soda.parser.ast;

import java.util.List;

import com.de4oult.soda.lib.Functions;
import com.de4oult.soda.lib.UserDefineFunction;

public final class FunctionDefine implements Statement {

	private final String name;
	private final List<String> argNames;
	private final Statement body;
	
	public FunctionDefine(String name, List<String> argNames, Statement body) {
		this.name = name;
		this.argNames = argNames;
		this.body = body;
	}

	@Override
	public void execute() {
		Functions.set(name, new UserDefineFunction(argNames, body));
	}
	
	@Override
	public String toString() {
		return "func(" + argNames.toString() + ") {\n" + body.toString() + "}\n";
	}

}
