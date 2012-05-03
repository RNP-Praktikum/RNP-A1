package nodes;

import java.util.HashMap;

import ci_compiler.AbstractDescr;

public class FactorNode extends AbstractNode {

	String ident, integer;
	AbstractNode selector,read, expression, string;
	
	public FactorNode() {
		super();
		this.ident = null;
		this.string = null;
		this.integer = null;
		this.selector = null;
		this.read = null;
		this.expression = null;
	}

	public FactorNode(String ident, AbstractNode string, String integer,
			AbstractNode selector, AbstractNode read, AbstractNode expression, int line, int column) {
		super(line, column);
		this.ident = ident;
		this.string = string;
		this.integer = integer;
		this.selector = selector;
		this.read = read;
		this.expression = expression;
	}


	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public AbstractNode getString() {
		return string;
	}

	public void setString(AbstractNode string) {
		this.string = string;
	}

	public String getInteger() {
		return integer;
	}

	public void setInteger(String integer) {
		this.integer = integer;
	}

	public AbstractNode getSelector() {
		return selector;
	}

	public void setSelector(AbstractNode selector) {
		this.selector = selector;
	}

	public AbstractNode getRead() {
		return read;
	}

	public void setRead(AbstractNode read) {
		this.read = read;
	}

	public AbstractNode getExpression() {
		return expression;
	}

	public void setExpression(AbstractNode expression) {
		this.expression = expression;
	}

	@Override
	public AbstractDescr compile(HashMap<String, AbstractDescr> symbolTable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void print() {
		trace("Factor");
		if(ident != null) {
			trace(ident);
			if(selector != null) {
				selector.print();
			}
			unindent();
		} else if(integer != null) {
			trace(integer);
			unindent();
		} else if(string != null) {
			string.print();
		} else if(read != null) {
			read.print();
		} else if(expression != null) {
			expression.print();
		}
		unindent();
	}

}
