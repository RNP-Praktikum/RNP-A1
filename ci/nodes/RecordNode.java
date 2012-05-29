package nodes;

import java.util.Map;

import descriptors.AbstractDescr;

public class RecordNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode fieldLists;

	public RecordNode() {
		super();
		this.fieldLists = null;
	}

	public RecordNode(AbstractNode fieldLists, int line, int column) {
		super(line, column);
		this.fieldLists = fieldLists;
		this.line = line;
		this.column = column;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("Record");
		if(fieldLists != null) {
			fieldLists.print();
		}
		unindent();
	}

}
