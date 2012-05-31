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
		if (! (descr instanceof ConstDescr)) {
			write("CONT, 1");
		}
		return descr;
	}

	@Override
	public void print() {
		trace("CONT");
		node.print();
		unindent();
	}

}
