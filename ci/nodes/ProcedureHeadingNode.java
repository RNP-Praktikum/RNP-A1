package nodes;

import java.util.*;
import static ci_compiler.Compiler.*;

import descriptors.AbstractDescr;
import descriptors.ProcedureDescr;

public class ProcedureHeadingNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode ident, formalParameters;

	public ProcedureHeadingNode() {
		super();
		this.ident = null;
		this.formalParameters = null;
	}

	public ProcedureHeadingNode(AbstractNode ident, AbstractNode formalParameters, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.formalParameters = formalParameters;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		int savedAddress= address;
		address = -3;
		level++;
		List<AbstractDescr> params = new LinkedList<AbstractDescr>();
		if (symbolTable.get(level) == null) symbolTable.put(level, new HashMap<String, AbstractDescr>());
		if (formalParameters != null) {
			for (AbstractNode elem: ((ListNode)formalParameters).getList()){
				AbstractDescr descr = elem.compile(symbolTable);
				params.addAll(((ProcedureDescr)descr).getParams());
			}
		}
		int lengthParBlock = Math.abs(address)-3;
		address = savedAddress;
		level--;
		return new ProcedureDescr(((IdentNode)ident).getIdent(), 0, lengthParBlock,0, params);
	}

	@Override
	public void print() {
		trace("ProcedureHeading");
		if (ident != null) {
			ident.print();
		}
		if (formalParameters != null) {
			formalParameters.print();
		}
		unindent();
	}

}
