package descriptors;

import java.util.*;
import java.util.Map.Entry;

public class RecordDescr extends AbstractDescr {

	private static final long serialVersionUID = 1L;

	Map<String, AbstractDescr> recsymbolTable;

	public RecordDescr() {
		size = 0;
		recsymbolTable = new HashMap<String, AbstractDescr>();
	}

	public RecordDescr(int fs, Map<String, AbstractDescr> fr) {
		size = fs;
		recsymbolTable = fr;
	}

	public void setRecSymbolTable(Map<String, AbstractDescr> fr) {
		recsymbolTable = fr;
	}

	public Map<String, AbstractDescr> getRecsymbolTable() {
		return recsymbolTable;
	}
	public void print(){
		trace("RecordDescr: size: " + size + " level: " + level);
		for(Entry<String, AbstractDescr> entry: recsymbolTable.entrySet()){
			System.out.print(AbstractDescr.getSpaces() + entry.getKey() + ": "); entry.getValue().print();
		}
//		Iterator<AbstractDescr> it = recsymbolTable.values().iterator();
//		while (it.hasNext()) {
//			it.next().print();
//		}
		unindent();
	}
}