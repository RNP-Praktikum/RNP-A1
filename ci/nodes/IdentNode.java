package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class IdentNode extends AbstractNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ident;

	public IdentNode() {
		super();
		this.ident = null;
	}

	public IdentNode(String ident, int line, int column) {
		super(line, column);
		this.ident = ident;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace(ident);
		unindent();
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}
	
	

}
