// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class MultipleConstDeclarations extends ConstDeclListNoSemi {

    private ConstDeclComma ConstDeclComma;
    private ConstDeclListNoSemi ConstDeclListNoSemi;

    public MultipleConstDeclarations (ConstDeclComma ConstDeclComma, ConstDeclListNoSemi ConstDeclListNoSemi) {
        this.ConstDeclComma=ConstDeclComma;
        if(ConstDeclComma!=null) ConstDeclComma.setParent(this);
        this.ConstDeclListNoSemi=ConstDeclListNoSemi;
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.setParent(this);
    }

    public ConstDeclComma getConstDeclComma() {
        return ConstDeclComma;
    }

    public void setConstDeclComma(ConstDeclComma ConstDeclComma) {
        this.ConstDeclComma=ConstDeclComma;
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
        if(ConstDeclComma!=null) ConstDeclComma.accept(visitor);
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclComma!=null) ConstDeclComma.traverseTopDown(visitor);
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclComma!=null) ConstDeclComma.traverseBottomUp(visitor);
        if(ConstDeclListNoSemi!=null) ConstDeclListNoSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleConstDeclarations(\n");

        if(ConstDeclComma!=null)
            buffer.append(ConstDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclListNoSemi!=null)
            buffer.append(ConstDeclListNoSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleConstDeclarations]");
        return buffer.toString();
    }
}
