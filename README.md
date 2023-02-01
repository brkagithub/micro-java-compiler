# MicroJava compiler for the Programming compilers course
Compiler for MicroJava, a programming language that is a simplified version of Java 'invented' for the purpose of this project. It supports variables, global functions, arrays, loops (foreach, while), conditions (if, else) and some language-specific operators - for example the unpack operator which unpacks an array and assigns the elements to variables.

<pre>
int intArray[], a, b;
intArray = new int[3];
intArray[0] = 1;
intArray[1] = 2;
intArray[2] = 3;
[a, ,b] = intArray; //a = 1, b = 3</pre>

Code is generated after the 4 phases of compiling are complete:

## Lexical analysis (JFlex)

The lexer recognizes keywords, numerical values, identifiers and even code comments by using the JFlex library.

### Example lexer output
<pre>INFO  14:53:27,145 - #14 program
INFO  14:53:27,145 - #47 test301
INFO  14:53:27,145 - #47 int
INFO  14:53:27,146 - #47 a
INFO  14:53:27,146 - #13 ,
INFO  14:53:27,146 - #47 b
INFO  14:53:27,146 - #8 ;
INFO  14:53:27,146 - #12 {
INFO  14:53:27,147 - #16 void
INFO  14:53:27,147 - #47 main
INFO  14:53:27,147 - #11 (
...etc...</pre>


## Syntactic analysis (cup_v10k)

Syntactic analysis uses the AST-CUP generator that can produce a syntactic tree from the LALR(1) grammar and the input program. In the 3rd and 4th phase we will traverse the tree and collect information needed to generate our code. This face also includes recovery from error - ex. the compiler will skip formal parameters that include syntactic errors. During the construction of the grammar I encountered LALR(1) state conflicts and had to solve them by transforming the grammar.


### Part of the grammar describing function calls and format parameters
<pre>MethodDeclaration ::= (MethodTypeDecl) MethodTypeName LPAREN FormalParsOpt:formalPars VarDeclListsOptional:methodVars LBRACE StatementList RBRACE
					  |
					  (MethodVoidDecl) VoidTypeName LPAREN FormalParsOpt:formalPars VarDeclListsOptional:methodVars LBRACE StatementList RBRACE
					  ;

MethodTypeName ::= (MethodTypeName) Type:methodType IDENT:methodName
				   ;
				   
VoidTypeName ::= (VoidTypeName) VOID IDENT:methodName
				 ;

FormalParsOpt ::= (FormPars) FormalPars 
			    |
			    (NoFormPars) RPAREN /* only close the parentheses */
			    ;
			     
FormalPars ::= (MultipleFormalPars) FormalParamComma FormalPars
			   |
			   (SingleFormalPar) FormalParamDecl RPAREN
			   |
			   (SingleFormalParError) error RPAREN:l {: parser.report_error("Izvrsen oporavak do ) za formalne parametre u liniji " + lleft, null); :}
			   ;

FormalParamComma ::= FormalParamDecl COMMA
				   |
				   (FormalParamCommaError) error COMMA:l {: parser.report_error("Izvrsen oporavak do , za formalne parametre u liniji " + lleft, null); :}
				   ;

FormalParamDecl ::= (FParamDecl) Type:fParamType IDENT:fParamName 
				    |
				    (FParamArrDecl) Type:arrType IDENT:arrName LBRACKET RBRACKET</pre>

### Syntactic tree output
<pre>ProgName(
   test301
  ) [ProgName]
  VarList(
    NoList(
    ) [NoList]
    GlobalVarDeclListDerived1(
      Type(
       int
      ) [Type]
      MultipleGlobalVarDeclarations(
        GlobalVarDecComma(
          VarDecl(
           a
          ) [VarDecl]
        ) [GlobalVarDecComma]
        SingleVarGlobalDeclaration(
          GlobalVarDeclSemi(
            VarDecl(
             b
            ) [VarDecl]
          ) [GlobalVarDeclSemi]
        ) [SingleVarGlobalDeclaration]
      ) [MultipleGlobalVarDeclarations]
    ) [GlobalVarDeclListDerived1]
  ) [VarList]</pre>


## Semantic analysis

Semantic analysis is done by traversing (POSTORDER) the syntactic tree. For each vertex of the tree a visit method is called, and information gets inserted into the symbol table (symboltable.jar). We use the symbol table in each visit method to add new information and report errors in case a part of code is semantically incorrect (example - using an undefined variable). The result from this phase is a completed symbol table that will be used in the 4th phase, and info and error output.

### Code examined
<pre>program test301

	int a, b;
	
