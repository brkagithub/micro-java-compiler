// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class SingleVarGlobalDeclaration extends GlobalVarDecls {

    private GlobalVarDeclEnd GlobalVarDeclEnd;

    public SingleVarGlobalDeclaration (GlobalVarDeclEnd GlobalVarDeclEnd) {
        this.GlobalVarDeclEnd=GlobalVarDeclEnd;
        if(GlobalVarDeclEnd!=null) GlobalVarDeclEnd.setParent(this);
    }

    public GlobalVarDeclEnd getGlobalVarDeclEnd() {
        return GlobalVarDeclEnd;
    }

    public void setGlobalVarDeclEnd(GlobalVarDeclEnd GlobalVarDeclEnd) {
        this.GlobalVarDeclEnd=GlobalVarDeclEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarDeclEnd!=null) GlobalVarDeclEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarDeclEnd!=null) GlobalVarDeclEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarDeclEnd!=null) GlobalVarDeclEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleVarGlobalDeclaration(\n");

        if(GlobalVarDeclEnd!=null)
            buffer.append(GlobalVarDeclEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleVarGlobalDeclaration]");
        return buffer.toString();
    }
}
