package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class SimpleExpressionNode extends AbstractNode {

	String operator, sign;
	AbstractNode term1, term2;
	
	public SimpleExpressionNode(){
		super(0,0);
		this.operator = null;
		this.term1 = null;
		this.term2 = null;
	}
	
	public SimpleExpressionNode(String sign,String op, AbstractNode term1, AbstractNode term2, int line, int column) {
		super(line, column);
		this.operator = op;
		this.term1 = new SimpleExpressionNode(sign,term1,null,line,column);
		this.term2 = term2;
	}
	
	public SimpleExpressionNode(String op, AbstractNode term1, AbstractNode term2, int line, int column) {
		super(line, column);
		this.operator = op;
		this.term1 = term1;
		this.term2 = term2;
	}
	
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}




	public AbstractNode getTerm1() {
		return term1;
	}




	public void setTerm1(AbstractNode term1) {
		this.term1 = term1;
	}




	public AbstractNode getTerm2() {
		return term2;
	}




	public void setTerm2(AbstractNode term2) {
		this.term2 = term2;
	}




	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("SimpleExpression ");
		if(operator != null) {
			trace(operator);
		}
		if (term1 != null)
			term1.print();
		if (term2 != null)
			term2.print();
		unindent();
		if(operator != null) {
		unindent();
		}

	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
