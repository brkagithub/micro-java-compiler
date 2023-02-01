// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class FactorFun extends Factor {

    private Designator Designator;
    private NonOptionalActPars NonOptionalActPars;

    public FactorFun (Designator Designator, NonOptionalActPars NonOptionalActPars) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.NonOptionalActPars=NonOptionalActPars;
        if(NonOptionalActPars!=null) NonOptionalActPars.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
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
        if(Designator!=null) Designator.accept(visitor);
        if(NonOptionalActPars!=null) NonOptionalActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(NonOptionalActPars!=null) NonOptionalActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(NonOptionalActPars!=null) NonOptionalActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorFun(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(NonOptionalActPars!=null)
            buffer.append(NonOptionalActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorFun]");
        return buffer.toString();
    }
}
