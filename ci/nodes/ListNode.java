package nodes;

import java.util.List;
import java.util.Map;

import descriptors.AbstractDescr;

public class ListNode extends AbstractNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7140857430155399928L;
	List<AbstractNode> list;
	
	public ListNode(List<AbstractNode> list, int line, int column) {
		super(line,column);
		this.list = list;
	}
	
	public ListNode() {
		super();
		this.list = null;
	}

	public List<AbstractNode> getList() {
		return list;
	}

	public void setList(List<AbstractNode> list) {
		this.list = list;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		for(AbstractNode node: list) {
			node.print();
		}
	}

}
