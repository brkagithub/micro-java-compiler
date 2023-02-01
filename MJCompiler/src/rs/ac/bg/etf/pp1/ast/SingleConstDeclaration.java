// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class SingleConstDeclaration extends ConstDeclListNoSemi {

    private ConstEnd ConstEnd;

    public SingleConstDeclaration (ConstEnd ConstEnd) {
        this.ConstEnd=ConstEnd;
        if(ConstEnd!=null) ConstEnd.setParent(this);
    }

    public ConstEnd getConstEnd() {
        return ConstEnd;
    }

    public void setConstEnd(ConstEnd ConstEnd) {
        this.ConstEnd=ConstEnd;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstEnd!=null) ConstEnd.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstEnd!=null) ConstEnd.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstEnd!=null) ConstEnd.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConstDeclaration(\n");

        if(ConstEnd!=null)
            buffer.append(ConstEnd.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConstDeclaration]");
        return buffer.toString();
    }
}
