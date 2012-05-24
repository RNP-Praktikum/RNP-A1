package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class ArrayNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode indexExpression, type;
	
	
	public ArrayNode(AbstractNode indexExpression, AbstractNode type,
			int line, int column) {
		super(line,column);
		this.indexExpression = indexExpression;
		this.type = type;
	}
	
	public ArrayNode() {
		super();
		this.indexExpression = null;
		this.type = null;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("Array");
		if (indexExpression != null) {
			indexExpression.print();
		}
		if (type != null) {
			type.print();
		}
		unindent();
	}

}
