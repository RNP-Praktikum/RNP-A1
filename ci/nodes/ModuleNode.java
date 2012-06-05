package nodes;

import java.util.HashMap;
import java.util.Map;

import descriptors.*;
import static ci_compiler.Compiler.*;

public class ModuleNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1414274589538425495L;
	AbstractNode ident, declarations, statementSequence;

	// TODO Module Ident das gleiche wie end Ident????

	@Override
	public AbstractDescr compile(
			Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		symbolTable.put(ci_compiler.Compiler.level,
				new HashMap<String, AbstractDescr>());
		symbolTable.get(level).put("int", new TypeDescr(1, level, "int"));
		write("PUSHS, " + ((IdentNode) ident).getIdent());
		int startLabel = newLabel();
		write("JMP, " + startLabel);
		declarations.compile(symbolTable);
		write("LABEL, " + startLabel);
		write("PUSHI, " + (address));
		write("SETSP");
		statementSequence.compile(symbolTable);
		write("STOP");
		return null;
	}

	public ModuleNode(AbstractNode ident, AbstractNode declarations,
			AbstractNode statementSequence, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.declarations = declarations;
		this.statementSequence = statementSequence;
	}

	public ModuleNode() {
		super();
		this.ident = null;
		this.declarations = null;
		this.statementSequence = null;
	}

	@Override
	public void print() {
		trace("Module ");
		if (ident != null) {
			ident.print();
		}
		trace("Declarations");

		if (declarations != null) {
			declarations.print();
		}
		unindent();
		if (statementSequence != null) {
			statementSequence.print();
		}
		unindent();

	}

}
