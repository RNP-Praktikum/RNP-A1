package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class StatementNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8958067484073366938L;
	AbstractNode statement;
	
	
	
	public StatementNode(AbstractNode statement, int line, int column) {
		super(line, column);
		this.statement = statement;
	}
	
	

	public StatementNode() {
		super();
		this.statement = null;
	}



	public AbstractNode getStatement() {
		return statement;
	}

	public void setStatement(AbstractNode statement) {
		this.statement = statement;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		if (statement != null) statement.print();

	}

}
