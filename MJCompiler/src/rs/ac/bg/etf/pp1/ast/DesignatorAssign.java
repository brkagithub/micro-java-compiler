// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAssign extends DesignatorStatement {

    private Designator Designator;
    private Assignop Assignop;
    private DesignatorExprSemi DesignatorExprSemi;

    public DesignatorAssign (Designator Designator, Assignop Assignop, DesignatorExprSemi DesignatorExprSemi) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.DesignatorExprSemi=DesignatorExprSemi;
        if(DesignatorExprSemi!=null) DesignatorExprSemi.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public DesignatorExprSemi getDesignatorExprSemi() {
        return DesignatorExprSemi;
    }

    public void setDesignatorExprSemi(DesignatorExprSemi DesignatorExprSemi) {
        this.DesignatorExprSemi=DesignatorExprSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(DesignatorExprSemi!=null) DesignatorExprSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(DesignatorExprSemi!=null) DesignatorExprSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(DesignatorExprSemi!=null) DesignatorExprSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAssign(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorExprSemi!=null)
            buffer.append(DesignatorExprSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAssign]");
        return buffer.toString();
    }
}
