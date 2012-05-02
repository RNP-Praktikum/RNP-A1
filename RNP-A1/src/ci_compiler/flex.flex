//UserCode
package compiler;

%%
// Options and Declarations
%public
%class Scanner
%line
%column
%caseless 
// %byaccj

//%{
//public static final int LINE_INC = 1;
//public static final int COLUMN_INC = 1;
//
//public static void show(Yytoken token) {
// column = column + COLUMN_INC;
// line = line + LINE_INC;
// System.out.println((token.line + LINE_INC) + "/" + (token.column + COLUMN_INC) + " " + token.name + ": " +token.type);
//}
//%}

operator        =		[*+-/=#()\"<>.,:;] | (:=) | (>=) | (<=) | ([) | (])
digit 	      = 		[0-9]
int 	          =		    {digit}+

blank	     =		[ \t\n\r]
rowComment    =	    "//"[^\r\n]*
letter 	     = 		[A-Za-z]
ident 	     =		{letter}({letter}|{digit})*
begin 	     =  	(BEGIN)
end 	         = 		(END)
of		     = 		(OF) 
then	         = 		(THEN)
do		     =		(DO)
integer        =       (INTEGER)
print          = 		(PRINT)
read          =		(READ)
else	         =		(ELSE)
elsif          =		(ELSIF)
if 		     =		(IF)
while         = 		(WHILE)
repeat        = 		(REPEAT)
until	         = 		(UNTIL)
array	        = 		(ARRAY)
record        = 		(RECORD)
const        =		(CONST)
type	        = 		(TYPE)
var          =		(VAR)
procedure    = 	    (PROCEDURE)
module      =		(MODULE)
literal         = 		{of}|{then}|{do}|{integer}|{print}|{read}|{else}|{elsif}|{if}|{while}|{repeat}|{until}|{array}|{record}|{const}|{type}|{var}|{procedure}|{module}
//identList     =       {ident}("," {ident})+
// test        =       (test)

// TYPE DEF NOCHMAL NACHGUCKEN!!
//arrayType   =       {array}{blank}?"["{blank}?{indexExpression}{blank}?"]"{blank}?{of}{blank}?{typeDef}
//indexExpression =   {int}|{constIdent}
//constIdent  =       {ident}
//typeDef        =       {ident}|{arrayType}|{recordType} 
//fieldList   =       "["{blank}*{identList}{blank}*{type}{blank}*"]"
//recordType  =       {record}{blank}*{fieldList}(";" {fieldList})*{blank}*{end}

%%
//Lexical Rules
{begin}|{end}			{return new Yytoken("Start/End", yytext().toUpperCase(), yyline, yycolumn);}
{literal}		{return new Yytoken("Literal", yytext().toUpperCase(), yyline, yycolumn);}
{blank}			{}
{operator}		{return new Yytoken("Operator", yytext(), yyline, yycolumn);}
{int}			{return new Yytoken("Integer", yytext(), yyline, yycolumn);}
{rowComment}	{return new Yytoken("Comment", yytext(), yyline, yycolumn);}
{ident}			{return new Yytoken("Ident", yytext(), yyline, yycolumn);}
//{identList}     {return new Yytoken("IdentList", yytext(), yyline, yycolumn);}
//{arrayType}     {return new Yytoken("ArrayType", yytext(), yyline, yycolumn);}
//{test}          {return new Yytoken("TEST SOLVED", yytext(), yyline, yycolumn);}
.				{return new Yytoken("ERROR", yytext(), yyline, yycolumn);}
