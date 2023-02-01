// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorArr extends Designator {

    private DesignatorArrHelper DesignatorArrHelper;
    private Expr Expr;

    public DesignatorArr (DesignatorArrHelper DesignatorArrHelper, Expr Expr) {
        this.DesignatorArrHelper=DesignatorArrHelper;
        if(DesignatorArrHelper!=null) DesignatorArrHelper.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorArrHelper getDesignatorArrHelper() {
        return DesignatorArrHelper;
    }

    public void setDesignatorArrHelper(DesignatorArrHelper DesignatorArrHelper) {
        this.DesignatorArrHelper=DesignatorArrHelper;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorArrHelper!=null) DesignatorArrHelper.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorArrHelper!=null) DesignatorArrHelper.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorArrHelper!=null) DesignatorArrHelper.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArr(\n");

        if(DesignatorArrHelper!=null)
            buffer.append(DesignatorArrHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArr]");
        return buffer.toString();
    }
}
