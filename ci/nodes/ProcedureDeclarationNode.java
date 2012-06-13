package nodes;

import static ci_compiler.Compiler.*;

import java.util.Map;

import descriptors.*;

public class ProcedureDeclarationNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AbstractNode procedureHeading, procedureBody, ident;
	
	public ProcedureDeclarationNode(AbstractNode procedureHeading,
			AbstractNode procedureBody, AbstractNode ident, int line, int column) {
		super(line,column);
		this.procedureHeading = procedureHeading;
		this.procedureBody = procedureBody;
		this.ident = ident;
	}

	public ProcedureDeclarationNode() {
		super();
		this.procedureHeading = null;
		this.procedureBody = null;
		this.ident = null;
	}
	public AbstractNode getProcedureHeading() {
		return procedureHeading;
	}

	public void setProcedureHeading(AbstractNode procedureHeading) {
		this.procedureHeading = procedureHeading;
	}

	public AbstractNode getProcedureBody() {
		return procedureBody;
	}

	public void setProcedureBody(AbstractNode procedureBody) {
		this.procedureBody = procedureBody;
	}

	public AbstractNode getIdent() {
		return ident;
	}

	public void setIdent(AbstractNode ident) {
		this.ident = ident;
	}

	@Override
	public AbstractDescr compile(Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		
		AbstractDescr headD = procedureHeading.compile(symbolTable);
		
		level++;
		int savedAddress = address;
		address = 0;
		((ProcedureBodyNode)procedureBody).getDeclarations().compile(symbolTable);
		int frameSize = address;
		int start = newLabel();
		write("LABEL, " + start);
		
		((ProcedureDescr)searchSymbolTable(level,((ProcedureDescr)headD).getName())).setStart(start);
		
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
		((ProcedureBodyNode)procedureBody).getStatementSequence().compile(symbolTable);
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
		AbstractDescr procD = new ProcedureDescr(((ProcedureDescr)headD).getName(), frameSize,((ProcedureDescr)headD).getLengthparblock(), start, ((ProcedureDescr)headD).getParams() );
		symbolTable.get(level).put(((ProcedureDescr)procD).getName(), procD);
		
		//Parameter vom SP abziehen
		write("GETSP");
		write("PUSHI, " + ((ProcedureDescr)procD).getLengthparblock());
		write("SUB");
		write("SETSP");
		
		write("REDUCE, " + (((ProcedureDescr)procD).getFramesize() + ((ProcedureDescr)procD).getLengthparblock() + 3  ));
		write("RET");
		return procD;
	}

	@Override
	public void print() {
		if(procedureHeading != null) {
			procedureHeading.print();
		}
		if(procedureBody != null){
			procedureBody.print();
		}
		if(ident != null){
			ident.print();
		}

	}

}
