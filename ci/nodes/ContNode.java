package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.AbstractDescr;

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
		node.compile(symbolTable);
		if (! (node instanceof ConstNode)) {
			write("CONT, 1");
		}
		return null;
	}

	@Override
	public void print() {
		trace("CONT");
		node.print();
		unindent();
	}

}
