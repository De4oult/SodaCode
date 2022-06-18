package com.de4oult.soda.parser.ast;

public final class WhileStatement implements Statement {

	private final Expression condition;
	private final Statement statement;
	
	public WhileStatement(Expression condition, Statement statement) {
		this.condition = condition;
		this.statement = statement;
	}
	
	@Override
	public void execute() {
		while(condition.eval().asNumber() != 0) {
			try {
            	statement.execute();
            }
            catch(RestStatement rs) {
            	break;
            }
            catch(ContinueStatement cs) {
            	
            }
		}
	}
	
	@Override
	public String toString() {
		return "while" + condition + " {\n" + statement + "\n}";
	}

}
