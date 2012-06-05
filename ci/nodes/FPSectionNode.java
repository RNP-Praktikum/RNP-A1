package nodes;

import java.util.*;
import static ci_compiler.Compiler.*;

import descriptors.*;

public class FPSectionNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	boolean isVar;
	AbstractNode identList, type;

	public FPSectionNode() {
		super();
		this.isVar = false;
		this.identList = null;
		this.type = null;
	}

	public FPSectionNode(boolean isVar, AbstractNode identList, AbstractNode type, int line, int column) {
		super(line, column);
		this.isVar = isVar;
		this.identList = identList;
		this.type = type;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		List<AbstractDescr> list = new LinkedList<AbstractDescr>();
		if (!isVar) {
			AbstractDescr typeD = type.compile(symbolTable);
			for (AbstractNode elem: ((ListNode)identList).getList()) {
				address -= typeD.getSize();
				write("                  put parameter " + level);
				AbstractDescr varD = new VarDescr(level, address, typeD);
				list.add(varD);
				symbolTable.get(level).put(((IdentNode)elem).getIdent(), varD);
				printSymbolTable();
			}
		}
		return new ProcedureDescr("", 0, 0, 0, list);
	}

	@Override
	public void print() {
		if (isVar) {
			trace("VAR");
		}
		if (identList != null) {
			identList.print();
		}
		if (type != null) {
			type.print();
		}
		if(isVar) {
			unindent();
		}
	}

}
