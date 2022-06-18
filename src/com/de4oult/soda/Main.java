package com.de4oult.soda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.de4oult.soda.parser.Lexer;
import com.de4oult.soda.parser.Parser;
import com.de4oult.soda.parser.Token;
import com.de4oult.soda.parser.ast.Statement;

public final class Main {
	
	public static void main(String[] args) throws IOException {
		final String input = new String(Files.readAllBytes(Paths.get("program.sc")), "UTF-8");
		final List<Token> tokens = new Lexer(input).tokenize();
		
		for(Token token : tokens) {
			System.out.println(token);
		}
		
		final Statement program = new Parser(tokens).parse();
		System.out.println(program.toString());
		program.execute();
	}
}
