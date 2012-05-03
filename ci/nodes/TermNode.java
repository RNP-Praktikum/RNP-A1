package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class TermNode extends AbstractNode {

	String operator;
	AbstractNode factor1, factor2;
	
	public TermNode() {
		super();
		this.factor1 = null;
		this.factor2 = null;
		this.operator = null;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public AbstractNode getFactor1() {
		return factor1;
	}

	public void setFactor1(AbstractNode factor1) {
		this.factor1 = factor1;
	}

	public AbstractNode getFactor2() {
		return factor2;
	}

	public void setFactor2(AbstractNode factor2) {
		this.factor2 = factor2;
	}

	public TermNode(String operator, AbstractNode factor1, AbstractNode factor2, int line, int column) {
		super(line, column);
		this.factor1 = factor1;
		this.factor2 = factor2;
		this.operator = operator;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("Term ");
		if(operator != null) {
			trace(operator);
		}
		if (factor1 != null)
			factor1.print();
		if (factor2 != null)
			factor2.print();
		unindent();
		if(operator != null) {
		unindent();
		}

	}

}
