package rs.ac.bg.etf.pp1;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.ac.bg.etf.pp1.ast.*;

import java.util.ArrayList;

import org.apache.log4j.Logger;


public class SemanticAnalyzer extends VisitorAdaptor {

	boolean errorDetected = false;
	int nVars = 0;
	int whileForeachDepth = 0;
	Obj currentMethod = null;
	int currentConstValue;
	private Struct currentType;
	private Struct currentClass;
	public String currentClassName;
	//private boolean returnFound;
	private int formalParamCounter = 0;
	java.util.List<Struct> currentActualParams = new ArrayList<Struct>();
	int numVarsOnCurrScope = 0;
	
	Logger log = Logger.getLogger(getClass());

	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean passed(){
    	return !errorDetected;
    }
	
	public void visit(ProgName progName){
    	progName.obj = CustomTab.insert(Obj.Prog, progName.getProgName(), CustomTab.noType);
    	CustomTab.openScope();
    }
	
	public void visit(Program program){
    	nVars = CustomTab.currentScope.getnVars();
    	CustomTab.chainLocalSymbols(program.getProgName().obj);
    	CustomTab.closeScope();
    }
	
	public void visit(ConstDecl constDecl){
    	if(CustomTab.currentScope.findSymbol(constDecl.getConstName()) == null) {
    		if(constDecl.getConstValue().struct.assignableTo(currentType)) {
    			constDecl.obj = CustomTab.insert(Obj.Con, constDecl.getConstName(), currentType);
    			constDecl.obj.setAdr(currentConstValue);
    			report_info("Detektovan simbol " + constDecl.getConstName(), constDecl);
    		}
    		else {
    			report_error("Greska: " + constDecl.getConstName() + " nije saglasnog tipa" , constDecl);
    		}
    	}
    	else {
    		report_error("Greska: " + constDecl.getConstName() + " vec postoji u tabeli simbola" , constDecl);
    	}
    }
	
	public void visit(NumVal numVal) {
		numVal.struct = CustomTab.intType;
		currentConstValue = numVal.getNum();
	}
	
	public void visit(CharVal charVal) {
		charVal.struct = CustomTab.charType;
		currentConstValue = charVal.getCh();
	}
	
	public void visit(BoolVal boolVal) {
		boolVal.struct = CustomTab.booleanType; //bool type
		if(boolVal.getBoo().equals("true")) {
			currentConstValue = 1;
		}
		else {
			currentConstValue = 0;
		}
	}
	
	public void visit(VarDecl varDecl) {
		if(CustomTab.currentScope.findSymbol(varDecl.getVarName()) == null) {
			String xd = varDecl.getVarName(); //useless
			Obj tempObj = CustomTab.insert(Obj.Var, varDecl.getVarName(), currentType);
			tempObj.setAdr(numVarsOnCurrScope + formalParamCounter);
			varDecl.obj = tempObj;
			//varDecl.obj.setLevel(currentMethod == null ? 0 : 1);
			numVarsOnCurrScope++;
			nVars++;
		}
		else {
			report_error("Greska: " + varDecl.getVarName() + " vec postoji u tabeli simbola" , varDecl);
		}
	}
	
	public void visit(VarArrDecl varArrDecl) {
		if(CustomTab.currentScope.findSymbol(varArrDecl.getVarName()) == null) {
			Obj tempObj = CustomTab.insert(Obj.Var, varArrDecl.getVarName(), new Struct(Struct.Array, currentType));
			tempObj.setAdr(numVarsOnCurrScope + formalParamCounter);
			varArrDecl.obj = tempObj;
			//varArrDecl.obj.setLevel(currentMethod == null ? 0 : 1);
			numVarsOnCurrScope++;
			nVars++;
		}
		else {
			report_error("Greska: " + varArrDecl.getVarName() + " vec postoji u tabeli simbola" , varArrDecl);
		}
	}
	
