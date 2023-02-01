// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclListDerived1 extends ConstDeclList {

    private Type Type;
    private ConstDeclListNoSemi ConstDeclListNoSemi;

    public ConstDeclListDerived1 (Type Type, ConstDeclListNoSemi ConstDeclListNoSemi) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.ConstDeclListNoSemi=ConstDeclListNoSemi;
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public ConstDeclListNoSemi getConstDeclListNoSemi() {
        return ConstDeclListNoSemi;
    }

    public void setConstDeclListNoSemi(ConstDeclListNoSemi ConstDeclListNoSemi) {
        this.ConstDeclListNoSemi=ConstDeclListNoSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclListDerived1(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclListNoSemi!=null)
            buffer.append(ConstDeclListNoSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclListDerived1]");
        return buffer.toString();
    }
}
