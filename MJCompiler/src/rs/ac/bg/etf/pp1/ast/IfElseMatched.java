// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class IfElseMatched extends Matched {

    private IfHelper IfHelper;
    private ConditionIfParen ConditionIfParen;
    private Matched Matched;
    private ElseHelper ElseHelper;
    private Matched Matched1;

    public IfElseMatched (IfHelper IfHelper, ConditionIfParen ConditionIfParen, Matched Matched, ElseHelper ElseHelper, Matched Matched1) {
        this.IfHelper=IfHelper;
        if(IfHelper!=null) IfHelper.setParent(this);
        this.ConditionIfParen=ConditionIfParen;
        if(ConditionIfParen!=null) ConditionIfParen.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.ElseHelper=ElseHelper;
        if(ElseHelper!=null) ElseHelper.setParent(this);
        this.Matched1=Matched1;
        if(Matched1!=null) Matched1.setParent(this);
    }

    public IfHelper getIfHelper() {
        return IfHelper;
    }

    public void setIfHelper(IfHelper IfHelper) {
        this.IfHelper=IfHelper;
    }

    public ConditionIfParen getConditionIfParen() {
        return ConditionIfParen;
    }

    public void setConditionIfParen(ConditionIfParen ConditionIfParen) {
        this.ConditionIfParen=ConditionIfParen;
    }

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public ElseHelper getElseHelper() {
        return ElseHelper;
    }

    public void setElseHelper(ElseHelper ElseHelper) {
        this.ElseHelper=ElseHelper;
    }

    public Matched getMatched1() {
        return Matched1;
    }

    public void setMatched1(Matched Matched1) {
        this.Matched1=Matched1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfHelper!=null) IfHelper.accept(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
        if(ElseHelper!=null) ElseHelper.accept(visitor);
        if(Matched1!=null) Matched1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfHelper!=null) IfHelper.traverseTopDown(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(ElseHelper!=null) ElseHelper.traverseTopDown(visitor);
        if(Matched1!=null) Matched1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfHelper!=null) IfHelper.traverseBottomUp(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(ElseHelper!=null) ElseHelper.traverseBottomUp(visitor);
        if(Matched1!=null) Matched1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseMatched(\n");

        if(IfHelper!=null)
            buffer.append(IfHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionIfParen!=null)
            buffer.append(ConditionIfParen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseHelper!=null)
            buffer.append(ElseHelper.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Matched1!=null)
            buffer.append(Matched1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseMatched]");
        return buffer.toString();
    }
}
