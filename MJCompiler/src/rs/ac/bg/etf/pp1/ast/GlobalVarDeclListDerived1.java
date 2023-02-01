// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class GlobalVarDeclListDerived1 extends GlobalVarDeclList {

    private Type Type;
    private GlobalVarDecls GlobalVarDecls;

    public GlobalVarDeclListDerived1 (Type Type, GlobalVarDecls GlobalVarDecls) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.GlobalVarDecls=GlobalVarDecls;
        if(GlobalVarDecls!=null) GlobalVarDecls.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public GlobalVarDecls getGlobalVarDecls() {
        return GlobalVarDecls;
    }

    public void setGlobalVarDecls(GlobalVarDecls GlobalVarDecls) {
        this.GlobalVarDecls=GlobalVarDecls;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(GlobalVarDecls!=null) GlobalVarDecls.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(GlobalVarDecls!=null) GlobalVarDecls.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(GlobalVarDecls!=null) GlobalVarDecls.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalVarDeclListDerived1(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalVarDecls!=null)
            buffer.append(GlobalVarDecls.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalVarDeclListDerived1]");
        return buffer.toString();
    }
}
