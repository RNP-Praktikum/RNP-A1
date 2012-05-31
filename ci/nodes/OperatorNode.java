package nodes;

import java.util.Map;
import descriptors.*;

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
		super(0, 0);
		this.operator = null;
		this.left = null;
		this.right = null;
	}

	public OperatorNode(String op, AbstractNode left, AbstractNode right,
			int line, int column) {
		super(line, column);
		this.operator = op;
		this.left = left;
		this.right = right;
	}

	@Override
	public AbstractDescr compile(
			Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr result = null;
		
			AbstractDescr leftD = left.compile(symbolTable);
			if (leftD instanceof ConstDescr) {
				write("PUSHI, " + ((ConstDescr) leftD).getValue());
			}
		
		
		if (! (right instanceof IdentNode)) {
			AbstractDescr rightD = right.compile(symbolTable);
			//result = rightD;
			if (rightD instanceof ConstDescr) {
				write("PUSHI, " + ((ConstDescr) rightD).getValue());
//				if (left instanceof IdentNode) {
//					result = ((VarDescr)symbolTable.get(level).get(((IdentNode)left).getIdent())).getType();
//				} else {
//					result = ((ArrayDescr)leftD).getBasetype();
//				}
			}
		}

		if (operator.equals("+"))
			write("ADD");
		if (operator.equals("-"))
			write("SUB");
		if (operator.equals("*"))
			write("MUL");
		if (operator.equals("/"))
			write("DIV");
		if (operator.equals("[")) {
			if (left instanceof IdentNode){
			write("PUSHI, "
					+ ((ArrayDescr) ((VarDescr) symbolTable.get(level).get(
							((IdentNode) left).getIdent())).getType())
							.getBasetype().getSize());
			} else {
				if (leftD instanceof ArrayDescr) {
					write("PUSHI, "+ ((ArrayDescr)leftD).getBasetype().getSize());
				} else {
					write("PUSHI, "+ ((ArrayDescr)((VarDescr)leftD).getType()).getBasetype().getSize());
				}
			}
			if (leftD instanceof ArrayDescr){
				result = ((ArrayDescr)leftD).getBasetype();
			} else {
				result = ((ArrayDescr)((VarDescr)leftD).getType()).getBasetype();
			}
			write("MUL");
			write("ADD");
		}
		if (operator.equals(".")) {
			Map<String, AbstractDescr> map = null;
			if (left instanceof IdentNode){
				map = ((RecordDescr) ((VarDescr) symbolTable
					.get(level).get(((IdentNode) left).getIdent())).getType())
					.getRecsymbolTable();
			} else {
				AbstractDescr outerDescr = ((VarDescr)leftD).getType();
				AbstractDescr innerD = ((RecordDescr)outerDescr).getRecsymbolTable().get(((IdentNode)right));
				map = ((RecordDescr)((VarDescr)innerD).getType()).getRecsymbolTable();
			}
			if(right instanceof IdentNode){
				write("PUSHI, "
						+ ((VarDescr) map.get(((IdentNode) right).getIdent()))
								.getAddress());
				} 
				result = map.get(((IdentNode)right).getIdent());
			
			write("ADD");
		}
		// TODO kleienr gleich usw
		// TODO wie constanten handeln
		return result;
	}

	@Override
	public void print() {
		trace("OpNode");
		trace(operator);
		if (left != null) {
			left.print();
		}
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
