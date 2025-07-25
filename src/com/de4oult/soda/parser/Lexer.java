package com.de4oult.soda.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Lexer {
	private static final String OPERATOR_CHARS = "+-*/()[]{}=<>!&|;,";
	
	private static final Map<String, TokenType> OPERATORS;
	static {
		OPERATORS = new HashMap<>();
		OPERATORS.put("+", TokenType.PLUS);
		OPERATORS.put("-", TokenType.MINUS);
		OPERATORS.put("*", TokenType.STAR);
		OPERATORS.put("/", TokenType.SLASH);
		OPERATORS.put("(", TokenType.LPAREN);
		OPERATORS.put(")", TokenType.RPAREN);
		OPERATORS.put("[", TokenType.LBRACKET);
		OPERATORS.put("]", TokenType.RBRACKET);
		OPERATORS.put("{", TokenType.LBRACE);
		OPERATORS.put("}", TokenType.RBRACE);
		OPERATORS.put("=", TokenType.EQUALS);
		OPERATORS.put("<", TokenType.LT);
		OPERATORS.put(">", TokenType.GT);
		OPERATORS.put(",", TokenType.COMMA);
		OPERATORS.put(";", TokenType.DOTCOMMA);

		OPERATORS.put("!", TokenType.EXCL);
		OPERATORS.put("&", TokenType.AMP);
		OPERATORS.put("|", TokenType.BAR);
		
		OPERATORS.put("==", TokenType.EQEQ);
		OPERATORS.put("!=", TokenType.EXCLEQ);
		OPERATORS.put("<=", TokenType.LTEQ);
		OPERATORS.put(">=", TokenType.GTEQ);
		
		OPERATORS.put("&&", TokenType.AMPAMP);
		OPERATORS.put("||", TokenType.BARBAR);

		//OPERATORS.put("++", TokenType.INCREMENT);
		//OPERATORS.put("--", TokenType.DECREMENT);
	}
	
	private final String input;
	private final int length;
	private final List<Token> tokens;
	
	private int pos;
	
	public Lexer(String input) {
		this.input = input;
		length = input.length();
		
		tokens = new ArrayList<>();
	}
	
	public List<Token> tokenize() {
		while(pos < length) {
			final char current = peek(0);
			
			if(Character.isDigit(current)) tokenizeNumber();
			else if(Character.isLetter(current)) tokenizeWord();
			else if(current == '#') {
				next();
				tokenizeHexNumber();
			}
			else if(current == '"') {
				tokenizeText();
			}
			else if(OPERATOR_CHARS.indexOf(current) != -1) { 
				tokenizeOperator();
			}
			else {
				next();
			}
		}
		return tokens;
	}

	private void tokenizeNumber() {
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		
		while(true) {
			if(current == '.') {
				if(buffer.indexOf(".") != -1) throw new RuntimeException("������������ ������� �����");
			}
			else if(!Character.isDigit(current)){
				break;
			}
			buffer.append(current);
			current = next();
		}
		addToken(TokenType.NUMBER, buffer.toString());
	}
	
	private void tokenizeHexNumber() {
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		
		while(Character.isDigit(current) || isHexNumber(current)) {
			buffer.append(current);
			current = next();
		}
		addToken(TokenType.HEX_NUMBER, buffer.toString());
	}

	private boolean isHexNumber(char current) {
		return "abcdef".indexOf(Character.toLowerCase(current)) != -1;
	}

	private void tokenizeOperator() {
		char current = peek(0);
		if(current == '/') {
			if(peek(1) == '/') {
				next();
				next();
				tokenizeComment();
				return;
			}
			else if(peek(1) == '*') {
				next();
				next();
				tokenizeMultilineComment();
				return;
			}
		}
		final StringBuilder buffer = new StringBuilder();
		while(true) {
			final String text = buffer.toString();
			if(!OPERATORS.containsKey(text + current) && !text.isEmpty()) {
				addToken(OPERATORS.get(text));
				return;
			}
			buffer.append(current);
			current = next();
		}
	}
	
	private void tokenizeWord() {
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		
		while(true) {
			if(!Character.isLetterOrDigit(current) && (current != '_') && (current != '$')) {
				break;
			}
			buffer.append(current);
			current = next();
		}
		String word = buffer.toString();
		switch(word) {
			case "disp": addToken(TokenType.DISP); break;
			case "if": addToken(TokenType.IF); break;
			case "else": addToken(TokenType.ELSE); break;
			case "while": addToken(TokenType.WHILE); break;
			case "for": addToken(TokenType.FOR); break;
			case "do": addToken(TokenType.DO); break;
			case "continue": addToken(TokenType.CONTINUE); break;
			case "rest": addToken(TokenType.REST); break;
			case "func": addToken(TokenType.FUNC); break;
			case "return": addToken(TokenType.RETURN); break;
			default: 
				addToken(TokenType.WORD, word);
				break;
		}	
	}

	private void tokenizeText() {
		next();
		final StringBuilder buffer = new StringBuilder();
		char current = peek(0);
		
		while(true) {
			if(current == '\\') {
				current = next();
				switch(current) {
					case '"': current = next(); buffer.append('"'); continue;
					case 'n': current = next(); buffer.append('\n'); continue;
					case 't': current = next(); buffer.append('\t'); continue;
				}
				buffer.append('\\');
				continue;
			}
			if(current == '"') break;
			
			buffer.append(current);
			current = next();
		}
		next();
		
		addToken(TokenType.TEXT, buffer.toString());
	}
	
	private void tokenizeComment() {
		char current = peek(0);
		while("\r\n\0".indexOf(current) == -1) {
			current = next();
		}
	}
	
	private void tokenizeMultilineComment() {
		char current = peek(0);
		while(true) {
			if(current == '\0') throw new RuntimeException("�������� ����������� ��� �����������");
			if(current == '*' && peek(1) == '/') break;
			current = next();
		}
		next();
		next();
	}
	
	private char next() {
		pos++;
		
		return peek(0);
	}
	
	private char peek(int relativePosition) {
		final int position = pos + relativePosition;
		if(position >= length) return '\0';
		
		return input.charAt(position);
	}

	private void addToken(TokenType type) {
		addToken(type, "");
	}
	
	private void addToken(TokenType type, String text) {
		tokens.add(new Token(type, text));
	}
}
