package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class ConstNode extends AbstractNode {

	AbstractNode ident, expression;
	int line, column;
	
	public ConstNode(AbstractNode ident, AbstractNode expression, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.expression = expression;
	}
	
	public ConstNode() {
		super(0,0);
		this.ident = null;
		this.expression = null;
	}

	public AbstractNode getIdent() {
		return ident;
	}

	public void setIdent(AbstractNode ident) {
		this.ident = ident;
	}

	public AbstractNode getExpression() {
		return expression;
	}

	public void setExpression(AbstractNode expression) {
		this.expression = expression;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("ConstNode");
		ident.print();
		expression.print();
		unindent();
	}


}
