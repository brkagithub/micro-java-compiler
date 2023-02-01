// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class VarDeclListDerived1 extends VarDeclList {

    private Type Type;
    private VarDeclListNoSemi VarDeclListNoSemi;

    public VarDeclListDerived1 (Type Type, VarDeclListNoSemi VarDeclListNoSemi) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.VarDeclListNoSemi=VarDeclListNoSemi;
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public VarDeclListNoSemi getVarDeclListNoSemi() {
        return VarDeclListNoSemi;
    }

    public void setVarDeclListNoSemi(VarDeclListNoSemi VarDeclListNoSemi) {
        this.VarDeclListNoSemi=VarDeclListNoSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(VarDeclListNoSemi!=null) VarDeclListNoSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclListDerived1(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclListNoSemi!=null)
            buffer.append(VarDeclListNoSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclListDerived1]");
        return buffer.toString();
    }
}
