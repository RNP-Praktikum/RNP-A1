package nodes;

import java.util.HashMap;
import java.util.List;

import ci_compiler.AbstractDescr;


//import cip.base.CodeGen; //import cip.base.Operator;
//import cip.instructions.*;

public class IfNode extends AbstractNode {

	private static final long serialVersionUID = 1L;

	AbstractNode condition;
	AbstractNode thenPart, elsePart;
	List<AbstractNode> elsifList;

	public IfNode() {
		super(0,0);
		condition = null;
		thenPart = null;
		elsePart = null;
		elsifList = null;
	};

	public IfNode(AbstractNode fe, AbstractNode fst1,List<AbstractNode>elsif, AbstractNode fst2, int line, int column) {
		super(line, column);
		condition = fe;
		thenPart = fst1;
		elsePart = fst2;
		elsifList = elsif;
	};

	public void setCondition(AbstractNode fe) {
		condition = fe;
	};

	public List<AbstractNode> getElsifList() {
		return elsifList;
	}

	public void setElsifList(List<AbstractNode> elsifList) {
		this.elsifList = elsifList;
	}

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

	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
//		int l1, l2;
//
//		trace("IfNode");
//		l1 = CodeGen.newLabel();
//		l2 = CodeGen.newLabel();
//		condition.compile(symbolTable);
//		CodeGen.outInstr(new BranchFalseInstruction(l1));
//		if (thenPart != null)
//			thenPart.compile(symbolTable);
//		CodeGen.outInstr(new JumpInstruction(l2));
//		CodeGen.outInstr(new LabelInstruction(l1));
//		if (elsePart != null)
//			elsePart.compile(symbolTable);
//		CodeGen.outInstr(new LabelInstruction(l2));
//		unindent();
		return null;
	};

	public void print() {
		trace("IfNode ");
		condition.print();
		if (thenPart != null){
			trace("then");
			thenPart.print();
			unindent();}
		if(elsifList != null) {
			trace("elsif");
			for(AbstractNode node : elsifList) {
				
				node.print();
			}
			unindent();
		}
		if (elsePart != null) {
			trace("else");
			elsePart.print();
			unindent();}
		unindent();
	};
}