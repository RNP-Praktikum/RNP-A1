package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class IntegerNode extends AbstractNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1789097387058308869L;
	int value;

	public IntegerNode() {
		super();
		this.value = 0;
	}

	public IntegerNode(String val, int line, int column) {
		super(line, column);
		this.value = Integer.parseInt(val);
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("IntNode");
		trace(value + "");
		unindent();
		unindent();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
