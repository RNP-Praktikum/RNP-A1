package ci_compiler;


import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import nodes.AbstractNode;

import descriptors.AbstractDescr;


public class Compiler {

	
	public static int address = 0;
	public static int level = 0;

	public static FileWriter writer;
	public static int label = 0;
	public static Map<Integer, Map<String, AbstractDescr>> symbolTable = new HashMap<Integer, Map<String, AbstractDescr>>();
	AbstractNode tree;
	
	public Compiler(AbstractNode tree) {
		this.tree = tree;
		
	    	File codetxt = new File("C:/Temp/ziel.txt");
	        
	        try {
				writer = new FileWriter(codetxt);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void compile() {
	//Symboltabelle
	

		tree.compile(symbolTable);
		for(Entry<String, AbstractDescr> entry : symbolTable.get(0).entrySet()) {
			System.out.print(entry.getKey() + ": ");
			entry.getValue().print();
		}

	}
	
	public static int newLabel() {
		return label++;
	}
	
	public static void write(String operation) {
		try {
			System.out.println("Writing " + operation);
			writer.write(operation + System.getProperty("line.separator"));
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
	}
}
