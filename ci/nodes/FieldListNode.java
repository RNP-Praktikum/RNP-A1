package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class FieldListNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode identList, type;

	public FieldListNode() {
		super();
		this.identList = null;
		this.type = null;
	}

	public FieldListNode(AbstractNode identList, AbstractNode type, int line, int column) {
		super(line, column);
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
		trace(":");
		if (identList != null) {
			identList.print();
		}
		if (type != null) {
			type.print();
		}
		unindent();

	}

}
