package com.de4oult.soda;

import java.util.List;

import com.de4oult.soda.lib.Variables;
import com.de4oult.soda.parser.Lexer;
import com.de4oult.soda.parser.Parser;
import com.de4oult.soda.parser.Token;
import com.de4oult.soda.parser.ast.Statement;

public final class Main {
	
	public static void main(String[] args) {
		final String input = "num = 2 + 2\nexpr = SODA + num";
		final List<Token> tokens = new Lexer(input).tokenize();
		
		for(Token token : tokens) {
			System.out.println(token);
		}
		
		final List<Statement> statements = new Parser(tokens).parse();
		for(Statement statement : statements) {
			System.out.println(statement);
		}
		for(Statement statement : statements) {
			statement.execute();
		}
		System.out.printf("%s = %f\n", "num", Variables.get("num"));
		System.out.printf("%s = %f\n", "expr", Variables.get("expr"));		
	}
}
