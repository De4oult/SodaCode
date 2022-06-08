package com.de4oult.soda.parser;

public enum TokenType {
	
	NUMBER, // 0, 1, 2... 
	HEX_NUMBER, // #000000
	WORD, // WORD
	TEXT, // "TEXT"
	
	//keywords
	DISP,
	IF,
	ELSE,
	
	PLUS, // +
	MINUS, // -
	STAR, // *
	SLASH, // /
	INCREMENT, // word++
	DECREMENT, // word--
	EQUALS, // =
	EQEQ, // ==
	EXCL, // !
	EXCLEQ, // != 
	LT, // <
	LTEQ, // <=
	GT, // >
	GTEQ, // >=
	
	BAR, // |
	BARBAR, // ||
	AMP, // &
	AMPAMP, // &&
	
	LPAREN, // (
	RPAREN, // )
	
	EOF // End of File
}
