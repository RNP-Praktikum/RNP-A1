package ci_compiler;

import java.util.*;

import nodes.*;

public class Parser {

	public final List<Yytoken> tokenList;
	private static Yytoken nexttoken = null;
	private static int labcnt = 0;
	private static Iterator<Yytoken> tokenIt;

	public Parser(List<Yytoken> tokenList) {
		this.tokenList = tokenList;
		tokenIt = tokenList.iterator();
	}

	public void parse() {
		insymbol();
		while (tokenIt.hasNext()){
			module().print();
//			expression().print();
		}
//		term().print();
//		simpleExpression().print();
//		statementSequence();
//		declarations();
		
	}

	public static void compile(String str) {
		System.out.println(str);
	}

	public static void outStr(String str) {
		System.out.println(str + " ");
	}

	public static void outInt(String i) {
		System.out.println(i + " ");
	}

	public static void outOp(String op) {
		System.out.println(op);
	}

	public static void error(String str, int line, int column) {
		System.out.println("Error in "+line+"/"+column+": " + str);
	}

	public static void insymbol() {
		if (tokenIt.hasNext()) {
			nexttoken = tokenIt.next();
		} else {
			nexttoken = new Yytoken("LastToken", "last", -1, -1);
		}
	}
	
	static AbstractNode identList(){
		if (nexttoken.getType().equals("Ident")){
			outStr(nexttoken.getName());
			insymbol();
			if (nexttoken.getName().equals(",")){
				outStr(",");
				insymbol();
				identList();
			}
		} else {
			error("IdentList Error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode arrayType(){
		if (nexttoken.getName().equals("ARRAY")){
			insymbol();
			outStr("ARRAY");
			if (nexttoken.getName().equals("[")){
				insymbol();
				indexExpression();
				if (nexttoken.getName().equals("]")){
					insymbol();
					if (nexttoken.getName().equals("OF")){
						insymbol();
						type();
					} else {
						error("'OF' expected", nexttoken.getLine(), nexttoken.getColumn());
					}
				} else {
					error("']' expected", nexttoken.getLine(), nexttoken.getColumn());
				}
			} else {
				error("'[' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else {
			error("ArrayType Error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode fieldList(){
		if (nexttoken.getType().equals("Ident")){
			identList();
			if (nexttoken.getName().equals(":")){
				insymbol();
				type();
			} else {
				error("':' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else {
			error("FieldList Error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode recordType(){
		if (nexttoken.getName().equals("RECORD")){
			outStr("RECORD");
			insymbol();
			fieldList();
			while (nexttoken.getName().equals(";")){
				outStr(";");
				insymbol();
				fieldList();
			}
			if (nexttoken.getName().equals("END")){
				insymbol();
				outStr("END");
			} else {
				error("'END' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else {
			error("RecordType Error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode type(){
		System.out.println(nexttoken.getName());
		System.out.println(nexttoken.getType());
		if (nexttoken.getType().equals("Ident")){
			outStr(nexttoken.getName());
			insymbol();
		} else if ( nexttoken.getName().equals("ARRAY")){
			arrayType();
		} else if (nexttoken.getName().equals("RECORD")){
			recordType();
		} else {
			error("Type Error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode fpSection(){
		if (nexttoken.getName().equals("VAR")){
			outStr("VAR");
			insymbol();
		} 
		if (nexttoken.getType().equals("Ident")){
			identList();
		} else {
			error("'IDENT' expected", nexttoken.getLine(), nexttoken.getColumn());
		}
		if (nexttoken.getName().equals(":")) {
			outStr(":");
			insymbol();
			type();
		} else {
			error("':' expected", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode formalParameters(){
		fpSection();
		while (nexttoken.getName().equals(";")) {
			outStr(";");
			insymbol();
			fpSection();
		}
		return null;
	}
	
	static AbstractNode procedureHeading(){
		if(nexttoken.getName().equals("PROCEDURE")) {
			outStr("PROCEDURE");
			insymbol();
			if(nexttoken.getType().equals("Ident")) {
				outStr(nexttoken.getName());
				insymbol();
				if(nexttoken.getName().equals("(")) {
					outStr("(");
					insymbol();
					formalParameters();
				} else {
					error("Missing '('", nexttoken.getLine(), nexttoken.getColumn());
				} 
				if(nexttoken.getName().equals(")")) {
					insymbol();
				} else {
					error("Missing ')'", nexttoken.getLine(), nexttoken.getColumn());
				}
			} else {
				error("Ident expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else {
			error("'PROCEDURE' expected", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode procedureBody(){
		declarations();
		if (nexttoken.getName().equals("BEGIN")) {
			insymbol();
			statementSequence();
			if (nexttoken.getName().equals("END")) {
				insymbol();
			} else {
				error("'END' expected", nexttoken.getLine(), nexttoken.getColumn());
			}	
		} else {
			error("'BEGIN' expected", nexttoken.getLine(), nexttoken.getColumn());
		}
		return null;
	}
	
	static AbstractNode procedureDeclaration(){
		AbstractNode heading = null, body = null, ident = null;
		int line = nexttoken.getLine(), column = nexttoken.getColumn();
		heading = procedureHeading();
		if(nexttoken.getName().equals(";")) {
			outStr(";");
			insymbol();
			body = procedureBody();
			if(nexttoken.getType().equals("Ident")) {
				outStr("Ident");
				ident = new IdentNode(nexttoken.getName(), nexttoken.getLine(), nexttoken.getColumn());
				insymbol();
			} else {
				error("'Ident' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		}
		return new ProcedureDeclarationNode(heading, body, ident,line,column);
	}
	
	static AbstractNode declarations(){
		//TODO DECLARATIONS!!!!!
		if(nexttoken.getName().equals("CONST")) {
			outStr("CONST");
			insymbol();
			if (nexttoken.getType().equals("Ident")) {
				outStr(nexttoken.getName());
				insymbol();
				if(nexttoken.getName().equals("=")) {
					outStr("=");
					insymbol();
					expression();
					if(nexttoken.getName().equals(";")) {
						outStr(";");
						insymbol();
						
						while(nexttoken.getType().equals("Ident")) {
							
							outStr("Ident");
							insymbol();
							if (nexttoken.getName().equals("=")) {
								outStr("=");
								insymbol();
								expression();
								if(nexttoken.getName().equals(";")) {
									outStr(";");
									insymbol();
									System.out.println("iM hERE");
								} else {
									error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
								}
							} else {
								error("'=' expected", nexttoken.getLine(), nexttoken.getColumn());
							}	
						}
					} else {
						error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
					}
						
			    } else {
			    	error("'=' expected", nexttoken.getLine(), nexttoken.getColumn());
			    }
			} else {
				error("'Ident' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} 
		if(nexttoken.getName().equals("TYPE")) {
			
			outStr("TYPE");
			insymbol();
			if (nexttoken.getType().equals("Ident")) {
				outStr(nexttoken.getName());
				insymbol();
				if(nexttoken.getName().equals("=")) {
					outStr("=");
					insymbol();
					type();
					if(nexttoken.getName().equals(";")) {
						outStr(";");
						insymbol();
						while(nexttoken.getType().equals("Ident")) {
							outStr(nexttoken.getName());
							insymbol();
							if (nexttoken.getName().equals("=")) {
								outStr("=");
								insymbol();
								type();
								if(nexttoken.getName().equals(";")) {
									outStr(";");
									insymbol();
								} else {
									error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
								}
							} else {
								error("'=' expected", nexttoken.getLine(), nexttoken.getColumn());
							}	
						}
					} else {
						error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
					}
			    } else {
			    	error("'=' expected", nexttoken.getLine(), nexttoken.getColumn());
			    }
			} else {
				error("'Ident' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
			
		}
		if(nexttoken.getName().equals("VAR")) {
			outStr("VAR");
			insymbol();
			identList();
				if(nexttoken.getName().equals(":")) {
					outStr(":");
					insymbol();
					type();
					if(nexttoken.getName().equals(";")) {
						outStr(";");
						insymbol();
						while(nexttoken.getType().equals("Ident")) {
							identList();
							if (nexttoken.getName().equals(":")) {
								outStr(":");
								insymbol();
								type();
								if(nexttoken.getName().equals(";")) {
									outStr(";");
									insymbol();
								} else {
									error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
								}
							} else {
								error("'=' expected", nexttoken.getLine(), nexttoken.getColumn());
							}	
						}
					} else {
						error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
					}
			    } else {
			    	error("'=' expected", nexttoken.getLine(), nexttoken.getColumn());
			    }
			
			
		}
		//Procedure ist first token in ProcedureDeclaration -> ProcedureHeading -> Procedure
		//Direkt Access because of the while Procedure
		while(nexttoken.getName().equals("PROCEDURE")) {
			procedureDeclaration();
			if(nexttoken.getName().equals(";")) {
				insymbol();
			} else {
				error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		}
		return null;
	}
	
	static AbstractNode module(){
		AbstractNode ident = null, decl = null, statementSeq = null; 
		int line = 0, column = 0;
		String identName = "";
		if(nexttoken.getName().equals("MODULE")) {
			outStr("MODULE");
			insymbol();
			if(nexttoken.getType().equals("Ident")) {
				outStr(identName = nexttoken.getName());
				ident = new IdentNode(nexttoken.getName(), nexttoken.getLine(), nexttoken.getColumn());
				insymbol();
				if(nexttoken.getName().equals(";")) {
					outStr(";");
					insymbol();
					decl = declarations();
					if(nexttoken.getName().equals("BEGIN")) {
						outStr("BEGIN MODULE");
						insymbol();
						statementSeq = statementSequence();
						if(nexttoken.getName().equals("END")) {
							outStr("END MODULE");
							insymbol();
							if(nexttoken.getType().equals("Ident") && identName.equals(nexttoken.getName())) {
								outStr(nexttoken.getName());
								insymbol();
								if(nexttoken.getName().equals(".")) {
									outStr(".");
									insymbol();
								} else {
									error("'.' expected", nexttoken.getLine(), nexttoken.getColumn());
								}
							} else {
								error("'Ident' expected", nexttoken.getLine(), nexttoken.getColumn());
							}
						} else {
							error("'END' expected", nexttoken.getLine(), nexttoken.getColumn());
						}
					} else {
						error("'BEGIN' expected", nexttoken.getLine(), nexttoken.getColumn());
					}
				} else {
					error("';' expected", nexttoken.getLine(), nexttoken.getColumn());
				}
			} else {
				error("'Ident' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		}
		return new ModuleNode(ident,decl,statementSeq,line,column);
	}

	static AbstractNode assignment(Yytoken ident) {
		//Ident bereits in statement() abgearbeitet
		AbstractNode selectorNode = selector();
		AbstractNode expressionNode = null;
		int line = 0, column = 0;
		if (nexttoken.getName().equals(":=")) {
			line = nexttoken.getLine();
			column = nexttoken.getColumn();
			outStr(":=");
			insymbol();
			expressionNode = expression();
		} else {
			error("':=' expected", nexttoken.getLine(), nexttoken.getColumn());
		}
		return new AssignmentNode(new IdentNode(ident.getName(), ident.getLine(), ident.getColumn()), selectorNode, expressionNode, line, column);
	}

	static AbstractNode actualParameters() {
		//TODO epxression Nodes????
		List<AbstractNode> params = new LinkedList<AbstractNode>();
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn();
		params.add(expression());
		while(nexttoken.getName().equals(",")){
			insymbol();
			params.add(expression());
		}
		return new ListNode(params, line, column);
	}

	static AbstractNode procedureCall(Yytoken ident) {
		AbstractNode actualParameters = null;
		AbstractNode identNode = new IdentNode(ident.getName(), ident.getLine(), ident.getColumn());
		// Ident bereits in statement() abgearbeitet
		if (nexttoken.getName().equals("(")) {
			insymbol();
			if (nexttoken.getName().equals(")")) {
				insymbol();
			} else {
				actualParameters = actualParameters();
				if (nexttoken.getName().equals(")")) {
					insymbol();
				} else {
					error("')' expected", nexttoken.getLine(), nexttoken.getColumn());
				}
			}
		} else {
			error("ProcedureCall error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return new ProcedureCallNode(identNode, actualParameters, ident.getLine(), ident.getColumn());
	}

	static AbstractNode ifStatement() {
		AbstractNode conditionNode = null, thenNode = null, elseNode = null;
		List<AbstractNode> elsifList = new LinkedList<AbstractNode>();
		int l = 0, c = 0;
		if (nexttoken.getName().equals("IF")) {
			outStr("IF");
			insymbol();
			l = nexttoken.getLine(); 
			c = nexttoken.getColumn();
			conditionNode = expression();
			if (nexttoken.getName().equals("THEN")) {
				outStr("THEN");
				insymbol();
				thenNode = statementSequence();
				while (nexttoken.getName().equals("ELSIF")) {
					AbstractNode cond1 = null, then1 = null;
					outStr("ELSIF");
					insymbol();
					int column = nexttoken.getColumn(), line = nexttoken.getLine();
					cond1 = expression();
					if (nexttoken.getName().equals("THEN")) {
						outStr("THEN");
						insymbol();
						then1 = statementSequence();
					} else {
						error("'THEN' expected", nexttoken.getLine(), nexttoken.getColumn());
					}
					elsifList.add(new IfNode(cond1, then1, null, null, line, column));
				}
				if (nexttoken.getName().equals("ELSE")) {
					outStr("ELSE");
					insymbol();
					elseNode = statementSequence();
				}
				if (nexttoken.getName().equals("END")) {
					outStr("END");
					insymbol();
				} else {
					error("'END' expected", nexttoken.getLine(), nexttoken.getColumn());
				}
			} else {
				error("'THEN' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else {
			error("IfStatement error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return new IfNode(conditionNode, thenNode, elsifList, elseNode, l,c);
	}

	static AbstractNode whileStatement() {
		AbstractNode doPart=null, cond=null;
		int line = 0, column = 0;
		if (nexttoken.getName().equals("WHILE")) {
			outStr("WHILE");
			insymbol();
			line = nexttoken.getLine();
			column = nexttoken.getColumn();
			cond = expression();
			if (nexttoken.getName().equals("DO")) {
				outStr("DO");
				insymbol();
				doPart = statementSequence();
				if (nexttoken.getName().equals("END")) {
					outStr("END");
					insymbol();
				} else {
					error("'END' expected", nexttoken.getLine(), nexttoken.getColumn());
				}
			} else {
				error("'DO' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else {
			error("WhileStatementError", nexttoken.getLine(), nexttoken.getColumn());
		}
		return new WhileNode(cond, doPart,line, column);
	}

	static AbstractNode repeatStatement() {
		AbstractNode cond=null, repeatPart=null;
		int line = 0, column = 0;
		if (nexttoken.getName().equals("REPEAT")) {
			outStr("REPEAT");
			insymbol();
			line = nexttoken.getLine();
			column = nexttoken.getColumn();
			repeatPart = statementSequence();
			if (nexttoken.getName().equals("UNTIL")) {
				outStr("UNTIL");
				insymbol();
				cond = expression();
			} else {
				error("'UNTIL' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else {
			error("RepeatStatement error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return new RepeatNode(cond, repeatPart,line,column);
	}

	static AbstractNode statement(){
		AbstractNode statement = null;
		int line = 0, column = 0;
		if (nexttoken.getType().equals("Ident")){
			Yytoken ident = nexttoken;
			line = nexttoken.getLine();
			column = nexttoken.getColumn();
			outStr(ident.getName());
			insymbol();
			if (nexttoken.getName().equals("(")){
				statement = procedureCall(ident);
			} else {
				statement = assignment(ident);
			}
		} else if (nexttoken.getName().equals("IF")){
			statement = ifStatement();
		} else if (nexttoken.getName().equals("PRINT")){
			outStr("PRINT");
			insymbol();
			statement = expression();
		} else if (nexttoken.getName().equals("WHILE")){
			statement = whileStatement();
		} else if (nexttoken.getName().equals("REPEAT")){
			statement = repeatStatement();
		}
		return new StatementNode(statement, line, column);
	}

	static AbstractNode statementSequence() {
		List<AbstractNode> statementList = new LinkedList<AbstractNode>();
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn();
		statementList.add(statement());
		while (nexttoken.getName().equals(";")){
			outStr(";");
			insymbol();
			statementList.add(statement());
		}
		return new StatementSequenceNode(statementList, line, column);
	}

	static AbstractNode string() {
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn();
		String str = null;
		if (nexttoken.getName().charAt(0) == '"') {
			insymbol();
			str = "";
			while (nexttoken.getName().charAt(0) != '"') {
				str += nexttoken.getName();
				insymbol();
			}
			outStr(str = '"' + str + '"');
			insymbol();
		}
		return new StringNode(str, line, column);
	}

	static OperatorNode selector() {
		OperatorNode node = null;
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn();
		if (nexttoken.getName().equals(".")) {
			insymbol();
			if (nexttoken.getType().equals("Ident")) {
				outStr("." + nexttoken.getName());
				node = new OperatorNode(".", null, new IdentNode(nexttoken.getName(),line, column), line, column);
				insymbol();
			} else {
				error("identifier expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else if (nexttoken.getName().equals("[")) {
			insymbol();
			node = new OperatorNode("[", null, expression(), line, column);
			if (nexttoken.getName().equals("]")) {
				insymbol();
			} else {
				error("']' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		}
		return node;
	}

	static AbstractNode factor() {
		AbstractNode node = null;;
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn();
		if (nexttoken.getName().equals("(")) {
			insymbol();
			node = expression();
			if (nexttoken.getName().equals(")")) {
				insymbol();
			} else {
				error("')' expected", nexttoken.getLine(), nexttoken.getColumn());
			}
		} else if (nexttoken.getType().equals("Ident")) {
			node = new IdentNode(nexttoken.getName(), line, column);
			outStr(nexttoken.getName());
			insymbol();
			OperatorNode selectorNode = selector();
			if (selectorNode != null) {
				selectorNode.setLeft(node);
				node = selectorNode;
			}
		} else if (nexttoken.getType().equals("Integer")) {
			outInt(nexttoken.getName());
			node = new IntegerNode(nexttoken.getName(), line, column); 
			insymbol();
		} else if (nexttoken.getName().equals("READ")) {
			node = read();
		} else if (nexttoken.getName().charAt(0) == '"') {
			node = string();
		}
		return node;
	}

	static AbstractNode read() {
		AbstractNode node = null;
		if (nexttoken.getName().equals("READ")) {
			insymbol();
			outStr("READ ");
			if (nexttoken.getName().charAt(0) == '"') {
				node = prompt();
			}
		}
		return node;
	}

	static AbstractNode prompt() {
		return string();
	}

	static AbstractNode term() {
		AbstractNode left = null, right = null;
		String operator = null;
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn();
		left = factor();
		if (nexttoken.getName().equals("*") || nexttoken.getName().equals("/")) {
			outOp((operator = nexttoken.getName()) + " ");
			insymbol();
			right = term();
		}
		if (operator != null) {
			return new OperatorNode(operator, left, right, line, column);
		} else {
			return left;
		}
	}

	static AbstractNode simpleExpression() {
		String sign = null;
		String operator = null;
		AbstractNode left = null;
		AbstractNode node = null;
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn(); 
		
		if (nexttoken.getName().equals("-")) {
			sign = nexttoken.getName();
			insymbol();
		}
		left = term();
		if (sign != null && sign.equals("-")) {
			outOp("- ");
			left = new OperatorNode("-", null, left, line, column);
		}
		
		while (nexttoken.getName().equals("-") || nexttoken.getName().equals("+")) {
			outOp(nexttoken.getName() + " ");
			operator = nexttoken.getName();
			insymbol();
			if (node == null){
				node = new OperatorNode(operator, left, term(), line, column);
			} else {
				node = new OperatorNode(operator, node, term(), line, column);
			}
		}
		if (node == null){
			return left;
		} else {
			return node;
		}
	}

	static AbstractNode expression() {
		AbstractNode right = null;
		AbstractNode left = null;
		String operator = null;
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn(); 
		left = simpleExpression();
		if (nexttoken.getName().equals("=") || nexttoken.getName().equals("#")
				|| nexttoken.getName().equals("<")
				|| nexttoken.getName().equals("<=")
				|| nexttoken.getName().equals(">")
				|| nexttoken.getName().equals(">=")) {
			outOp(nexttoken.getName() + " ");
			operator = nexttoken.getName();
			insymbol();
			right = simpleExpression();
		}
		if (operator != null){
			return new OperatorNode(operator, left, right, line, column);
		} else {
			return left;
		}
	}

	static AbstractNode indexExpression() {
		AbstractNode node = null;
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn(); 
		if (nexttoken.getType().equals("Integer")) {
			outInt(nexttoken.getName());
			node = new IntegerNode(nexttoken.getName(), line, column);
			insymbol();
		} else {
			node = constIdent();
		}
		return node;
	}

	static AbstractNode constIdent() {
		AbstractNode node = null;
		int line = nexttoken.getLine();
		int column = nexttoken.getColumn(); 
		if (nexttoken.getType().equals("Ident")) {
			outStr(nexttoken.getName());
			node = new IdentNode(nexttoken.getName(), line, column);
			insymbol();
		} else {
			error("ConstIdent Error", nexttoken.getLine(), nexttoken.getColumn());
		}
		return node;
	}

}
