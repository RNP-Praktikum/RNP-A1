package nodes;

import java.util.*;

import descriptors.AbstractDescr;

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
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		
		for(AbstractNode elem : statements) {
			elem.compile(symbolTable);
		}
		return null;
	}

	@Override
	public void print() {
		trace("Statements");
		if(statements != null) {
			for(AbstractNode statement : statements) {
				if(statement != null) statement.print();
			}
		}
		unindent();
	}

}
