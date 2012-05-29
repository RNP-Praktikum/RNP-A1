package descriptors;

public class TypeDescr extends AbstractDescr {

	String type;
	
	
	
	public TypeDescr() {
		super();
		this.type = "";
	}



	public TypeDescr(int size, int level, String type) {
		super(size, level);
		this.type = type;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	@Override
	public void print() {
		// SimpleTypeDescr: integer size: 1 level: 0
		trace("TypeDescr: " + type + " size: " + size + " level: " +level);
		unindent();

	}

}
