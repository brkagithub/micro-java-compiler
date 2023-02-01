// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class WhileUnmatched extends Unmatched {

    private WhileKeyword WhileKeyword;
    private ConditionIfParen ConditionIfParen;
    private Unmatched Unmatched;

    public WhileUnmatched (WhileKeyword WhileKeyword, ConditionIfParen ConditionIfParen, Unmatched Unmatched) {
        this.WhileKeyword=WhileKeyword;
        if(WhileKeyword!=null) WhileKeyword.setParent(this);
        this.ConditionIfParen=ConditionIfParen;
        if(ConditionIfParen!=null) ConditionIfParen.setParent(this);
        this.Unmatched=Unmatched;
        if(Unmatched!=null) Unmatched.setParent(this);
    }

    public WhileKeyword getWhileKeyword() {
        return WhileKeyword;
    }

    public void setWhileKeyword(WhileKeyword WhileKeyword) {
        this.WhileKeyword=WhileKeyword;
    }

    public ConditionIfParen getConditionIfParen() {
        return ConditionIfParen;
    }

    public void setConditionIfParen(ConditionIfParen ConditionIfParen) {
        this.ConditionIfParen=ConditionIfParen;
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
        if(WhileKeyword!=null) WhileKeyword.accept(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.accept(visitor);
        if(Unmatched!=null) Unmatched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileKeyword!=null) WhileKeyword.traverseTopDown(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseTopDown(visitor);
        if(Unmatched!=null) Unmatched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileKeyword!=null) WhileKeyword.traverseBottomUp(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseBottomUp(visitor);
        if(Unmatched!=null) Unmatched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileUnmatched(\n");

        if(WhileKeyword!=null)
            buffer.append(WhileKeyword.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConditionIfParen!=null)
            buffer.append(ConditionIfParen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Unmatched!=null)
            buffer.append(Unmatched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileUnmatched]");
        return buffer.toString();
    }
}