{
	void main()	
	{
		a = 5;
		b = a + 3;
		print(a+b);
			
	}
}
</pre>
### Example#1 output - int type detected and symbol table
<pre>INFO  14:53:27,155 - ===================================
INFO  14:53:27,157 - Detektovan tip int na liniji 5
=====================SYMBOL TABLE DUMP=========================
Type int: int, -1, -1 
Type char: char, -1, -1 
Con eol: char, 10, 0 
Con null: Class [], 0, 0 
Meth chr: char, 0, 1 
   Var i: int, 0, 1 

Meth ord: int, 0, 1 
   Var ch: char, 0, 1 

Meth len: int, 0, 1 
   Var arr: Arr of notype, 0, 1 

Type bool: , -1, -1 
Prog test301: notype, 0, 1 
   Var a: int, 0, 0 
   Var b: int, 1, 0 
   Meth main: notype, 0, 0 </pre>
  
  ## Code generation
  
  Code generation does a second postorder traversal of the tree if the previous phases did not report any errors. In the visit methods we generate code and manipulate the expression stack - for example a simple `a = a + b` instruction will include pushing a and b on the expression stack, as well as an add instruction that will pop them and push the addition to the stack. Since the result of the add operation needs to be loaded in a, we also need to push the load instruction to the stack which will pop the addition from the stack leaving the stack empty just like it was before this instruction.

### Visit method of the increment (++) part of the grammar

<pre>public void visit(DesignatorInc designator) {
		Obj designatorObj = designator.getDesignator().obj;
		
		if(designatorObj.getKind() == Obj.Var) {
			Code.load(designator.getDesignator().obj);
			Code.loadConst(1);
		}
		else if(designatorObj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
			Code.load(designator.getDesignator().obj); //aload
			Code.loadConst(1);
			// arrayAdr and index will already be on the stack from DesignatorArr
			// we need to double them - one pair for load and one pair for store
		}
		
		Code.put(Code.add);
		Code.store(designator.getDesignator().obj);
	}</pre>

You can notice that we are using the information from the first traversal (semantic phase) to check whether the Designator is a variable or an element - since the stack manipulation we have to do is different for those two cases.

### Code generated for the Example#1 with explanations
<pre>disasm:
     [java] codeSize=26
     [java] dataSize=2
     [java] mainPC=0
     [java] 0: enter 0 0 //enter main, load params, move base pointer and stack pointer
     [java] 3: const_5
     [java] 4: putstatic 0 //load 5 to a
     [java] 7: getstatic 0 //push a to the stack
     [java] 10: const_3 //push 3 to add it to a
     [java] 11: add //add them - leaves 'a+3' result on the stack
     [java] 12: putstatic 1 //load the result to b and pop it
     [java] 15: getstatic 0 //push a
     [java] 18: getstatic 1 //push b
     [java] 21: add //add them
     [java] 22: const_5 //'width' of the print - will be popped by print instruction
     [java] 23: print //expects stack -> width val and prints val on that width
     [java] 24: exit //exit and return are standard microjava instructions that change the base pointer and pop the function parameters
     [java] 25: return</pre>
     
### It's also possible to debug by viewing how the stack changes between instructions
<pre>[java]   pos: instruction operands
     [java]      | expressionstack
     [java] -----------------------------
     [java]     0: enter       0 0 
     [java]      | 
     [java]     3: const_5     
     [java]      | 5 
     [java]     4: putstatic   0 
     [java]      | 
     [java]     7: getstatic   0 
     [java]      | 5 
     [java]    10: const_3     
     [java]      | 5 3 
     [java]    11: add         
     [java]      | 8 
     [java]    12: putstatic   1 
     [java]      | 
     [java]    15: getstatic   0 
     [java]      | 5 
     [java]    18: getstatic   1 
     [java]      | 5 8 
     [java]    21: add         
     [java]      | 13 
     [java]    22: const_5     
     [java]      | 13 5 
     [java]    23: print          13
     [java]      | 
     [java]    24: exit        
     [java]      | 
     [java]    25: return    </pre>
     
And finally - we can run this with the run_java.bat file to see whether the program really prints the correct value.
<pre>C:\Users\Marko Brkic\Desktop\faks7semestar\pp1\projekat\pp1lab.templateAST\workspace\MJCompiler>java -cp ./test;./lib/mj-runtime-1.1.jar rs.etf.pp1.mj.runtime.Run test/program.obj
   13
Completion took 0 ms</pre>

It really works :O! There are much more complex test examples in MJCompiler/test folder. If you want to try the project out - you have to run 'compile' from build.xml to generate the grammar, then run the test/MJParserTest.java to run the 3rd and 4th phase and enter the above command to run the program in your console. Thanks for reading!
