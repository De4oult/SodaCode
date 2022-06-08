package com.de4oult.soda.parser;

public enum TokenType {
	
	NUMBER, // 0, 1, 2... 
	HEX_NUMBER, // #000000
	WORD, // WORD
	
	PLUS, // +
	MINUS, // -
	STAR, // *
	SLASH, // /
	EQUALS, // =
	
	LPAREN, // (
	RPAREN, // )
	
	EOF // End of File
}
