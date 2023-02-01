// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class MultipleGlobalVarDeclarations extends GlobalVarDecls {

    private GlobalVarDeclComma GlobalVarDeclComma;
    private GlobalVarDecls GlobalVarDecls;

    public MultipleGlobalVarDeclarations (GlobalVarDeclComma GlobalVarDeclComma, GlobalVarDecls GlobalVarDecls) {
        this.GlobalVarDeclComma=GlobalVarDeclComma;
        if(GlobalVarDeclComma!=null) GlobalVarDeclComma.setParent(this);
        this.GlobalVarDecls=GlobalVarDecls;
        if(GlobalVarDecls!=null) GlobalVarDecls.setParent(this);
    }

    public GlobalVarDeclComma getGlobalVarDeclComma() {
        return GlobalVarDeclComma;
    }

    public void setGlobalVarDeclComma(GlobalVarDeclComma GlobalVarDeclComma) {
        this.GlobalVarDeclComma=GlobalVarDeclComma;
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
        if(GlobalVarDeclComma!=null) GlobalVarDeclComma.accept(visitor);
        if(GlobalVarDecls!=null) GlobalVarDecls.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarDeclComma!=null) GlobalVarDeclComma.traverseTopDown(visitor);
        if(GlobalVarDecls!=null) GlobalVarDecls.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarDeclComma!=null) GlobalVarDeclComma.traverseBottomUp(visitor);
        if(GlobalVarDecls!=null) GlobalVarDecls.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleGlobalVarDeclarations(\n");

        if(GlobalVarDeclComma!=null)
            buffer.append(GlobalVarDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalVarDecls!=null)
            buffer.append(GlobalVarDecls.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleGlobalVarDeclarations]");
        return buffer.toString();
    }
}
