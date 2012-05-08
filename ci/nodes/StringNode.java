package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class StringNode extends AbstractNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2894749233320105022L;
	String string;

	public StringNode() {
		super();
		this.string = null;
	}

	public StringNode(String string, int line, int column) {
		super(line, column);
		this.string = string;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
//		trace("String ");
		trace(string);
		unindent();
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

}
