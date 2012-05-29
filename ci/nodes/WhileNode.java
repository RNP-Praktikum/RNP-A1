package nodes;

import java.util.Map;

import descriptors.AbstractDescr;

public class WhileNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9111016639659348001L;
	AbstractNode condition, doPart;
	
	public WhileNode(AbstractNode condition, AbstractNode doPart, int line, int column) {
		super(line,column);
		this.condition = condition;
		this.doPart = doPart;
	}
	
	public WhileNode() {
		super();
		this.condition = null;
		this.doPart = null;
	}

	public AbstractNode getCondition() {
		return condition;
	}

	public void setCondition(AbstractNode condition) {
		this.condition = condition;
	}

	public AbstractNode getDoPart() {
		return doPart;
	}

	public void setDoPart(AbstractNode doPart) {
		this.doPart = doPart;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("WhileNode ");
		trace("Cond: ");
		condition.print();
		unindent();
		trace("doPart: ");
		if (doPart!= null)
			doPart.print();
		unindent();
		unindent();
	}

}
