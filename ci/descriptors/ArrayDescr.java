package descriptors;


public class ArrayDescr extends AbstractDescr {

	private static final long serialVersionUID = 1L;

	int numberelems;
	AbstractDescr basetype;

	public ArrayDescr() {
		numberelems = 0;
		size = 0;
		basetype = null;
	}

	public ArrayDescr(int fn, int fs, AbstractDescr fb) {
		numberelems = fn;
		size = fs;
		basetype = fb;
	}

	public void setNumberelems(int fn) {
		numberelems = fn;
	}

	public void setBasetype(AbstractDescr fb) {
		basetype = fb;
	}

	public int getNumberelems() {
		return numberelems;
	}

	public AbstractDescr getBasetype() {
		return basetype;
	}
	public void print(){
		trace("ArrayDescr: numberelems: " + numberelems +
				" size: " + size);
		basetype.print();
		unindent();
	}
}
