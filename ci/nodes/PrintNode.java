package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.*;

public class PrintNode extends AbstractNode {
	
	AbstractNode expression;

	public PrintNode() {
		super(0,0);
		this.expression = null;
	}

	public PrintNode(AbstractNode expression, int line, int column) {
		super(line, column);
		this.expression = expression;
	}

	public AbstractNode getExpression() {
		return expression;
	}

	public void setExpression(AbstractNode expression) {
		this.expression = expression;
	}

	@Override
	public AbstractDescr compile(
			Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr descr = expression.compile(symbolTable);
		if (descr instanceof ConstDescr) write("PUSHI, "+ ((ConstDescr)descr).getValue());
		write("PRINT");
		return null;
	}

	@Override
	public void print() {
		trace("PrintNode");
			expression.print();
		unindent();
	}

}
