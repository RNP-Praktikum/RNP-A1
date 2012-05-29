package descriptors;

public class VarDescr extends AbstractDescr {

	static final int SIZE = 0;
	AbstractDescr type;
	int address;
	
	
	public VarDescr(int level, int address, AbstractDescr type) {
		super(SIZE, level);
		this.type = type;
		this.address = address;
		// TODO Auto-generated constructor stub
	}

	public int getAddress() {
		return address;
	}


	public void setAddress(int address) {
		this.address = address;
	}


	public AbstractDescr getType() {
		return type;
	}


	public void setType(AbstractDescr type) {
		this.type = type;
	}


	@Override
	public void print() {
		trace("varDescr: " + address +" Size: " + size);
		type.print();
		unindent();
	}
	

}
