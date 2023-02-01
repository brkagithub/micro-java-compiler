package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.sun.org.apache.bcel.internal.classfile.CodeException;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.concepts.Scope;

public class CodeGenerator extends VisitorAdaptor {
	private int mainPc;
	
	private boolean returnFound;
	
	//private int destructNumElems = 0;
	
	List<Obj> destructElems = new ArrayList<Obj>();
		
	Obj currArray = null; //unused
	
	Obj loopingObj = null;
	Obj arrObj = null;
	
	Stack<Integer> ifStack = new Stack<Integer>();
	Stack<Integer> andStack = new Stack<Integer>();
	Stack<Integer> orStack = new Stack<Integer>();
	Stack<Integer> whileStack = new Stack<Integer>();
	Stack<Integer> breakStack = new Stack<Integer>();
	Stack<Integer> breakStackCnt = new Stack<Integer>();
	Stack<Integer> forEachStack = new Stack<Integer>();
	Stack<Integer> forEachCmpStack = new Stack<Integer>();
	Stack<Integer> andCntStack = new Stack<Integer>();
	//Stack<Obj> forEachObjects = new Stack<Obj>();
	
	boolean inWhileNotForeach = false;
	
	int andCnt = 0;
	int orCnt = 0;
	
	public int getMainPc(){
		return mainPc;
	}
	
	/*
	public void visit(DesignatorArr designatorArr) {
		Code.load(designatorArr.obj);
		// since obj elem is type Elem, it calls aload and leaves arr[n] on the expr stack
	}
	*/
	
	
	public void visit(WhileKeyword whileKeyword) {
		inWhileNotForeach = true;
		
		breakStackCnt.push(0); //kad udjemo u while gledamo breakCnt za tekuci while - zato push
		whileStack.push(Code.pc); //pamti se adresa tekuceg whilea
	}
	
	public void visit(Continue continueStmt) {
		if(inWhileNotForeach)
			Code.putJump(whileStack.peek()); //na whileStack adresa gde pocinje telo whilea
		else {
			Code.loadConst(1);			
			Code.put(Code.add);
			Code.putJump(forEachStack.peek());
		}
	}
	
	public void visit(Break breakStmt) {
		breakStack.push(Code.pc); //cuvamo pc da bi fixovali posle, Code.pc + 1 je mesto gde ce biti adresa jumpa koj treba fixovati
		Code.putJump(0); // treba fixovati posle
		int cnt = breakStackCnt.pop() + 1; //povecavamo broj breakova koje treba fixovati
		breakStackCnt.push(cnt);

	}
	
	public void visit(WhileMatched whileStmt) {
		Code.putJump(whileStack.pop()); //skace se na pocetak whilea kako bi se opet proverio uslov
		
		int breakCnt = breakStackCnt.pop();
		while(breakCnt > 0) {
			breakCnt--; //??? da li ovo dobro radi za nested while - odg je radi jer u WhileKeyword ima push 0 na breakstackcnt
			Code.fixup(breakStack.pop() + 1); //+1 jer je sledeca instrukcija jmp x, a mi treba da ispravimo x da skace iza whilea
		}
		
		int andCnt = andCntStack.pop();
		
		if(andCnt > 0) {
			Code.fixup(ifStack.pop() + 1);
			// fix the jump that goes after the then
			
			while(andCnt > 0) {
				andCnt--;
				//fix all non-last ands
				Code.fixup(andStack.pop() + 1);
			}
		}
		else {
			if(orCnt == 0) {
				// normal if or normal and or last or
				Code.fixup(ifStack.pop() + 1);
			}
			// otherwise an or was used - already everything is fixed in conditionor
		}
	}
	
