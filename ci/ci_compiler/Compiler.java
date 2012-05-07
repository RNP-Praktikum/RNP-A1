package ci_compiler;

import java.util.*;

public class Compiler {

	/**
	 * @param args
	 */
	public static void main(String argv[]) {
		Yytoken token;
		List<Yytoken> tokenList = new LinkedList<Yytoken>();
		if (argv.length == 0) {
			System.out.println("Usage : java Scanner <inputfile>");
		} else {
			for (int i = 0; i < argv.length; i++) {
				Scanner scanner = null;
				System.out.println("Scanner:");
				try {
					scanner = new Scanner(new java.io.FileReader(argv[i]));
//					 scanner = new Scanner( new java.io.FileReader("t2.txt"));
					// );

					do {
						token = scanner.yylex();
						if (token != null) {
							tokenList.add(token);
							System.out.println(token);

						}
					} while (token != null);
				} catch (java.io.FileNotFoundException e) {
					System.out.println("File not found : \"" + argv[i] + "\"");
				} catch (java.io.IOException e) {
					System.out.println("IO error scanning file \"" + argv[i]
							+ "\"");
					System.out.println(e);
				} catch (Exception e) {
					System.out.println("Unexpected exception:");
					e.printStackTrace();
				}
			}
		}
		System.out.println("Parser:");
		Parser parser = new Parser(tokenList);
		System.out.println("AbstractTree:");
		parser.parse();
	}


}
