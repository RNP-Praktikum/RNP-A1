package descriptors;

public class VarDescr extends AbstractDescr {

	AbstractDescr type;
	int address;
	
	
	public VarDescr(int level, int address, AbstractDescr type) {
		super(type.getSize(), level);
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
		trace("varDescr: address: " + address +" Size: " + size);
		type.print();
		unindent();
	}
	

}
