package com.de4oult.soda.parser.ast;

public final class ForStatement implements Statement {

	private final Statement initialization;
    private final Expression termination;
    private final Statement increment;
    private final Statement statement;

    public ForStatement(Statement initialization, Expression termination, Statement increment, Statement block) {
        this.initialization = initialization;
        this.termination = termination;
        this.increment = increment;
        this.statement = block;
    }

    @Override
    public void execute() {
        for (initialization.execute(); termination.eval().asNumber() != 0; increment.execute()) {
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
		return "for" + initialization + ", " + termination + ", " + increment + " " + statement;
	}

}
