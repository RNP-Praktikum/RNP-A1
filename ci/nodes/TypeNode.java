package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.*;

public class TypeNode extends AbstractNode {

	AbstractNode ident, type;
	int line, column;
	
	public TypeNode(AbstractNode ident, AbstractNode type, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.type = type;
	}
	
	public TypeNode() {
		super(0,0);
		this.ident = null;
		this.type = null;
	}

	public AbstractNode getIdent() {
		return ident;
	}

	public void setIdent(AbstractNode ident) {
		this.ident = ident;
	}

	public AbstractNode getType() {
		return type;
	}

	public void setType(AbstractNode type) {
		this.type = type;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr typeD = null;
		if (type instanceof IdentNode){
			if (symbolTable.get(level).containsKey(((IdentNode)type).getIdent())) {
				typeD = symbolTable.get(level).get(((IdentNode)type).getIdent());
			} else {
				typeD = new TypeDescr(1, level, ((IdentNode)ident).getIdent());
			}
		} else {
			typeD = type.compile(symbolTable);
		}

		symbolTable.get(level).put(((IdentNode)ident).getIdent(), typeD);
		return typeD;
	}

	@Override
	public void print() {
		trace("TypeNode");
		ident.print();
		type.print();
		unindent();
	}


}
