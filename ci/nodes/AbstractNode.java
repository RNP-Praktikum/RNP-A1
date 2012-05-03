package nodes;

import java.io.Serializable;
import java.util.HashMap;

import ci_compiler.AbstractDescr;

/**
 * Die abstrakte Darstellung eines Knotens im abstrakten Syntaxbaum.
 * 
 * @author VLL + nilo
 * 
 */
public abstract class AbstractNode
        implements
		Serializable {

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	private static final long serialVersionUID = 1L;

	int line, column;
	
	public AbstractNode() {
		line = 0;
		column = 0;
	}

	public AbstractNode(int line, int column) {
		this.line = line;
		this.column = column;
	};

	abstract public AbstractDescr compile(
			HashMap<String, AbstractDescr> symbolTable);

	abstract public void print();

	/**
	 * Diese Variable steuert die Einrückung bei der Ausgabe
	 */
	private static String spaces = "";

	/**
	 * Diese Methode ist eine Darstellungshilfe für den abstrakten Syntaxbaum.
	 * Die Einrückung wird aufgehoben.
	 */
	public void unindent() {
		spaces = spaces.substring(2);
	}

	private void indent() {
		spaces = spaces + "  ";
	}

	/**
	 * Einrückungstiefe um zwei Leerzeichen erhöhen und den String ausgeben.
	 * 
	 * @param fieldName
	 */
	public void trace(String s) {
		this.indent();
 System.out.println(spaces + s);
	}
}