	public void visit(WhileUnmatched whileStmt) {
		Code.putJump(whileStack.pop()); //skace se na pocetak whilea kako bi se opet proverio uslov
		
		int breakCnt = breakStackCnt.pop();
		while(breakCnt > 0) {
			breakCnt--;
			Code.fixup(breakStack.pop() + 1); //fixovati sve breakove da skacu iza while tela
		}
		
		int andCnt = andCntStack.pop();
		
		if(andCnt > 0) {
			Code.fixup(ifStack.pop() + 1);
			// fix the jump that goes after the then
			
			while(andCnt > 0) {
				andCnt--;
				//fix all non-last ands
				Code.fixup(andStack.pop() + 1);
			}
		}
		else {
			if(orCnt == 0) {
				// normal if or normal and or last or
				Code.fixup(ifStack.pop() + 1);
			}
			// otherwise an or was used - already everything is fixed
		}
	}
	
	public void visit(ElseHelper elseHelper) {
		int pcToPush = Code.pc;
		Code.putJump(0); //jump to after the if statement
		
		if(andCnt > 0) {
			Code.fixup(ifStack.pop() + 1);
			// fix the jump that goes after the then - can be and or or or normal
			
			while(andCnt > 0) {
				andCnt--;
				//fix all non-last ands that go after the then
				Code.fixup(andStack.pop() + 1);
			}
			ifStack.push(pcToPush);
		}
		else {
			if(orCnt == 0) {
				// normal if or normal and or last or
				Code.fixup(ifStack.pop() + 1);
				ifStack.push(pcToPush);
			}
			// otherwise an or was used - already everything is fixed
		}
		
		
		// adresa posle else-a (unutar)
	}
	
	public void visit(CondFactExpr condition) {		
		Code.put(Code.const_1); //prep stack for eq or neq
		
		if(condition.getParent().getParent() instanceof CondTermAnd) {
			// if its part of and and its not the last and - should be inverse jump
			
			andStack.push(Code.pc);
			andCnt++;
			Code.putFalseJump(Code.eq, 0);
		}
		else {
			if(condition.getParent().getParent().getParent() instanceof ConditionOr) {
				//if its part of or
				//or its the last and and descendant of or
				//and its not the last or
				orStack.push(Code.pc);
				orCnt++;
				Code.putFalseJump(Code.ne, 0); //should inverse
				while(andCnt > 0) {
					andCnt--;
					Code.fixup(andStack.pop() + 1);
				}
			}
			else {
				//normal if
				//last and but not descendant of or (also can be last condition in or)
				//last or
				ifStack.push(Code.pc);
				Code.putFalseJump(Code.eq, 0);
			}
		}
		
	}
	
	public void visit(ConditionIfParenCorrect condition) {
		// all ors except the last one jump after if - to the then part
		while(orCnt > 0) {
			orCnt--;
			Code.fixup(orStack.pop() + 1);
		}
		
		if(condition.getParent() instanceof WhileMatched || condition.getParent() instanceof IfUnmatched | condition.getParent() instanceof WhileUnmatched)
		{
			andCntStack.push(Integer.valueOf(andCnt));
			this.andCnt = 0;
		}
	}
	
