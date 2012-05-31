package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.AbstractDescr;
import descriptors.TypeDescr;
import descriptors.VarDescr;


public class VarNode extends AbstractNode {

	AbstractNode identList, type;
	int line, column;
	
	public VarNode(AbstractNode ident, AbstractNode type, int line, int column) {
		super(line, column);
		this.identList = ident;
		this.type = type;
	}
	
	public VarNode() {
		super(0,0);
		this.identList = null;
		this.type = null;
	}

	public AbstractNode getIdentList() {
		return identList;
	}

	public void setIdentList(AbstractNode ident) {
		this.identList = ident;
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
		if(type instanceof IdentNode) {
			typeD = searchSymbolTable(level, ((IdentNode)type).getIdent());
			
		} else {
			// Case of Array and Record
			typeD = type.compile(symbolTable);
			
		}
		 
		if(identList instanceof ListNode) {
			for(AbstractNode elem : ((ListNode)identList).getList()) {
				AbstractDescr varD= new VarDescr(level, address , typeD);
				symbolTable.get(level).put(((IdentNode) elem).getIdent(), varD);
				address += typeD.getSize();
				
			}
		} else {
			AbstractDescr varD= new VarDescr(level, address , typeD);
			symbolTable.get(level).put(((IdentNode) identList).getIdent(), varD);
			address += typeD.getSize();
			varD.print();
		}
		return null;
	}

	@Override
	public void print() {
		trace("VarNode");
		identList.print();
		trace("TypeNode: ");
		if (type != null) type.print();
		unindent();
		unindent();
	}

}
