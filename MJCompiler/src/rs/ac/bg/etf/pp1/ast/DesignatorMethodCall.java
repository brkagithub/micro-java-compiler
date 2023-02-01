// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorMethodCall extends DesignatorStatement {

    private DesignatorMethodCallHelper DesignatorMethodCallHelper;
    private NonOptionalActPars NonOptionalActPars;

    public DesignatorMethodCall (DesignatorMethodCallHelper DesignatorMethodCallHelper, NonOptionalActPars NonOptionalActPars) {
        this.DesignatorMethodCallHelper=DesignatorMethodCallHelper;
        if(DesignatorMethodCallHelper!=null) DesignatorMethodCallHelper.setParent(this);
        this.NonOptionalActPars=NonOptionalActPars;
        if(NonOptionalActPars!=null) NonOptionalActPars.setParent(this);
    }

    public DesignatorMethodCallHelper getDesignatorMethodCallHelper() {
        return DesignatorMethodCallHelper;
    }

    public void setDesignatorMethodCallHelper(DesignatorMethodCallHelper DesignatorMethodCallHelper) {
        this.DesignatorMethodCallHelper=DesignatorMethodCallHelper;
    }

    public NonOptionalActPars getNonOptionalActPars() {
        return NonOptionalActPars;
    }

    public void setNonOptionalActPars(NonOptionalActPars NonOptionalActPars) {
        this.NonOptionalActPars=NonOptionalActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorMethodCallHelper!=null) DesignatorMethodCallHelper.accept(visitor);
        if(NonOptionalActPars!=null) NonOptionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorMethodCallHelper!=null) DesignatorMethodCallHelper.traverseTopDown(visitor);
        if(NonOptionalActPars!=null) NonOptionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorMethodCallHelper!=null) DesignatorMethodCallHelper.traverseBottomUp(visitor);
        if(NonOptionalActPars!=null) NonOptionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorMethodCall(\n");

        if(DesignatorMethodCallHelper!=null)
            buffer.append(DesignatorMethodCallHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NonOptionalActPars!=null)
            buffer.append(NonOptionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorMethodCall]");
        return buffer.toString();
    }
}
