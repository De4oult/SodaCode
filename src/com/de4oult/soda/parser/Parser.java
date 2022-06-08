package com.de4oult.soda.parser;

import java.util.ArrayList;
import java.util.List;

import com.de4oult.soda.parser.ast.AssignmentStatement;
import com.de4oult.soda.parser.ast.BinaryExpression;
import com.de4oult.soda.parser.ast.VariablesExpression;
import com.de4oult.soda.parser.ast.Expression;
import com.de4oult.soda.parser.ast.NumberExpression;
import com.de4oult.soda.parser.ast.Statement;
import com.de4oult.soda.parser.ast.UnaryExpression;

public final class Parser {
	
	private static final Token EOF = new Token(TokenType.EOF, "");
	
	private final List<Token> tokens;
	private final int size;
	private int pos;
	
	public Parser(List<Token> tokens) {
		this.tokens = tokens;
		size = tokens.size();
	}
	
	public List<Statement> parse() {
		final List<Statement> result = new ArrayList<>();
		while(!match(TokenType.EOF)) {
			result.add(statement());
		}
		return result;
	}
	
	private Statement statement() {
		return assignmentStatement();
	}
	
	private Statement assignmentStatement() {
		final Token current = get(0);
		if(match(TokenType.WORD) && get(0).getType() == TokenType.EQUALS) {
			final String variable = current.getText();
			consume(TokenType.EQUALS);
			return new AssignmentStatement(variable, expression());
		}
		throw new RuntimeException("Неизвестный оператор");
	}
	
	private Expression expression() {
		return additive();
	}

	private Expression additive() {
		Expression result = multiplicative();
		
		while(true) {
		if(match(TokenType.PLUS)) {
			result = new BinaryExpression('+', result, multiplicative());
			continue;
		}
		if(match(TokenType.MINUS)) {
			result = new BinaryExpression('-', result, multiplicative());
			continue;
		}
		break;
	}
		return result;
	}
	
	private Expression multiplicative() {
		Expression result = unary();
		
		while(true) {
			if(match(TokenType.STAR)) {
				result = new BinaryExpression('*', result, unary());
				continue;
			}
			if(match(TokenType.SLASH)) {
				result = new BinaryExpression('/', result, unary());
				continue;
			}
			break;
		}
		return result;
	}
	
	private Expression unary() {
		if(match(TokenType.MINUS)) {
			return new UnaryExpression('-', primary());
		}
		if(match(TokenType.PLUS)) {
			return primary();
		}
		return primary();
	}
	
	private Expression primary() {
		final Token current = get(0);
		if(match(TokenType.NUMBER)) {
			return new NumberExpression(Double.parseDouble(current.getText()));
		}
		if(match(TokenType.HEX_NUMBER)) {
			return new NumberExpression(Long.parseLong(current.getText(), 16));
		}
		if(match(TokenType.WORD)) {
			return new VariablesExpression(current.getText());
		}
		if(match(TokenType.LPAREN)) {
			Expression result = expression();
			match(TokenType.RPAREN);
			return result;
		}
		throw new RuntimeException("Неизвестное выражение");
	}
	
	private Token consume(TokenType type) {
		final Token current = get(0);
		if(type != current.getType()) throw new RuntimeException("Токен " + current + " не соответствует " + type);
		pos++;
		return current;
	}
	
	private boolean match(TokenType type) {
		final Token current = get(0);
		if(type != current.getType()) return false;
		pos++;
		return true;
	}
	
	private Token get(int relativePosition) {
		final int position = pos + relativePosition;
		if(position >= size) return EOF;
		return tokens.get(position);
	}
}
