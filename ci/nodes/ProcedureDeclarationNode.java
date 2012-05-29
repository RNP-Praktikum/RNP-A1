package nodes;

import java.util.Map;

import descriptors.AbstractDescr;

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
		// TODO Auto-generated method stub
		return null;
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
