package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class ProcedureBodyNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode declarations, statementSequence;

	public ProcedureBodyNode() {
		super();
		this.declarations = null;
		this.statementSequence = null;
	}

	public ProcedureBodyNode(AbstractNode declarations, AbstractNode statementSequence, int line, int column) {
		super(line, column);
		this.declarations = declarations;
		this.statementSequence = statementSequence;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("ProcedureBody");
		if (declarations != null) {
			declarations.print();
		}
		if (statementSequence != null) {
			statementSequence.print();
		}
		unindent();
	}

}
