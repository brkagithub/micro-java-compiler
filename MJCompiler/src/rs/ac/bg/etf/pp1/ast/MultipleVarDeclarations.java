// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class MultipleVarDeclarations extends VarDeclListNoSemi {

    private VarDeclListNoSemi VarDeclListNoSemi;
    private VarDeclaration VarDeclaration;

    public MultipleVarDeclarations (VarDeclListNoSemi VarDeclListNoSemi, VarDeclaration VarDeclaration) {
        this.VarDeclListNoSemi=VarDeclListNoSemi;
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.setParent(this);
        this.VarDeclaration=VarDeclaration;
        if(VarDeclaration!=null) VarDeclaration.setParent(this);
    }

    public VarDeclListNoSemi getVarDeclListNoSemi() {
        return VarDeclListNoSemi;
    }

    public void setVarDeclListNoSemi(VarDeclListNoSemi VarDeclListNoSemi) {
        this.VarDeclListNoSemi=VarDeclListNoSemi;
    }

    public VarDeclaration getVarDeclaration() {
        return VarDeclaration;
    }

    public void setVarDeclaration(VarDeclaration VarDeclaration) {
        this.VarDeclaration=VarDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.accept(visitor);
        if(VarDeclaration!=null) VarDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.traverseTopDown(visitor);
        if(VarDeclaration!=null) VarDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.traverseBottomUp(visitor);
        if(VarDeclaration!=null) VarDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleVarDeclarations(\n");

        if(VarDeclListNoSemi!=null)
            buffer.append(VarDeclListNoSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclaration!=null)
            buffer.append(VarDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleVarDeclarations]");
        return buffer.toString();
    }
}
