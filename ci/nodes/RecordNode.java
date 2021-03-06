package nodes;

import java.util.HashMap;
import java.util.Map;

import descriptors.*;
import static ci_compiler.Compiler.*;

public class RecordNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode fieldLists;

	public RecordNode() {
		super();
		this.fieldLists = null;
	}

	public RecordNode(AbstractNode fieldLists, int line, int column) {
		super(line, column);
		this.fieldLists = fieldLists;
		this.line = line;
		this.column = column;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		Map<String, AbstractDescr> map = new HashMap<String,AbstractDescr>();
		int size = 0;
		int address = 0;
		for(AbstractNode fieldList: ((ListNode)fieldLists).getList()) {
			AbstractDescr typeD = null;
			AbstractNode type = ((FieldListNode)fieldList).getType();
			System.out.println("Type: "+type);
			if (type instanceof IdentNode) {
				typeD = searchSymbolTable(level,((IdentNode)type).getIdent());
				System.out.println("TypeD: "+ typeD);
				if (typeD == null) {
					typeD = new TypeDescr(1, level, ((IdentNode)type).getIdent());
				}
			} else {
				typeD = type.compile(symbolTable);
			}
			for(AbstractNode ident : ((ListNode)((FieldListNode)fieldList).getIdentList()).getList()) {
				map.put(((IdentNode)ident).getIdent(), new VarDescr(level, address, false, typeD));
				address += typeD.getSize();
				size += typeD.getSize();
			}
		}
		return new RecordDescr(size, map);
	}

	@Override
	public void print() {
		trace("Record");
		if(fieldLists != null) {
			fieldLists.print();
		}
		unindent();
	}

}