	public void visit(CondFactRelop condition) {
		// TO DO probably should check whether we are in an if or while?
		
		// if 
		
		// adresa posle uslova
		
		boolean shouldInverse = false;
		int andToFixCounter = 0;
		
		if(condition.getParent().getParent() instanceof CondTermAnd) {
			// if its part of and and its not the last and - should be inverse jump
			andStack.push(Code.pc); //to fix false jump
			andCnt++;
		}
		else {
			if(condition.getParent().getParent().getParent() instanceof ConditionOr) {
				//if its part of or
				//or its the last and and descendant of or
				//and its not the last or
				shouldInverse = true; //treba invertovati operaciju koju ce invertovati falsejump
				orStack.push(Code.pc); //to fix false jump
				orCnt++;
				while(andCnt > 0) { //fix previous ands to jump to or if failed
					andCnt--; //dodato
					andToFixCounter++;
				}
			}
			else {
				//normal if
				//last and but not descendant of or
				//last or
				ifStack.push(Code.pc);
			}
		}
		
		if(!shouldInverse) { //only pushing inverse in case of non-or
			if(condition.getRelop() instanceof RelopEQ) {
				// first arg is our relop - it will inverse it
				// second arg is adr in (adr-pc+1)  - 0 in the beggining, we will fix it
				Code.putFalseJump(Code.eq, 0);
			}
			else if(condition.getRelop() instanceof RelopNEQ) {
				Code.putFalseJump(Code.ne, 0);
			}
			else if(condition.getRelop() instanceof RelopGT) {
				Code.putFalseJump(Code.gt, 0);
			}
			else if(condition.getRelop() instanceof RelopGTE) {
				Code.putFalseJump(Code.ge, 0);
			}		
			else if(condition.getRelop() instanceof RelopLT) {
				Code.putFalseJump(Code.lt, 0);
			}
			else if(condition.getRelop() instanceof RelopLTE) {
				Code.putFalseJump(Code.le, 0);
			}
		}
		else {
			if(condition.getRelop() instanceof RelopEQ) {
				// first arg is our relop - it will inverse it
				// second arg is adr in (adr-pc+1)  - 0 in the beggining, we will fix it
				Code.putFalseJump(Code.ne, 0);
			}
			else if(condition.getRelop() instanceof RelopNEQ) {
				Code.putFalseJump(Code.eq, 0);
			}
			else if(condition.getRelop() instanceof RelopGT) {
				Code.putFalseJump(Code.le, 0);
			}
			else if(condition.getRelop() instanceof RelopGTE) {
				Code.putFalseJump(Code.lt, 0);
			}		
			else if(condition.getRelop() instanceof RelopLT) {
				Code.putFalseJump(Code.ge, 0);
			}
			else if(condition.getRelop() instanceof RelopLTE) {
				Code.putFalseJump(Code.gt, 0);
			}
		}
		
		while(andToFixCounter > 0) {
			andToFixCounter--;
			//andCnt--;
			Code.fixup(andStack.pop() + 1);
		}
	}
	
	public void visit(IfElseMatched ifStmt) {
		//everything was fixed by else except the jump after else
		Code.fixup(ifStack.pop() + 1);
	}
	
	public void visit(IfElseUnmatched ifStmt) {
		//everything was fixed by else except the jump after else
		Code.fixup(ifStack.pop() + 1);
	}
	
	public void visit(IfUnmatched ifStmt) {
		int andCnt = andCntStack.pop();
		
		if(andCnt > 0) {
			Code.fixup(ifStack.pop() + 1); 
			// fix the jump that goes after the then
			
			while(andCnt > 0) {
				andCnt--;
				//fix all non-last ands to jump after if
				Code.fixup(andStack.pop() + 1);
			}
		}
		else {
			if(orCnt == 0) {
				// normal if or normal and or last or
				Code.fixup(ifStack.pop() + 1);
			}
			// otherwise an or was used - already everything is fixed
		}
	}
	
