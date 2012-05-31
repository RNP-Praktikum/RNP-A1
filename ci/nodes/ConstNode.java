package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.AbstractDescr;
import descriptors.ConstDescr;

public class ConstNode extends AbstractNode {

	AbstractNode ident, expression;
	int line, column;
	
	public ConstNode(AbstractNode ident, AbstractNode expression, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.expression = expression;
	}
	
	public ConstNode() {
		super(0,0);
		this.ident = null;
		this.expression = null;
	}

	public AbstractNode getIdent() {
		return ident;
	}

	public void setIdent(AbstractNode ident) {
		this.ident = ident;
	}

	public AbstractNode getExpression() {
		return expression;
	}

	public void setExpression(AbstractNode expression) {
		this.expression = expression;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr constD = expression.compile(symbolTable);
		symbolTable.get(level).put(((IdentNode)ident).getIdent(), constD);
		return constD;
	}

	@Override
	public void print() {
		trace("ConstNode");
		ident.print();
		expression.print();
		unindent();
	}


}
