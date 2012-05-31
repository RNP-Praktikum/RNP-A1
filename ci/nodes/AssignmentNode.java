package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.AbstractDescr;
import descriptors.ConstDescr;
import descriptors.VarDescr;

public class AssignmentNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractNode ident, selector, expression;
	
	
	public AssignmentNode(AbstractNode ident, AbstractNode selector,
			AbstractNode expression, int line, int column) {
		super(line,column);
		this.ident = ident;
		this.selector = selector;
		this.expression = expression;
	}
	
	public AssignmentNode() {
		super();
		this.ident = null;
		this.selector = null;
		this.expression = null;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr exprD = expression.compile(symbolTable);
		if (expression instanceof IntegerNode) write("PUSHI, " + ((ConstDescr)exprD).getValue());
		if (ident != null) {
			write("PUSHI, " + ((VarDescr)symbolTable.get(level).get(((IdentNode)ident).getIdent())).getAddress());
		}
		if (selector != null) {
			selector.compile(symbolTable);
		}
		write("ASSIGN, 1");
		return null;
	}

	@Override
	public void print() {
		trace(":=");
		if(ident != null) {
			ident.print();
		}
		if(selector != null) {
			selector.print();
		}
		if(expression != null) {
			expression.print();
		}
		unindent();
	}

}
