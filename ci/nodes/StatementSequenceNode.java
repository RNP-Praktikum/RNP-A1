package nodes;

import java.util.*;

import ci_compiler.AbstractDescr;

public class StatementSequenceNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8176339600250230973L;
	
	List<AbstractNode> statements;
	
	public StatementSequenceNode(List<AbstractNode> statements, int line, int column) {
		super(line,column);
		this.statements = statements;
		
	}

	public StatementSequenceNode(AbstractNode statement, int line, int column) {
		statements = new LinkedList<AbstractNode>();
		statements.add(statement);
	}
	
	public StatementSequenceNode() {
		super();
		this.statements = null;
	}

	public List<AbstractNode> getStatements() {
		return statements;
	}

	public void setStatements(List<AbstractNode> statements) {
		this.statements = statements;
	}

	
	
	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		// trace(StatementSequence);
		for(AbstractNode statement : statements) {
			if(statement != null) statement.print();
		}
		//unindent();
	}

}
