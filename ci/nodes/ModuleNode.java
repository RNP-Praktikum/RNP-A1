package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class ModuleNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1414274589538425495L;
	AbstractNode ident, declarations, statementSequence;
	//TODO Module Ident das gleiche wie end Ident????
	
	
	
	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	public ModuleNode(AbstractNode ident, AbstractNode declarations,
			AbstractNode statementSequence, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.declarations = declarations;
		this.statementSequence = statementSequence;
	}
	
	public ModuleNode(){
		super();
		this.ident = null;
		this.declarations = null;
		this.statementSequence = null;
	}

	@Override
	public void print() {
		trace("Module ");
		trace("Declarations");
			if(ident != null) {ident.print();}
			if(declarations != null) {declarations.print();}
		trace("Statements");
			if(statementSequence != null){statementSequence.print();}
			unindent();


	}

}
