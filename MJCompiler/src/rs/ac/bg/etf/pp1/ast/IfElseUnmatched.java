// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class IfElseUnmatched extends Unmatched {

    private IfHelper IfHelper;
    private ConditionIfParen ConditionIfParen;
    private Matched Matched;
    private ElseHelper ElseHelper;
    private Unmatched Unmatched;

    public IfElseUnmatched (IfHelper IfHelper, ConditionIfParen ConditionIfParen, Matched Matched, ElseHelper ElseHelper, Unmatched Unmatched) {
        this.IfHelper=IfHelper;
        if(IfHelper!=null) IfHelper.setParent(this);
        this.ConditionIfParen=ConditionIfParen;
        if(ConditionIfParen!=null) ConditionIfParen.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
        this.ElseHelper=ElseHelper;
        if(ElseHelper!=null) ElseHelper.setParent(this);
        this.Unmatched=Unmatched;
        if(Unmatched!=null) Unmatched.setParent(this);
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

    public Unmatched getUnmatched() {
        return Unmatched;
    }

    public void setUnmatched(Unmatched Unmatched) {
        this.Unmatched=Unmatched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfHelper!=null) IfHelper.accept(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
        if(ElseHelper!=null) ElseHelper.accept(visitor);
        if(Unmatched!=null) Unmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfHelper!=null) IfHelper.traverseTopDown(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
        if(ElseHelper!=null) ElseHelper.traverseTopDown(visitor);
        if(Unmatched!=null) Unmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfHelper!=null) IfHelper.traverseBottomUp(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        if(ElseHelper!=null) ElseHelper.traverseBottomUp(visitor);
        if(Unmatched!=null) Unmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseUnmatched(\n");

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

        if(Unmatched!=null)
            buffer.append(Unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseUnmatched]");
        return buffer.toString();
    }
}
