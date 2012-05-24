package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class FPSectionNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	boolean isVar;
	AbstractNode identList, type;

	public FPSectionNode() {
		super();
		this.isVar = false;
		this.identList = null;
		this.type = null;
	}

	public FPSectionNode(boolean isVar, AbstractNode identList, AbstractNode type, int line, int column) {
		super(line, column);
		this.isVar = isVar;
		this.identList = identList;
		this.type = type;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		if (isVar) {
			trace("VAR");
		}
		if (identList != null) {
			identList.print();
		}
		if (type != null) {
			type.print();
		}
		if(isVar) {
			unindent();
		}
	}

}
