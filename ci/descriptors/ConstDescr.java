package descriptors;

public class ConstDescr extends AbstractDescr {

	int value;
	
	public ConstDescr(int level, int value) {
		super(0, level);
		this.value = value;
	}
	
	@Override
	public void print() {
		trace("ConstDescr: " + value + " level: " + level);
		unindent();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
