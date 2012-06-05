package nodes;

import java.util.List;
import java.util.Map;
import static ci_compiler.Compiler.*;
import descriptors.AbstractDescr;


//import cip.base.CodeGen; //import cip.base.Operator;
//import cip.instructions.*;

public class IfNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	AbstractNode condition;
	AbstractNode thenPart, elsePart;

	public IfNode() {
		super(0,0);
		condition = null;
		thenPart = null;
		elsePart = null;
	};

	public IfNode(AbstractNode fe, AbstractNode fst1,AbstractNode fst2, int line, int column) {
		super(line, column);
		condition = fe;
		thenPart = fst1;
		elsePart = fst2;
	};

	public void setCondition(AbstractNode fe) {
		condition = fe;
	};

	public void setThenPart(AbstractNode fst1) {
		thenPart = fst1;
	};

	public void setElsePart(AbstractNode fst2) {
		elsePart = fst2;
	};

	public AbstractNode getCondition() {
		return condition;
	};

	public AbstractNode getThenPart() {
		return thenPart;
	};

	public AbstractNode getElsePart() {
		return elsePart;
	};

	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		int l1, l2;

		l1 = newLabel();
		l2 = newLabel();
		condition.compile(symbolTable);
		write("BF, " + l1);			//springe zu else wenn false
		if (thenPart != null)
			thenPart.compile(symbolTable);
		write("JMP, " + l2);		//springe zum Ende nach then-Part
		write("LABEL, " + l1);		//else-Part start
		if (elsePart != null)
			elsePart.compile(symbolTable);
		write("LABEL, " + l2);		//Ende
		return null;
	};

	public void print() {
		trace("IfNode ");
		condition.print();
		if (thenPart != null){
			trace("then");
			thenPart.print();
			unindent();}
		if (elsePart != null) {
			trace("else");
			elsePart.print();
			unindent();}
		unindent();
	}


}