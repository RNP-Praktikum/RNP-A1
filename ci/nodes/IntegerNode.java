package nodes;

import java.util.Map;

import descriptors.AbstractDescr;
import descriptors.ConstDescr;
import static ci_compiler.Compiler.*;
public class IntegerNode extends AbstractNode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1789097387058308869L;
	int value;

	public IntegerNode() {
		super();
		this.value = 0;
	}

	public IntegerNode(String val, int line, int column) {
		super(line, column);
		this.value = Integer.parseInt(val);
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		return new ConstDescr(level, value);
	}

	@Override
	public void print() {
		trace("IntNode");
		trace(value + "");
		unindent();
		unindent();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
