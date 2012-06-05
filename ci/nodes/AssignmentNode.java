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
		int size = 1;
		if (expression instanceof IntegerNode) write("PUSHI, " + ((ConstDescr)exprD).getValue());
		if (ident != null) {
			AbstractDescr descr = searchSymbolTable(level, ((IdentNode)ident).getIdent());
			write("PUSHI, " + ((VarDescr)descr).getAddress());
			if (descr.getLevel() == level) {
				write("GETFP");
				write("ADD");
			} else {
				write("PUSHI, " + descr.getLevel());
				write("GETSL");
				write("ADD");
			}
			size = descr.getSize();
		}
		if (selector != null) {
			AbstractDescr selectD = selector.compile(symbolTable);
			size = selectD.getSize();
		}
		write("ASSIGN, " + size);
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
