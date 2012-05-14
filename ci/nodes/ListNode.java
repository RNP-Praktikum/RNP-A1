package nodes;

import java.util.HashMap;
import java.util.List;

import ci_compiler.AbstractDescr;

public class ListNode extends AbstractNode {
	
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
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
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
