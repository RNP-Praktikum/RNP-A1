package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class TypeNode extends AbstractNode {

	AbstractNode ident, type;
	int line, column;
	
	public TypeNode(AbstractNode ident, AbstractNode type, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.type = type;
	}
	
	public TypeNode() {
		super(0,0);
		this.ident = null;
		this.type = null;
	}

	public AbstractNode getIdent() {
		return ident;
	}

	public void setIdent(AbstractNode ident) {
		this.ident = ident;
	}

	public AbstractNode getType() {
		return type;
	}

	public void setType(AbstractNode type) {
		this.type = type;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("TypeNode");
		ident.print();
		type.print();
		unindent();
	}


}
