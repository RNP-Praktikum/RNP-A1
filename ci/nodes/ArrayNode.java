package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.*;

public class ArrayNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode indexExpression, type;
	
	
	public ArrayNode(AbstractNode indexExpression, AbstractNode type,
			int line, int column) {
		super(line,column);
		this.indexExpression = indexExpression;
		this.type = type;
	}
	
	public ArrayNode() {
		super();
		this.indexExpression = null;
		this.type = null;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr indexD = indexExpression.compile(symbolTable);
		AbstractDescr typeD = null;
		if (type instanceof IdentNode) {
			typeD = new TypeDescr(1, level, ((IdentNode)type).getIdent());
		} else {
			typeD = type.compile(symbolTable);
		}
		int numberOfElems = ((ConstDescr)indexD).getValue();
		int size = numberOfElems * typeD.getSize();
		return new ArrayDescr(numberOfElems, size, typeD );
	}

	@Override
	public void print() {
		trace("Array");
		if (indexExpression != null) {
			indexExpression.print();
		}
		if (type != null) {
			type.print();
		}
		unindent();
	}

}
