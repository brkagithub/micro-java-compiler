// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class VarDeclLists extends VarDeclListsOptional {

    private VarDeclListsOptional VarDeclListsOptional;
    private VarDeclList VarDeclList;

    public VarDeclLists (VarDeclListsOptional VarDeclListsOptional, VarDeclList VarDeclList) {
        this.VarDeclListsOptional=VarDeclListsOptional;
        if(VarDeclListsOptional!=null) VarDeclListsOptional.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
    }

    public VarDeclListsOptional getVarDeclListsOptional() {
        return VarDeclListsOptional;
    }

    public void setVarDeclListsOptional(VarDeclListsOptional VarDeclListsOptional) {
        this.VarDeclListsOptional=VarDeclListsOptional;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListsOptional!=null) VarDeclListsOptional.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListsOptional!=null) VarDeclListsOptional.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListsOptional!=null) VarDeclListsOptional.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclLists(\n");

        if(VarDeclListsOptional!=null)
            buffer.append(VarDeclListsOptional.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclLists]");
        return buffer.toString();
    }
}
