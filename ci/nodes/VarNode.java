package nodes;

import java.util.Map;

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
			typeD = new TypeDescr(1, ci_compiler.Compiler.level, ((IdentNode)type).getIdent());
			
		} else {
			// Case of Array and Record
			typeD = type.compile(symbolTable);
			
		}
		 
		if(identList instanceof ListNode) {
			for(AbstractNode elem : ((ListNode)identList).getList()) {
				AbstractDescr varD= new VarDescr(ci_compiler.Compiler.level, ci_compiler.Compiler.address , typeD);
				symbolTable.get(ci_compiler.Compiler.level).put(((IdentNode) elem).getIdent(), varD);
				ci_compiler.Compiler.address += typeD.getSize();
				
			}
		} else {
			AbstractDescr varD= new VarDescr(ci_compiler.Compiler.level, ci_compiler.Compiler.address , typeD);
			symbolTable.get(ci_compiler.Compiler.level).put(((IdentNode) identList).getIdent(), varD);
			ci_compiler.Compiler.address += typeD.getSize();
			varD.print();
		}
		return null;
	}

	@Override
	public void print() {
		trace("VarNode");
		identList.print();
		trace("TypeNode: ");
		type.print();
		unindent();
		unindent();
	}

}