	public void visit(ClassName className) {
		if(CustomTab.find(className.getClassName()) == CustomTab.noObj) {
			currentClass = new Struct(Struct.Class);
			currentClassName = className.getClassName();
			className.obj = CustomTab.insert(Obj.Type, currentClassName, currentClass);
			CustomTab.openScope();
		}
		else {
			report_error("Greska: " + className.getClassName() + " vec postoji u tabeli simbola" , className);
		}
	}
	
	public void visit(ClassDecl classDecl) {
		CustomTab.chainLocalSymbols(currentClass);
		currentClass = null;
		currentClassName = "";
		CustomTab.closeScope();
		numVarsOnCurrScope = 0;
	}
	
	public void visit(ClassDeclWMethods classDecl) {
		CustomTab.chainLocalSymbols(currentClass);
		currentClass = null;
		currentClassName = "";
		CustomTab.closeScope();
		numVarsOnCurrScope = 0;
	}
	
	public void visit(ExtendsExists extendsE) {
		if(extendsE.getType().struct == null || extendsE.getType().struct.getKind() != Struct.Class) {
			report_error("Greska: klasa " + currentClassName + " ne moze da se prosiri tipom koji nije postojeca klasa", null);
		}
		else {
			
		}
	}
	
	public void visit(MethodTypeName methodTypeName) {
		currentMethod = CustomTab.insert(Obj.Meth, methodTypeName.getMethodName(), methodTypeName.getType().struct);
		methodTypeName.obj = currentMethod;
		numVarsOnCurrScope = 0;
		CustomTab.openScope();
		//report_info valjda ne mora
	}
	
	public void visit(VoidTypeName voidTypeName) {
		currentMethod = CustomTab.insert(Obj.Meth, voidTypeName.getMethodName(), CustomTab.noType);
		voidTypeName.obj = currentMethod;
		numVarsOnCurrScope = 0;
		CustomTab.openScope();
		//report_info valjda ne mora
	}
	
	public void visit(MethodTypeDecl methodDeclaration) {
		/*if(!returnFound) {
			report_error("Greska: U metodi " + currentMethod.getName() + " fali return ", null);
		}*/
		
		currentMethod.setLevel(formalParamCounter);
		
		CustomTab.chainLocalSymbols(currentMethod);
		CustomTab.closeScope();
		
		
		currentMethod = null;
		//returnFound = false;
		formalParamCounter = 0;
		numVarsOnCurrScope = 0;
	}
	
	public void visit(MethodVoidDecl methodDeclaration) {
		currentMethod.setLevel(formalParamCounter);
		
		CustomTab.chainLocalSymbols(currentMethod);
		CustomTab.closeScope();
		
		currentMethod = null;
		//returnFound = false;
		formalParamCounter = 0;
		numVarsOnCurrScope = 0;
	}

	public void visit(FParamDecl formalParam) {
		Obj fParam = CustomTab.insert(Obj.Var, formalParam.getFParamName(), formalParam.getType().struct);
		fParam.setAdr(formalParamCounter);
		fParam.setLevel(3); //!!! bitno
		fParam.setFpPos(1);
		formalParamCounter++;
	}
	
	public void visit(FParamArrDecl formalParam) {
		Obj fParam = CustomTab.insert(Obj.Var, formalParam.getArrName(), new Struct(Struct.Array, formalParam.getType().struct));
		fParam.setAdr(formalParamCounter);
		fParam.setLevel(3);
		fParam.setFpPos(1);
		formalParamCounter++;
	}
	
	public void visit(ActualParamSingle actualParam) {
		currentActualParams.add(actualParam.getExpr().struct);
	}
	
	public void visit(ActualParamMultiple actualParam) {
		currentActualParams.add(actualParam.getExpr().struct);
	}
	
	public void visit(DesignatorMandatory designator) {
		designator.obj = designator.getDesignator().obj;
		designator.obj.setLevel(1); //brojanje za deStruct
	}
	
	public void visit(NoDesignator designator) {
		designator.obj = CustomTab.noObj;
		designator.obj.setFpPos(-6);
		designator.obj.setLevel(1); //brojanje za deStruct
	}

