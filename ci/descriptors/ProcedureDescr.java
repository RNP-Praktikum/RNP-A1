package descriptors;

import java.util.*;

public class ProcedureDescr extends AbstractDescr {


	private static final long serialVersionUID = 1L;

	String name;
	int lengthparblock, framesize;
	int start;

	List<AbstractDescr> params;

	public ProcedureDescr() {
		lengthparblock = 0;
		framesize = 0;
		start = 0;
		params = null;
		name = "";
	}

	public ProcedureDescr(String fn, int ff, int fl, int fs,
			List<AbstractDescr> fp) {
		name = fn;
		framesize = ff;
		lengthparblock = fl;
		start = fs;
		params = fp;
	}

	public void setName(String fn) {
		name = fn;
	}

	public void setFrameSize(int ff) {
		framesize = ff;
	}

	public void setLengthparblock(int fl) {
		lengthparblock = fl;
	}

	public void setStart(int fs) {
		start = fs;
	}

	public void setParams(List<AbstractDescr> fp) {
		params = fp;
	}

	public String getName() {
		return name;
	}

	public int getFramesize() {
		return framesize;
	}

	public int getLengthparblock() {
		return lengthparblock;
	}

	public int getStart() {
		return start;
	}

	public List<AbstractDescr> getParams() {
		return params;
	}

	public void print() {
		int i = 0;
		trace("ProcdureDescr: " + name + "start: " + start
				+ " lengthparblock: " + lengthparblock + "framesize: "
				+ framesize);
		if (params != null){
		for (i = 0; i < params.size(); i++)
			params.get(i).print();
		}
		unindent();
	}
}