	public void visit(MethodTypeName methodTypeName) {
		if("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		
		Code.put(Code.enter);
		Code.put(methodTypeName.obj.getLevel()); //formal params
		Code.put(methodTypeName.obj.getLocalSymbols().size()); //fp + vars
	}
	
	public void visit(VoidTypeName methodTypeName) {
		if("main".equalsIgnoreCase(methodTypeName.getMethodName())) {
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		
		Code.put(Code.enter);
		Code.put(methodTypeName.obj.getLevel()); //formal params
		Code.put(methodTypeName.obj.getLocalSymbols().size()); //fp + vars
	}
	
	public void visit(DesignatorMethodCall methodCall) {
		Obj functionObj = methodCall.getDesignatorMethodCallHelper().getDesignator().obj;
		
		if(functionObj.getName().equals("len")) {
			Code.put(Code.arraylength);
			return;
		}
		if(functionObj.getName().equals("chr") || functionObj.getName().equals("ord")) {
			return; //vratice sta treba
		}
		
		int offset = functionObj.getAdr() - Code.pc;
		Code.put(Code.call); 
		Code.put2(offset); // call offset is the format we need
		if(functionObj.getType() != CustomTab.noType) {
			Code.put(Code.pop); // when we call f() which returns - we have to clear from the stack
		}
	}
	
	public void visit(MethodTypeDecl methodDecl){
		if(!returnFound) {
			Code.put(Code.trap);
		}
		
		returnFound = false;
		
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(Return returnStmt) {
		returnFound = true;
	}
	
	public void visit(MethodVoidDecl methodDeclaration) {
		returnFound = false;
		
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(FactorDesignator factorDesignator) {
		Code.load(factorDesignator.getDesignator().obj);
		// load designator on the expr stack - depending on its type it will call the needed load fun
		// example - designatorArr, it will call aload
	}
	
	public void visit(FactorFun factorFun) {
		Obj designatorFunction = factorFun.getDesignator().obj;
		String funName = designatorFunction.getName();
		
		if(funName.equals("len")) {
			Code.put(Code.arraylength);
			return;
		}
		if(funName.equals("chr")) {
	
			return;
		}
		if(funName.equals("ord")) {
			
			return;
		}
		
		int relativeOffset = designatorFunction.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(relativeOffset);
		
		// we need 'call offset' where offset is the distance between method and pc
		// the objFunction will already have its' address set
	}
	
	public void visit(FactorNum factor) {
		Code.loadConst(factor.getN1());
	}
	
	public void visit(FactorChar factor) {
		Code.loadConst(factor.getC1());
	}
	
	public void visit(FactorBool factor) {
		String trueOrFalse = factor.getB1();
		Obj toLoad = CustomTab.insert(Obj.Con, trueOrFalse, CustomTab.booleanType);
		
		if(trueOrFalse.equals("true")) {
			toLoad.setAdr(1); //mark true
		}
		else {
			toLoad.setAdr(0); //mark false
		}
		
		toLoad.setLevel(0);
		Code.load(toLoad);
	}
	
	public void visit(FactorNewBrackets factorArr) {
		Code.put(Code.newarray);
		
		// we also need to tell new array whether it should allocate bytes or words
		// int or char/bools
		
		if(factorArr.struct.getElemType() == CustomTab.intType) {
			Code.put(1);
		}
		else if(factorArr.struct.getElemType() == CustomTab.charType) {
			Code.put(0);
		}
		else if(factorArr.struct.getElemType() == CustomTab.booleanType) {
			Code.put(1);
		}
	}
	
	public void visit(TermListAdd termListAdd) {
		if(termListAdd.getAddop() instanceof Plus) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
		// takes two values from expr stack and puts back the result
	}
	
	public void visit(TermMul termMul) {
		if(termMul.getMulop() instanceof Mul) {
			Code.put(Code.mul);
		}
		else if(termMul.getMulop() instanceof Div) {
			Code.put(Code.div);
		}
		else {
			Code.put(Code.rem);
		}
		// takes two values from expr stack and puts back the result
	}
	
	public void visit(ExprMinus expr) {
		Code.put(Code.neg);
		
		// negate the value on top of the stack
	}
	
	public void visit(Print print) {
		if(print.getExpr().struct == CustomTab.intType || print.getExpr().struct == CustomTab.booleanType) {
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}
		
		// default width for int/bool is 5 and for char is 1
	}
	
	public void visit(PrintWithNum print) {
		Code.loadConst(print.getN2());
		
		if(print.getExpr().struct == CustomTab.intType || print.getExpr().struct == CustomTab.booleanType) {
			Code.put(Code.print);
		}
		else {
			Code.put(Code.bprint);
		}
	}
	
	public void visit(Read read) {
		if(read.getDesignator().obj.getType() == CustomTab.intType || read.getDesignator().obj.getType() == CustomTab.booleanType) {
			Code.put(Code.read);
		}
		else {
			Code.put(Code.bread);
		}
		Code.store(read.getDesignator().obj);
		
		// Code.store will give us instructions needed to store the value on top of the stack into Designator
	}
	
	public void visit(DesignatorArrHelper designator) {
		Code.load(designator.getDesignator().obj);
		// load the array - address, the index will be loaded by expr
	}
	
	public void visit(DesignatorIdent designator) {
		Obj isArray = CustomTab.find(designator.getDesignatorName());
		//Scope currScope = CustomTab.currentScope();
		if(isArray.getType().getKind() == Struct.Array) {
			currArray = isArray; //unused
		}
	}
	
	
	public void visit(DesignatorInc designator) {
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
	}
	
	public void visit(DesignatorDec designator) {
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
		
		Code.put(Code.sub);
		Code.store(designator.getDesignator().obj);
	}
	
	public void visit(DesignatorAssign designator) {
		Code.store(designator.getDesignator().obj);
		// designator and expr are already on the stack
	}
	
	public void visit(DesignatorDeStruct designatorDeStruct) {
		int numElem = designatorDeStruct.getDesignatorList().obj.getLevel();
		//ovo nije duzina niza - valjalo bi da se dobije i proveri
		
		Obj arrObj = designatorDeStruct.getDesignator().obj;
		
		
		Code.loadConst(numElem);
		Code.load(arrObj);
		Code.put(Code.arraylength);
			
		Integer fixJmp = Code.pc;
		
		Code.putFalseJump(Code.gt, 0);
		Code.put(Code.trap);
		Code.fixup(fixJmp + 1);
		
		
		for(int i = numElem-1; i >= 0; i--) {
			if(destructElems.get(i) == null) continue;
			Code.load(arrObj);
			Code.loadConst(i);
			Code.put(Code.aload);
			Code.store(destructElems.get(i));
		}
		
		destructElems = new ArrayList<Obj>();
	}

	public void visit(SingleDesignator designatorList) {
		Obj designatorObj = designatorList.getDesignator().obj;
		destructElems.add(designatorObj);
		//Code.load(designatorObj);
	}
	
	public void visit(NoDesignatorList designatorList) {
		destructElems.add(null);
		//Code.put(-1);
	}
	
	public void visit(DesignatorMandatory designator) {
		Obj designatorObj = designator.getDesignator().obj;
		destructElems.add(designatorObj);
		//Code.load(designatorObj);
	}
	
	public void visit(NoDesignator designator) {
		destructElems.add(null);
		//Code.put(-1);
	}

	public void visit(ForeachMapHelper foreachMap) {
		inWhileNotForeach = false;
		
		breakStackCnt.push(0);
		
		loopingObj = foreachMap.obj;
		
		arrObj = ((Foreach)foreachMap.getParent()).getDesignator().obj;
				
		Code.load(arrObj);
		Code.loadConst(0);
		//load starting index
		// stack -> index arr
		
		forEachStack.push(Code.pc);
		
		Code.put(Code.dup);
		//stack -> index index arr
		Code.load(arrObj);
		//stack -> arr index index arr
		Code.put(Code.arraylength);
		//stack -> arrlen index index arr
		
		forEachCmpStack.push(Code.pc);
		Code.putFalseJump(Code.ne, 0);
		// if index==arrlength we should skip foreach
		// index and arrlength removed from stack
		
		Code.put(Code.dup2);
		//stack -> index arr index arr
		
		if(loopingObj.getType().getElemType() == CustomTab.charType) {
			Code.put(Code.baload);
		}
		else Code.put(Code.aload); //ucita na stek element
		
		//stack-> arrElem index arr
		
		Code.store(loopingObj);
		//store arrElem in obj
		
		//stack -> index arr
	}
	
	
	public void visit(Foreach foreachStmt) {
		
		
		//after the statements
		
		//stack -> index arr
		Code.loadConst(1);
		Code.put(Code.add);
		
		//stack -> index+1 arr
		
		Code.putJump(forEachStack.peek());
		Code.fixup(forEachCmpStack.pop()+1);
		
		//stack -> index+1 arr
		
		int breakCnt = breakStackCnt.pop();
		while(breakCnt > 0) {
			breakCnt--; //??? da li ovo dobro radi za nested while - odg je radi jer u WhileKeyword ima push 0 na breakstackcnt
			Code.fixup(breakStack.pop() + 1); //+1 jer je sledeca instrukcija jmp x, a mi treba da ispravimo x da skace iza whilea
		}
		
		Code.put(Code.pop);
		Code.put(Code.pop);
		
		if(!forEachStack.empty()) forEachStack.pop(); //remove the address where we were jumping
	}
}
