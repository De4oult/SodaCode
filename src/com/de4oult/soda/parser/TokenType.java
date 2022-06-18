package com.de4oult.soda.parser;

public enum TokenType {
	
	NUMBER, // 0, 1, 2... 
	HEX_NUMBER, // #000000
	WORD, // WORD
	TEXT, // "TEXT"
	
	// types of data
	STRING,
	INTEGER,
	FLOAT,
	CHAR,
	
	// keywords
	LET,
	DISP,
	IF,
	ELSE,
	FOR,
	WHILE,
	DO,
	CONTINUE,
	REST,
	FUNC,
	RETURN,
	
	PLUS, // +
	MINUS, // -
	STAR, // *
	SLASH, // /
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
	LBRACE, // {
	RBRACE, // }
	LBRACKET, // [
	RBRACKET, // ]
	COMMA, // ,
	DOTCOMMA, // ;
	
	EOF // End of File
}
