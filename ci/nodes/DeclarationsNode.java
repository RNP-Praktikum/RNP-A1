package nodes;

import java.util.*;
import static ci_compiler.Compiler.*;

import descriptors.AbstractDescr;

public class DeclarationsNode extends AbstractNode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<AbstractNode> constList, typeList, varList;
	List<AbstractNode> procedureDeclaration;

	public DeclarationsNode(List<AbstractNode> constList,
			List<AbstractNode> typeList, List<AbstractNode> varList,
			List<AbstractNode> procedureDeclaration, int line, int column) {
		super(line, column);
		this.constList = constList;
		this.typeList = typeList;
		this.varList = varList;
		this.procedureDeclaration = procedureDeclaration;
	}

	public DeclarationsNode() {
		super();
		this.constList = null;
		this.typeList = null;
		this.varList = null;
		this.procedureDeclaration = null;
	}

	public List<AbstractNode> getProcedureDeclaration() {
		return procedureDeclaration;
	}

	public void setProcedureDeclaration(List<AbstractNode> procedureDeclaration) {
		this.procedureDeclaration = procedureDeclaration;
	}

	public List<AbstractNode> getConstList() {
		return constList;
	}

	public void setConstList(List<AbstractNode> constList) {
		this.constList = constList;
	}

	public List<AbstractNode> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<AbstractNode> typeList) {
		this.typeList = typeList;
	}

	public List<AbstractNode> getVarList() {
		return varList;
	}

	public void setVarList(List<AbstractNode> varList) {
		this.varList = varList;
	}

	@Override
	public AbstractDescr compile(
			Map<Integer, Map<String, AbstractDescr>> symbolTable) {
		if (symbolTable.get(level) == null) {
			symbolTable.put(level, new HashMap<String, AbstractDescr>());
		}
		for (AbstractNode elem : constList) {
			elem.compile(symbolTable);
		}
		for (AbstractNode elem : typeList) {
			elem.compile(symbolTable);
		}
		for (AbstractNode elem : varList) {
			elem.compile(symbolTable);
		}
		for (AbstractNode elem : procedureDeclaration) {
			elem.compile(symbolTable);
		}
		return null;
	}

	@Override
	public void print() {

		if (!constList.isEmpty()) {
			trace("CONST");
			for (AbstractNode node : constList) {
				node.print();
			}
			unindent();
		}
		if (!typeList.isEmpty()) {
			trace("TYPE");
			for (AbstractNode node : typeList) {
				node.print();
			}
			unindent();
		}
		if (!varList.isEmpty()) {
			trace("VAR");
			for (AbstractNode node : varList) {
				node.print();
			}
			unindent();
		}
		if (!procedureDeclaration.isEmpty()) {
			trace("PROC DECLARATIONS");
			for (AbstractNode node : procedureDeclaration) {
				node.print();
			}
			unindent();
		}

	}

}
