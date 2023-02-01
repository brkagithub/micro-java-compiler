// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class FParamDecl extends FormalParamDecl {

    private Type Type;
    private String fParamName;

    public FParamDecl (Type Type, String fParamName) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.fParamName=fParamName;
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getFParamName() {
        return fParamName;
    }

    public void setFParamName(String fParamName) {
        this.fParamName=fParamName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Type!=null) Type.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FParamDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+fParamName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FParamDecl]");
        return buffer.toString();
    }
}
