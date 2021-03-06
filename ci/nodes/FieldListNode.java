package nodes;

import java.util.Map;

import descriptors.AbstractDescr;

public class FieldListNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode identList, type;

	public AbstractNode getIdentList() {
		return identList;
	}

	public void setIdentList(AbstractNode identList) {
		this.identList = identList;
	}

	public AbstractNode getType() {
		return type;
	}

	public void setType(AbstractNode type) {
		this.type = type;
	}

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
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
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
