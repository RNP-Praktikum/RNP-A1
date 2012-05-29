package nodes;

import java.util.Map;

import descriptors.AbstractDescr;
import static ci_compiler.Compiler.*;

public class OperatorNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2450404640425774491L;
	String operator;
	AbstractNode left, right;
	
	public OperatorNode() {
		super(0,0);
		this.operator = null;
		this.left = null;
		this.right = null;
	}

	public OperatorNode(String op, AbstractNode left, AbstractNode right, int line, int column) {
		super(line, column);
		this.operator = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		left.compile(symbolTable);
		right.compile(symbolTable);
		if(operator.equals("+")) write("ADD");
		if(operator.equals("-")) write("SUB");
		if(operator.equals("*")) write("MUL");
		if(operator.equals("/")) write("DIV");
		//TODO kleienr gleich usw
		return null;
	}

	@Override
	public void print() {
		trace("OpNode");
		trace(operator);
		if (left != null){
			left.print();}
		if (right != null)
			right.print();
		unindent();
		unindent();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public AbstractNode getLeft() {
		return left;
	}

	public void setLeft(AbstractNode left) {
		this.left = left;
	}

	public AbstractNode getRight() {
		return right;
	}

	public void setRight(AbstractNode right) {
		this.right = right;
	}

}
