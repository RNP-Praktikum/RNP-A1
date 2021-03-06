package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;
import descriptors.*;

import descriptors.AbstractDescr;

public class IdentNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String ident;

	public IdentNode() {
		super();
		this.ident = null;
	}

	public IdentNode(String ident, int line, int column) {
		super(line, column);
		this.ident = ident;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		AbstractDescr identD = searchSymbolTable(level, ident);
//		printSymbolTable();
		if (identD instanceof VarDescr) {
				write("PUSHI, " + ((VarDescr)identD).getAddress());
			if (identD.getLevel() == level) {
				write("GETFP");
				write("ADD");
			} else {
				write("PUSHI, " + identD.getLevel());
				write("GETSL");
				write("ADD");
			}
		}
		return identD;
	}

	@Override
	public void print() {
		trace("identNode");
		trace(ident);
		unindent();
		unindent();
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

}
