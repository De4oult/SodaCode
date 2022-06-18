package com.de4oult.soda;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import com.de4oult.soda.parser.Lexer;
import com.de4oult.soda.parser.Parser;
import com.de4oult.soda.parser.Token;
import com.de4oult.soda.parser.ast.Statement;

public final class Main {
	
	public static void main(String[] args) throws IOException {
		System.out.print("Компилируемый файл >> ");
			
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String pathToScript = sc.nextLine();
		System.out.println("\n\n");
		final String input = new String(Files.readAllBytes(Paths.get(pathToScript )), "UTF-8");
		final List<Token> tokens = new Lexer(input).tokenize();
		final Statement program = new Parser(tokens).parse();
		program.execute();
	}
}