	public void visit(DesignatorIdent designator) {
		Obj obj = CustomTab.find(designator.getDesignatorName());
    	if(obj == CustomTab.noObj){
    		designator.obj = CustomTab.noObj;
    		report_error("Greska: " + designator.getDesignatorName() + " nije u tabeli simbola ", null);
    	}
    	else {
    		designator.obj = obj;
    		if(designator.obj.getAdr() < formalParamCounter && designator.obj.getLevel() >= 1) {
    			report_info("Detektovan pristup formalnom parametru metode  " + designator.obj.getName(), designator);
    		}
    	}
	}
	
	public void visit(DesignatorArr designator) {
		Designator myDesignator = designator.getDesignatorArrHelper().getDesignator();
		Expr myExpr = designator.getExpr();
		Obj obj = CustomTab.find(myDesignator.obj.getName());
    	if(obj == CustomTab.noObj){
    		designator.obj = CustomTab.noObj;
			report_error("Greska: " + myDesignator.obj.getName() + " nije u tabeli simbola ", designator);
    	}
    	else if(myDesignator.obj.getType().getKind() != Struct.Array) {
    		designator.obj = CustomTab.noObj;
    		report_error("Greska: " + myDesignator.obj.getName() + " mora biti tipa Array, a nije ", designator);
    	}
    	else if(myExpr.struct.getKind() != Struct.Int) {
    		designator.obj = CustomTab.noObj;
    		report_error("Greska: " + myDesignator.obj.getName() + " se ne moze dodeliti Expr koji nije tipa int ", designator);
    	}
    	else {
    		Obj arrElemObj = new Obj(Obj.Elem, myDesignator.obj.getName() + " elem", myDesignator.obj.getType().getElemType());      	
    		arrElemObj.setLevel(myDesignator.obj.getLevel());
    		designator.obj = arrElemObj;
    		report_info("Detektovan pristup nizu  " + designator.obj.getName(), designator);
        	if(designator.obj.getLevel() < formalParamCounter && designator.obj.getLevel() >= 1) {
    			report_info("Detektovan pristup formalnom parametru metode  " + designator.obj.getName(), designator);
    		}
    	}
	}
	
	public void visit(DesignatorDot designator) {
		Designator myDesignator = designator.getDesignator();
		Obj obj = CustomTab.find(myDesignator.obj.getName());
		if(obj == CustomTab.noObj){
			designator.obj = CustomTab.noObj;
			report_error("Greska: " + myDesignator.obj.getName() + " nije u tabeli simbola ", designator);
    	}
		if(myDesignator.obj.getType().getKind() != Struct.Class) {
			designator.obj = CustomTab.noObj;
			report_error("U izrazu Designator = Designator . ident Designator mora biti klasnog tipa", designator);
		}
		else {
			Obj foundInClass = myDesignator.obj.getType().getMembers().searchKey(designator.getDesignatorName());
			if(foundInClass != null){
				designator.obj = foundInClass;
				//nivo c - pronadjena metoda ili atribut klase
			}
			else {
				designator.obj = CustomTab.noObj;
				report_error("Greska: " + myDesignator.obj.getName() + " nema atribut ili metodu s nazivom " + designator.getDesignatorName(), designator);
			}
		}
	}
	
	public void visit(WhileKeyword whileKeyword) {
		whileForeachDepth++;
	}
	
	public void visit(WhileMatched whileStmt) {
		whileForeachDepth--;
	}
	
	public void visit(WhileUnmatched whileStmt) {
		whileForeachDepth--;
	}
	
	public void visit(ForeachMapHelper foreachMap) {
		whileForeachDepth++;
	}
	
