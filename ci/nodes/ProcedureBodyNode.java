package nodes;

import java.util.Map;
import static ci_compiler.Compiler.*;

import descriptors.AbstractDescr;
import descriptors.ProcedureDescr;

public class ProcedureBodyNode extends AbstractNode {
	
	private static final long serialVersionUID = 1L;
	AbstractNode declarations, statementSequence;

	public ProcedureBodyNode() {
		super();
		this.declarations = null;
		this.statementSequence = null;
	}

	public ProcedureBodyNode(AbstractNode declarations, AbstractNode statementSequence, int line, int column) {
		super(line, column);
		this.declarations = declarations;
		this.statementSequence = statementSequence;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {

		level++;
		int savedAddress = address;
		address = 0;
		declarations.compile(symbolTable);
		int frameSize = address;
		int start = newLabel();
		write("LABEL, " + start);
		
		//Register retten
		write("PUSHREG, RK");
		write("PUSHREG, FP");
		write("PUSHI, " + level);
		write("PUSHREG, SL");
		//FP neu setzen
		write("GETSP");
		write("SETFP");
		//SL setzen
		write("GETFP");
		write("PUSHI, " + level);
		write("SETSL");
		//SP setzen
		write("GETSP");
		write("PUSHI, " + frameSize);
		write("ADD");
		write("SETSP");
		//Statements
		statementSequence.compile(symbolTable);
		//Exit
		//SP restaurieren
		write("GETFP");
		write("SETSP");
		//SL, FP und RK restaurieren
		write("PUSHI, "+level);
		write("POPREG, SL");
		write("POPREG, FP");
		write("POPREG, RK");
		
		
		
		
		
		address = savedAddress;
		level--;
		return new ProcedureDescr("", frameSize, 0, start, null);
	}

	public AbstractNode getDeclarations() {
		return declarations;
	}

	public AbstractNode getStatementSequence() {
		return statementSequence;
	}

	@Override
	public void print() {
		trace("ProcedureBody");
		if (declarations != null) {
			declarations.print();
		}
		if (statementSequence != null) {
			statementSequence.print();
		}
		unindent();
	}

}
