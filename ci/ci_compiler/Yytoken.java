package ci_compiler;

public class Yytoken {

	private final String name, type;
	private final int line, column;
	private static final int LINE_INC = 1;
	private static final int COLUMN_INC = 1;
	
	
	public Yytoken(String type, String name, int line, int column) {
		this.name = name;
		this.type = type;
		this.line = line + LINE_INC;
		this.column = column + COLUMN_INC;
	}


	public String getName() {
		return name;
	}


	public String getType() {
		return type;
	}


	public int getLine() {
		return line;
	}


	public int getColumn() {
		return column;
	}


	@Override
	public String toString() {
		return "Yytoken [" + line +"/" + column + " " + type + ": " +name+ "]";
	}
	
	
	
	
	
}
