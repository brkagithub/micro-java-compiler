// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class MethodVoidDecl extends MethodDeclaration {

    private VoidTypeName VoidTypeName;
    private FormalParsOpt FormalParsOpt;
    private VarDeclListsOptional VarDeclListsOptional;
    private StatementList StatementList;

    public MethodVoidDecl (VoidTypeName VoidTypeName, FormalParsOpt FormalParsOpt, VarDeclListsOptional VarDeclListsOptional, StatementList StatementList) {
        this.VoidTypeName=VoidTypeName;
        if(VoidTypeName!=null) VoidTypeName.setParent(this);
        this.FormalParsOpt=FormalParsOpt;
        if(FormalParsOpt!=null) FormalParsOpt.setParent(this);
        this.VarDeclListsOptional=VarDeclListsOptional;
        if(VarDeclListsOptional!=null) VarDeclListsOptional.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public VoidTypeName getVoidTypeName() {
        return VoidTypeName;
    }

    public void setVoidTypeName(VoidTypeName VoidTypeName) {
        this.VoidTypeName=VoidTypeName;
    }

    public FormalParsOpt getFormalParsOpt() {
        return FormalParsOpt;
    }

    public void setFormalParsOpt(FormalParsOpt FormalParsOpt) {
        this.FormalParsOpt=FormalParsOpt;
    }

    public VarDeclListsOptional getVarDeclListsOptional() {
        return VarDeclListsOptional;
    }

    public void setVarDeclListsOptional(VarDeclListsOptional VarDeclListsOptional) {
        this.VarDeclListsOptional=VarDeclListsOptional;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VoidTypeName!=null) VoidTypeName.accept(visitor);
        if(FormalParsOpt!=null) FormalParsOpt.accept(visitor);
        if(VarDeclListsOptional!=null) VarDeclListsOptional.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VoidTypeName!=null) VoidTypeName.traverseTopDown(visitor);
        if(FormalParsOpt!=null) FormalParsOpt.traverseTopDown(visitor);
        if(VarDeclListsOptional!=null) VarDeclListsOptional.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VoidTypeName!=null) VoidTypeName.traverseBottomUp(visitor);
        if(FormalParsOpt!=null) FormalParsOpt.traverseBottomUp(visitor);
        if(VarDeclListsOptional!=null) VarDeclListsOptional.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodVoidDecl(\n");

        if(VoidTypeName!=null)
            buffer.append(VoidTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParsOpt!=null)
            buffer.append(FormalParsOpt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListsOptional!=null)
            buffer.append(VarDeclListsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodVoidDecl]");
        return buffer.toString();
    }
}
