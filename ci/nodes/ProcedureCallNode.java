package nodes;

import java.util.*;

import ci_compiler.AbstractDescr;

public class ProcedureCallNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractNode ident;
	List<AbstractNode> parameterList;
	
	public ProcedureCallNode(AbstractNode ident, List<AbstractNode> expression, int line, int column) {
		super(line,column);
		this.ident = ident;
		this.parameterList = expression;
	}

	public ProcedureCallNode() {
		super();
		this.ident = null;
		this.parameterList = null;
		
	}

	public AbstractNode getIdent() {
		return ident;
	}

	public void setIdent(AbstractNode ident) {
		this.ident = ident;
	}

	public List<AbstractNode> getExpression() {
		return parameterList;
	}

	public void setExpression(List<AbstractNode> expression) {
		this.parameterList = expression;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("(");
		if(ident != null) {
			ident.print();
		}
		if(parameterList != null) {
		for(AbstractNode expression : parameterList) {
			expression.print();
		}
		}
		unindent();
	}
}
