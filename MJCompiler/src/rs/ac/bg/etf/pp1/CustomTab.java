package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CustomTab extends Tab {
	public static final Struct booleanType = new Struct(5);
	
	public static void customInit() {
		init();
		currentScope.addToLocals(new Obj(Obj.Type, "bool", booleanType));
	}
}
