// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class MethodTypeDecl extends MethodDeclaration {

    private MethodTypeName MethodTypeName;
    private FormalParsOpt FormalParsOpt;
    private VarDeclListsOptional VarDeclListsOptional;
    private StatementList StatementList;

    public MethodTypeDecl (MethodTypeName MethodTypeName, FormalParsOpt FormalParsOpt, VarDeclListsOptional VarDeclListsOptional, StatementList StatementList) {
        this.MethodTypeName=MethodTypeName;
        if(MethodTypeName!=null) MethodTypeName.setParent(this);
        this.FormalParsOpt=FormalParsOpt;
        if(FormalParsOpt!=null) FormalParsOpt.setParent(this);
        this.VarDeclListsOptional=VarDeclListsOptional;
        if(VarDeclListsOptional!=null) VarDeclListsOptional.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodTypeName getMethodTypeName() {
        return MethodTypeName;
    }

    public void setMethodTypeName(MethodTypeName MethodTypeName) {
        this.MethodTypeName=MethodTypeName;
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
        if(MethodTypeName!=null) MethodTypeName.accept(visitor);
        if(FormalParsOpt!=null) FormalParsOpt.accept(visitor);
        if(VarDeclListsOptional!=null) VarDeclListsOptional.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodTypeName!=null) MethodTypeName.traverseTopDown(visitor);
        if(FormalParsOpt!=null) FormalParsOpt.traverseTopDown(visitor);
        if(VarDeclListsOptional!=null) VarDeclListsOptional.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodTypeName!=null) MethodTypeName.traverseBottomUp(visitor);
        if(FormalParsOpt!=null) FormalParsOpt.traverseBottomUp(visitor);
        if(VarDeclListsOptional!=null) VarDeclListsOptional.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodTypeDecl(\n");

        if(MethodTypeName!=null)
            buffer.append(MethodTypeName.toString("  "+tab));
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
        buffer.append(") [MethodTypeDecl]");
        return buffer.toString();
    }
}