	public void visit(Foreach foreachStmt) {
		whileForeachDepth--;
		
		Obj designatorArr = foreachStmt.getDesignator().obj;
		boolean jeNiz = designatorArr.getType().getKind() == 3;
		String identForeach = foreachStmt.getIdentForeach();
		Obj identVar = CustomTab.find(identForeach);
		foreachStmt.getForeachMapHelper().obj = identVar;
		
		if(!jeNiz) {
			report_error("U foreach naredbi " + designatorArr.getName() + " mora biti niz", foreachStmt);
		}	
		else if(identVar == CustomTab.noObj) {
			report_error("U foreach naredbi ident => Statement ident mora biti u tabeli simbola", foreachStmt);
		}
		else if(identVar.getType() != designatorArr.getType().getElemType()) {
			report_error("U foreach naredbi element niza " + designatorArr.getName() + " mora biti istog tipa kao i ident u ident => Statement", foreachStmt);
		}
		else {
			//sve ok
		}
	}
	
	public void visit(Break breakStmt) {
		if(whileForeachDepth == 0) report_error("Greska: break mora biti unutar whilea ili foreacha", breakStmt);
	}
	
	public void visit(Continue continueStmt) {
		if(whileForeachDepth == 0) report_error("Greska: continue mora biti unutar whilea ili foreacha", continueStmt);
	}
	
	public void visit(Return returnStmt) {
		//returnFound = true;
		
		if(returnStmt.getExprOptional().struct.getKind() != currentMethod.getType().getKind()) {
			report_error("Greska: funkcija  " + currentMethod.getName() + " ima drugaciji return tip od onog u return statementu" , returnStmt);
		}
	}
	
	public void visit(Read readStmt) {
		int objectReadKind = readStmt.getDesignator().obj.getKind();
		int objectReadTypeKind = readStmt.getDesignator().obj.getType().getKind();
		
		if((objectReadKind == 1 || objectReadKind == 4 || objectReadKind == 5) && (objectReadTypeKind == 1 || objectReadTypeKind == 2 || objectReadTypeKind == 5)) {
			
		}
		else {
			report_error("Greska: u read izrazu  " + readStmt.getDesignator().obj.getName() + " mora biti promenljiva/element niza/polje tipa int, char ili bool" , readStmt);
		}
	}
	
	public void visit(Print printStmt) {
		int objectPrintTypeKind = printStmt.getExpr().struct.getKind();
		
		if(objectPrintTypeKind == 1 || objectPrintTypeKind == 2 || objectPrintTypeKind == 5) {
			
		}
		else {
			report_error("Greska: u print izrazu print(x) x mora biti tipa int, char ili bool" , printStmt);
		}
	}
	
	public void visit(PrintWithNum printStmt) {
		int objectPrintTypeKind = printStmt.getExpr().struct.getKind();
		
		if(objectPrintTypeKind == 1 || objectPrintTypeKind == 2 || objectPrintTypeKind == 5) {
			
		}
		else {
			report_error("Greska: u print izrazu print(x, ...intParametri) x mora biti tipa int, char ili bool" , printStmt);
		}
	}
	
	public void visit(DesignatorAssign designatorStmt) {
		int designatorKind = designatorStmt.getDesignator().obj.getKind();
		Struct designatorType = designatorStmt.getDesignator().obj.getType();
		Struct exprType = designatorStmt.getDesignatorExprSemi().struct;
		
		if(!(designatorKind == 1 || designatorKind == 4 || designatorKind == 5)) {
			report_error("Greska: u izrazu Designator = Expr " + designatorStmt.getDesignator().obj.getName() + " mora biti promenljiva, element niza ili polje", designatorStmt);
		}
		else if(!designatorType.compatibleWith(exprType)) {
			report_error("Greska: u izrazu Designator = Expr Designator i Expr moraju biti kompatibilnih tipova ", designatorStmt);
		}
	}
	
