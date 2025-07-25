package com.de4oult.soda.parser.ast;

public final class DoWhileStatement implements Statement {

	private final Expression condition;
	private final Statement statement;
	
	public DoWhileStatement(Expression condition, Statement statement) {
		this.condition = condition;
		this.statement = statement;
	}
	
	@Override
	public void execute() {
		do {
			try {
            	statement.execute();
            }
            catch(RestStatement rs) {
            	break;
            }
            catch(ContinueStatement cs) {
            	
            }
		} while(condition.eval().asNumber() != 0);
	}
	
	@Override
	public String toString() {
		return "do {\n" + statement + "\n} while " + condition;
	}

}
