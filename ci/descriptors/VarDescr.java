package descriptors;

public class VarDescr extends AbstractDescr {

	AbstractDescr type;
	int address;
	boolean isVarPar;
	
	
	public VarDescr(int level, int address, boolean isVarPar, AbstractDescr type) {
		super(type.getSize(), level);
		this.isVarPar = isVarPar;
		this.type = type;
		this.address = address;
		// TODO Auto-generated constructor stub
	}

	public boolean isVarPar() {
		return isVarPar;
	}

	public void setVarPar(boolean isVarPar) {
		this.isVarPar = isVarPar;
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
		trace("varDescr: isVar: " + isVarPar + " address: " + address +" Size: " + size + " level: " + level);
		type.print();
		unindent();
	}
	

}