	public void visit(DesignatorMethodCall methodCall) {
		Obj func = methodCall.getDesignatorMethodCallHelper().getDesignator().obj;
		
		if(func.getName().equals("chr")) {
			report_info("Detektovan poziv funkcije  " + func.getName(), methodCall);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			currentFormalParams.add(CustomTab.intType);
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , methodCall);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(!currentFormalParams.get(i).compatibleWith(currentActualParams.get(i))) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , methodCall);
				}
			}
			currentActualParams = new ArrayList<Struct>();
			return;
		}
		if(func.getName().equals("len")) {
			report_info("Detektovan poziv funkcije  " + func.getName(), methodCall);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			currentFormalParams.add(new Struct(Struct.Array, CustomTab.noType));
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , methodCall);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(currentActualParams.get(i).getKind() != Struct.Array) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , methodCall);
				}
			}
			currentActualParams = new ArrayList<Struct>();
			return;
		}
		if(func.getName().equals("ord")) {
			report_info("Detektovan poziv funkcije  " + func.getName(), methodCall);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			currentFormalParams.add(CustomTab.intType);
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , methodCall);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(!currentFormalParams.get(i).compatibleWith(currentActualParams.get(i))) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , methodCall);
				}
			}
			currentActualParams = new ArrayList<Struct>();
			return;
		}
		if(func.getKind() == Obj.Meth) {
			report_info("Detektovan poziv funkcije  " + func.getName(), methodCall);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			
			for(Obj o : func.getLocalSymbols()) {
				if(o.getFpPos() == 1 && o.getLevel() == 3) {
					currentFormalParams.add(o.getType());
				}
			}
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , methodCall);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(!currentFormalParams.get(i).compatibleWith(currentActualParams.get(i))) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , methodCall);
				}
			}
		}
		else {
			report_info(""+func.getKind(), null);

			report_error("Greska: funkcija  " + func.getName() + " ne postoji" , methodCall);
		}
		
		currentActualParams = new ArrayList<Struct>();
	}
	
	public void visit(DesignatorInc designatorStmt) {
		int designatorKind = designatorStmt.getDesignator().obj.getKind();
		Struct designatorType = designatorStmt.getDesignator().obj.getType();

		if(!(designatorKind == 1 || designatorKind == 4 || designatorKind == 5)) {
			report_error("Greska: u izrazu Designator = Expr " + designatorStmt.getDesignator().obj.getName() + " mora biti promenljiva, element niza ili polje", designatorStmt);
		}
		else if(designatorType != CustomTab.intType) {
			report_error("Greska: U Designator++ Designator mora biti tipa int", designatorStmt);
		}
		designatorStmt.obj = new Obj(designatorKind, designatorStmt.getDesignator().obj.getName(), designatorType);
	}
	
	public void visit(DesignatorDec designatorStmt) {
		int designatorKind = designatorStmt.getDesignator().obj.getKind();
		Struct designatorType = designatorStmt.getDesignator().obj.getType();

		if(!(designatorKind == 1 || designatorKind == 4 || designatorKind == 5)) {
			report_error("Greska: u izrazu Designator = Expr " + designatorStmt.getDesignator().obj.getName() + " mora biti promenljiva, element niza ili polje", designatorStmt);
		}
		else if(designatorType != CustomTab.intType) {
			report_error("Greska: U Designator-- Designator mora biti tipa int", designatorStmt);
		}
		designatorStmt.obj = new Obj(designatorKind, designatorStmt.getDesignator().obj.getName(), designatorType);
	}
	
	public void visit(DesignatorDeStruct designatorStmt) {
		Obj designatorListObj = designatorStmt.getDesignatorList().obj;
		designatorListObj.setLevel(designatorListObj.getLevel());
		
		if(designatorListObj.getFpPos() != -3) {
			//report_error("Greska: U DeStruct izrazu s leve strane Designatori moraju biti promenljive, elementi niza ili polja", designatorStmt);
		}
		else if(designatorStmt.getDesignator().obj.getType().getKind() != 3) {
			report_error("Greska: U DeStruct izrazu s desne strane Designator mora biti niz", designatorStmt);
		}
		else if(!designatorStmt.getDesignatorList().obj.getType().compatibleWith(designatorStmt.getDesignator().obj.getType().getElemType())) {
			if(designatorStmt.getDesignatorList().obj.getType().getKind() != 0)
				report_error("Greska: U DeStruct izrazu s desne strane Designator mora biti niz sa elementima kompatibilnim sa vrednostima sa leve strane", designatorStmt);
		}
		report_info(designatorListObj.getLevel() + " DeStruct-uira se elemenata", null);
	}
	
	public void visit(SingleDesignator designatorList) {
		if(designatorList.getDesignator().obj.getKind() == 1 || designatorList.getDesignator().obj.getKind() == 4 || designatorList.getDesignator().obj.getKind() == 5)
		{
			designatorList.obj = new Obj(designatorList.getDesignator().obj.getKind(), designatorList.getDesignator().obj.getName(), designatorList.getDesignator().obj.getType());
			designatorList.obj.setFpPos(-3); //ok je kind
			designatorList.obj.setLevel(1);
		}
	}
	
	public void visit(MultipleDesignators designatorList) {
		Obj designatorListObj = designatorList.getDesignatorList().obj;
		Obj designatorOptObj = designatorList.getDesignatorOptional().obj;
		
		if(designatorListObj == null) {
			designatorList.obj = new Obj(designatorOptObj.getKind(), "DesignatorList", designatorOptObj.getType());
			designatorList.obj.setFpPos(-3);
			designatorList.obj.setLevel(designatorOptObj.getLevel() + 1);
			return;
		}
		
		if(designatorListObj.getKind() == 1 || designatorListObj.getKind() == 4 || designatorListObj.getKind() == 5)
		{
			if(designatorListObj.getType().getKind() == 0 || designatorOptObj.getFpPos() == -6 || (designatorOptObj.getKind() == 1 || designatorOptObj.getKind() == 4 || designatorOptObj.getKind() == 5)) {
				//ako ne postoji ili ako je var arrElem ili field
				
				//ako ne postoji ili se slaze tip
				
				if(designatorListObj.getType().getKind() == 0) {
					designatorList.obj = new Obj(designatorListObj.getKind(), "DesignatorList", designatorOptObj.getType());
					designatorList.obj.setFpPos(-3);
					designatorList.obj.setLevel(designatorListObj.getLevel() + designatorOptObj.getLevel()); //always += 1
				}
				else if(designatorOptObj.getFpPos() == -6 || (designatorOptObj.getType() == designatorListObj.getType())) {
					
					designatorList.obj = new Obj(designatorListObj.getKind(), "DesignatorList", designatorListObj.getType());
					designatorList.obj.setFpPos(-3);
					designatorList.obj.setLevel(designatorListObj.getLevel() + designatorOptObj.getLevel()); //always += 1
				}
				else {
					designatorList.obj = new Obj(0, "CorruptedDesignatorList", CustomTab.noType);
					report_error("corrupted designator list1", null);
				}
				
			}
			else {
				designatorList.obj = new Obj(0, "CorruptedDesignatorList", CustomTab.noType);
				report_error("corrupted designator list2", null);
			}
			//designatorList.obj.setFpPos(-3); //ok je kind
		}
		else {
			designatorList.obj = new Obj(0, "CorruptedDesignatorList", CustomTab.noType);
			report_error("corrupted designator list3", null);
		}
	}
	
	public void visit(ConditionOr condition) {
		if(condition.getCondition().struct == CustomTab.booleanType && condition.getCondTerm().struct == CustomTab.booleanType) {
			condition.struct = CustomTab.booleanType;
		}
		else {
			condition.struct = CustomTab.noType;
		}
	}
	
	public void visit(ConditionSingle condition) {
		condition.struct = condition.getCondTerm().struct;
	}
	
	public void visit(CondTermSingle condTerm) {
		condTerm.struct = condTerm.getCondFact().struct;
	}
	
	public void visit(CondTermAnd condTerm) {
		if(condTerm.getCondFact().struct == CustomTab.booleanType && condTerm.getCondTerm().struct == CustomTab.booleanType) {
			condTerm.struct = CustomTab.booleanType;
		}
		else {
			condTerm.struct = CustomTab.noType;
		}
	}
	
	public void visit(CondFactExpr condFact) {
		//if(condFact.getExpr().struct.getKind() != 5) {
		//	report_error("U  CondFactExpr tip mora biti boolean", condFact);
		//}
		//else {
			condFact.struct = condFact.getExpr().struct;
		//}
	}
	
	public void visit(CondFactRelop condFact) {
		if(condFact.getExpr().struct.compatibleWith(condFact.getExpr1().struct) && (condFact.getExpr().struct.getKind() == Struct.Array || condFact.getExpr().struct.getKind() == Struct.Array) && (condFact.getRelop() instanceof RelopEQ || condFact.getRelop() instanceof RelopNEQ)) {
			//compatible i class/arr i jeste eq ili neq
			condFact.struct = CustomTab.booleanType;
		}
		else if(condFact.getExpr().struct.compatibleWith(condFact.getExpr1().struct) && (condFact.getExpr().struct.getKind() == Struct.Array || condFact.getExpr().struct.getKind() == Struct.Array)) {
			//compatible i class/arr i nije eq ili neq
			report_error("Ako je u CondFact = Expr Relop Expr Expr klasnog ili nizovnog tipa, mora se koristiti Eq ili Neq kao Relop", condFact);
			condFact.struct = CustomTab.noType;
		}
		else if(condFact.getExpr().struct.compatibleWith(condFact.getExpr1().struct)) {
			//compatible i nije class/arr
			condFact.struct = CustomTab.booleanType;
		}
		else {
			report_error("Tipovi u CondFact = Expr Relop Expr moraju biti kompatibilni", condFact);
		}
	}
	
	public void visit(ExprOptionalExpr expr) {
		expr.struct = expr.getExpr().struct;
	}
	
	public void visit(ExprOptionalEpsilon expr) {
		expr.struct = CustomTab.noType;
	}
	
	public void visit(ExprMinus expr) {
		if(expr.getTerm().struct.getKind() != Struct.Int) {
			report_error("Greska: u izrazu Expr = - TermList TermList mora biti int ", expr);
			expr.struct = CustomTab.noType;
		}
		else {
			expr.struct = CustomTab.intType;
		}
	}
	
	public void visit(ExprPlus expr) {
		expr.struct = expr.getTermList().struct;
	}
	
	public void visit(DesignatorExprAndSemi expr) {
		expr.struct = expr.getExpr().struct;
	}
	
	public void visit(TermListAdd termList) {
		if(termList.getTerm().struct.getKind() != Struct.Int || termList.getTermList().struct.getKind() != Struct.Int) {
			report_error("Greska: u izrazu TermList = TermList Addop Term i TermList i Term moraju biti int ", termList);
			termList.struct = CustomTab.noType;
		}
		else {
			termList.struct = CustomTab.intType;
		}
	}
	
	public void visit(TermListSingle termList) {
		termList.struct = termList.getTerm().struct;
	}
	
	public void visit(TermSingle term) {
		term.struct = term.getFactor().struct;
	}
	
	public void visit(TermMul term) {
		if(term.getTerm().struct.getKind() != Struct.Int || term.getFactor().struct.getKind() != Struct.Int) {
			report_error("Greska: u izrazu Term = Term Mulop Factor i Term i Factor moraju biti int ", term);
			term.struct = CustomTab.noType;
		}
		else {
			term.struct = CustomTab.intType;
		}
	}
	
	public void visit(FactorNum factor) {
		factor.struct = CustomTab.intType;
	}
	
	public void visit(FactorChar factor) {
		factor.struct = CustomTab.charType;
	}
	
	public void visit(FactorBool factor) {
		factor.struct = CustomTab.booleanType;
	}
	
	public void visit(FactorExpr factor) {
		factor.struct = factor.getExpr().struct;
	}
	
	public void visit(FactorNewBrackets factor) {
		if(factor.getExpr().struct.getKind() != Struct.Int) {
			report_error("Greska: u izrazu Factor = new Type[Expr] Expr mora biti tipa int ", factor);
			factor.struct = CustomTab.noType;
		}
		else factor.struct = new Struct(Struct.Array, factor.getType().struct);
	}
	
	public void visit(FactorNewParens factor) {
		if(factor.getType().struct.getKind() != Struct.Class) {
			report_error("Greska: u izrazu Factor = new Type NonOptionalActPars Type mora biti postojeca klasa ", factor);
			factor.struct = CustomTab.noType;
		}
		else factor.struct = new Struct(Struct.Class, currentType.getMembers());
	}
	
	public void visit(FactorFun factor) {
		Obj func = factor.getDesignator().obj;
		
		if(func.getName().equals("chr")) {
			report_info("Detektovan poziv funkcije  " + func.getName(), factor);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			currentFormalParams.add(CustomTab.intType);
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , factor);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(!currentFormalParams.get(i).compatibleWith(currentActualParams.get(i))) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , factor);
				}
			}
			factor.struct = factor.getDesignator().obj.getType();
			currentActualParams = new ArrayList<Struct>();
			return;
		}
		if(func.getName().equals("len")) {
			report_info("Detektovan poziv funkcije  " + func.getName(), factor);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			currentFormalParams.add(new Struct(Struct.Array, CustomTab.noType));
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , factor);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(currentActualParams.get(i).getKind() != Struct.Array) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , factor);
				}
			}
			factor.struct = factor.getDesignator().obj.getType();
			currentActualParams = new ArrayList<Struct>();
			return;
		}
		if(func.getName().equals("ord")) {
			report_info("Detektovan poziv funkcije  " + func.getName(), factor);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			currentFormalParams.add(CustomTab.charType);
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , factor);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(!currentFormalParams.get(i).compatibleWith(currentActualParams.get(i))) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , factor);
				}
			}
			factor.struct = factor.getDesignator().obj.getType();
			currentActualParams = new ArrayList<Struct>();
			return;
		}
		
		if(func.getKind() == Obj.Meth) {
			report_info("Detektovan poziv funkcije  " + func.getName(), factor);
			
			java.util.List<Struct> currentFormalParams = new ArrayList<Struct>();
			
			for(Obj o : func.getLocalSymbols()) {
				if(o.getFpPos() == 1 && o.getLevel() == 3) {
					currentFormalParams.add(o.getType());
				}
			}
			
			if(currentFormalParams.size() != currentActualParams.size()) {
				report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan broj aktuelnih parametara" , factor);
			}
			else {
				boolean checkOkay = true;
				int i;
				for(i = 0; i < currentFormalParams.size(); i++) {
					if(!currentFormalParams.get(i).compatibleWith(currentActualParams.get(i))) {
						checkOkay = false;
						break;
					}
				}
				
				if(!checkOkay) {
					report_error("Greska: funkciji  " + func.getName() + " je zadat pogresan tip aktuelnog parametra na poziciji " + i , factor);
				}
			}
			
		}
		else {
			report_error("Greska: funkcija  " + func.getName() + " ne postoji" , factor);
			factor.struct = CustomTab.noType;
		}
		
		factor.struct = factor.getDesignator().obj.getType();
		currentActualParams = new ArrayList<Struct>();
		/*if(factor.getDesignator().obj.getKind() != Obj.Meth) {
			report_error("Greska: u izrazu Factor = Designator OptionalActPars mora biti metoda ", factor);
			factor.struct = CustomTab.noType;
		}
		else {
			factor.struct = factor.getDesignator().obj.getType();
		}*/
	}
	
	public void visit(FactorDesignator factor) {
		factor.struct = factor.getDesignator().obj.getType();
	}
	
	
	public void visit(Type type) {
		Obj typeInSymbTble = CustomTab.find(type.getTypeName());
		
		if(typeInSymbTble.getKind() != Obj.Type) {
 			report_error("Greska: " + type.getTypeName() + " nije validan tip" , type);
 			//uhvatice i gresku da nije validan tip i da ne postoji u TS jer bi se onda vratio noObj koji je kind Var
		}
		else {
			type.struct = typeInSymbTble.getType();
			currentType = type.struct;
			report_info("Detektovan tip " + typeInSymbTble.getName(), type);
		}
	}
}
