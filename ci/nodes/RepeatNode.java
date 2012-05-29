package nodes;

import java.util.Map;

import descriptors.AbstractDescr;

public class RepeatNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4281250025530063923L;
	AbstractNode condition, repeatPart;
	
	
	public AbstractNode getCondition() {
		return condition;
	}

	public RepeatNode() {
		super();
		this.condition = null;
		this.repeatPart = null;
	}

	public RepeatNode(AbstractNode condition, AbstractNode repeatPart, int line, int column) {
		super(line, column);
		this.condition = condition;
		this.repeatPart = repeatPart;
	}

	public void setCondition(AbstractNode condition) {
		this.condition = condition;
	}

	public AbstractNode getRepeatPart() {
		return repeatPart;
	}

	public void setRepeatPart(AbstractNode repeatPart) {
		this.repeatPart = repeatPart;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("RepeatNode ");
		if(condition != null) {
		condition.print();
		}
		if (repeatPart!= null)
			repeatPart.print();
		unindent();
	}

}
