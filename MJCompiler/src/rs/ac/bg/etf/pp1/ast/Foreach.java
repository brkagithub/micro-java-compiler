// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class Foreach extends Matched {

    private Designator Designator;
    private String identForeach;
    private ForeachMapHelper ForeachMapHelper;
    private ForeachStmtHelper ForeachStmtHelper;

    public Foreach (Designator Designator, String identForeach, ForeachMapHelper ForeachMapHelper, ForeachStmtHelper ForeachStmtHelper) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.identForeach=identForeach;
        this.ForeachMapHelper=ForeachMapHelper;
        if(ForeachMapHelper!=null) ForeachMapHelper.setParent(this);
        this.ForeachStmtHelper=ForeachStmtHelper;
        if(ForeachStmtHelper!=null) ForeachStmtHelper.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public String getIdentForeach() {
        return identForeach;
    }

    public void setIdentForeach(String identForeach) {
        this.identForeach=identForeach;
    }

    public ForeachMapHelper getForeachMapHelper() {
        return ForeachMapHelper;
    }

    public void setForeachMapHelper(ForeachMapHelper ForeachMapHelper) {
        this.ForeachMapHelper=ForeachMapHelper;
    }

    public ForeachStmtHelper getForeachStmtHelper() {
        return ForeachStmtHelper;
    }

    public void setForeachStmtHelper(ForeachStmtHelper ForeachStmtHelper) {
        this.ForeachStmtHelper=ForeachStmtHelper;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(ForeachMapHelper!=null) ForeachMapHelper.accept(visitor);
        if(ForeachStmtHelper!=null) ForeachStmtHelper.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(ForeachMapHelper!=null) ForeachMapHelper.traverseTopDown(visitor);
        if(ForeachStmtHelper!=null) ForeachStmtHelper.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(ForeachMapHelper!=null) ForeachMapHelper.traverseBottomUp(visitor);
        if(ForeachStmtHelper!=null) ForeachStmtHelper.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Foreach(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+identForeach);
        buffer.append("\n");

        if(ForeachMapHelper!=null)
            buffer.append(ForeachMapHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForeachStmtHelper!=null)
            buffer.append(ForeachStmtHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Foreach]");
        return buffer.toString();
    }
}
