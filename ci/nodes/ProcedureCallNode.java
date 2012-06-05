package nodes;

import java.util.*;
import static ci_compiler.Compiler.*;

import descriptors.*;

public class ProcedureCallNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractNode ident;
	AbstractNode parameterList;
	
	public ProcedureCallNode(AbstractNode ident, AbstractNode expressionList, int line, int column) {
		super(line,column);
		this.ident = ident;
		this.parameterList = expressionList;
	}

	public ProcedureCallNode() {
		super();
		this.ident = null;
		this.parameterList = null;
		
	}

	public AbstractNode getIdent() {
		return ident;
	}

	public void setIdent(AbstractNode ident) {
		this.ident = ident;
	}

	public AbstractNode getExpressionList() {
		return parameterList;
	}

	public void setExpressionList(AbstractNode expressionList) {
		this.parameterList = expressionList;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		List<AbstractNode> list = ((ListNode)parameterList).getList();
		AbstractDescr procD = searchSymbolTable(level, ((IdentNode)ident).getIdent());
		write("INIT, " + (((ProcedureDescr)procD).getFramesize() + ((ProcedureDescr)procD).getLengthparblock() + 4  ));
		for(ListIterator<AbstractNode> it = list.listIterator(list.size());it.hasPrevious();){
			AbstractNode elem = it.previous();
			AbstractDescr elemD = elem.compile(symbolTable);
			if (elemD instanceof ConstDescr)
				write("PUSHI, " + ((ConstDescr)elemD).getValue());
			
			int size = elemD.getSize();
			write("GETSP");
			write("ASSIGN, " + size);
			write("GETSP");
			write("PUSHI, " + size);
			write("ADD");
			write("SETSP");
		}
	
		
		write("CALL, " + ((ProcedureDescr)procD).getStart());
		return procD;
	}

	@Override
	public void print() {
		trace("(");
		if(ident != null) {
			ident.print();
		}
		if(parameterList != null) {
		parameterList.print();
		}
		unindent();
	}
}
