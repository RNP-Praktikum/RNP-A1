package nodes;

import java.util.*;

import ci_compiler.AbstractDescr;

public class ProcedureCallNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractNode ident;
	AbstractNode parameterList;
	
	public ProcedureCallNode(AbstractNode ident, AbstractNode expressionList, int line, int column) {
		super(line,column);
		this.ident = ident;
		this.parameterList = expressionList;
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

	public AbstractNode getExpressionList() {
		return parameterList;
	}

	public void setExpressionList(AbstractNode expressionList) {
		this.parameterList = expressionList;
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
		parameterList.print();
		}
		unindent();
	}
}
