// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class MultipleFormalPars extends FormalPars {

    private FormalParamComma FormalParamComma;
    private FormalPars FormalPars;

    public MultipleFormalPars (FormalParamComma FormalParamComma, FormalPars FormalPars) {
        this.FormalParamComma=FormalParamComma;
        if(FormalParamComma!=null) FormalParamComma.setParent(this);
        this.FormalPars=FormalPars;
        if(FormalPars!=null) FormalPars.setParent(this);
    }

    public FormalParamComma getFormalParamComma() {
        return FormalParamComma;
    }

    public void setFormalParamComma(FormalParamComma FormalParamComma) {
        this.FormalParamComma=FormalParamComma;
    }

    public FormalPars getFormalPars() {
        return FormalPars;
    }

    public void setFormalPars(FormalPars FormalPars) {
        this.FormalPars=FormalPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParamComma!=null) FormalParamComma.accept(visitor);
        if(FormalPars!=null) FormalPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamComma!=null) FormalParamComma.traverseTopDown(visitor);
        if(FormalPars!=null) FormalPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamComma!=null) FormalParamComma.traverseBottomUp(visitor);
        if(FormalPars!=null) FormalPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultipleFormalPars(\n");

        if(FormalParamComma!=null)
            buffer.append(FormalParamComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalPars!=null)
            buffer.append(FormalPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultipleFormalPars]");
        return buffer.toString();
    }
}
