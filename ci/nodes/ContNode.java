package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.*;

public class ContNode extends AbstractNode {
	
	AbstractNode node;

	public ContNode() {
		super();
		node = null;
	}

	public ContNode(AbstractNode node, int line, int column) {
		super(line, column);
		this.node = node;
	}

	@Override
	public AbstractDescr compile(
			Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr descr = node.compile(symbolTable);
		if (! (descr instanceof ConstDescr) ) {
			if (!((descr instanceof VarDescr) && ((VarDescr)descr).isVarPar())) {
				int size = descr.getSize();
				write("CONT, " + size);
			}
		}
		return descr;
	}

	public AbstractNode getNode() {
		return node;
	}

	public void setNode(AbstractNode node) {
		this.node = node;
	}

	@Override
	public void print() {
		trace("CONT");
		node.print();
		unindent();
	}

}
