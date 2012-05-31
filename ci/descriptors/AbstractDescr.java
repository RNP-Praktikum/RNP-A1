package descriptors;

import java.io.Serializable;


/**
 * 
 * @author nilo VLL
 * 
 */
public abstract class AbstractDescr
implements
		Serializable {

	private static final long serialVersionUID = 1L;

	protected int size;
	protected int level;

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public AbstractDescr(int size, int level) {
		this.size = size;
		this.level = level;
	}
	
	public AbstractDescr() {
		this.size = 0;
//		this.level = CodeGen.level;
	}

	public void setSize(int fs) {
		size = fs;
	}

	public int getSize() {
		return size;
	}

	abstract public void print();

	/**
	 * Diese Variable steuert die Einrückung bei der Ausgabe
	 */
	private static String spaces = "";

	public static String getSpaces() {
		return spaces;
	}

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
//		if (Debug.debug > 0) System.out.println(spaces + s);
	}

}