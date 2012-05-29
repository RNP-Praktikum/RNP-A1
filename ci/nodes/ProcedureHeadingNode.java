package nodes;

import java.util.Map;

import descriptors.AbstractDescr;

public class ProcedureHeadingNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode ident, formalParameters;

	public ProcedureHeadingNode() {
		super();
		this.ident = null;
		this.formalParameters = null;
	}

	public ProcedureHeadingNode(AbstractNode ident, AbstractNode formalParameters, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.formalParameters = formalParameters;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("ProcedureHeading");
		if (ident != null) {
			ident.print();
		}
		if (formalParameters != null) {
			formalParameters.print();
		}
		unindent();
	}

}
