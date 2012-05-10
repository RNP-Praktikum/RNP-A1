package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class AssignmentNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractNode ident, selector, expression;
	
	
	public AssignmentNode(AbstractNode ident, AbstractNode selector,
			AbstractNode expression, int line, int column) {
		super(line,column);
		this.ident = ident;
		this.selector = selector;
		this.expression = expression;
	}
	
	public AssignmentNode() {
		super();
		this.ident = null;
		this.selector = null;
		this.expression = null;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace(":=");
		if(ident != null) {
			ident.print();
		}
		if(selector != null) {
			selector.print();
		}
		if(expression != null) {
			expression.print();
		}
	}

}
