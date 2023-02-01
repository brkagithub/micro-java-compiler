// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class WhileMatched extends Matched {

    private WhileKeyword WhileKeyword;
    private ConditionIfParen ConditionIfParen;
    private Matched Matched;

    public WhileMatched (WhileKeyword WhileKeyword, ConditionIfParen ConditionIfParen, Matched Matched) {
        this.WhileKeyword=WhileKeyword;
        if(WhileKeyword!=null) WhileKeyword.setParent(this);
        this.ConditionIfParen=ConditionIfParen;
        if(ConditionIfParen!=null) ConditionIfParen.setParent(this);
        this.Matched=Matched;
        if(Matched!=null) Matched.setParent(this);
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

    public Matched getMatched() {
        return Matched;
    }

    public void setMatched(Matched Matched) {
        this.Matched=Matched;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(WhileKeyword!=null) WhileKeyword.accept(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.accept(visitor);
        if(Matched!=null) Matched.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(WhileKeyword!=null) WhileKeyword.traverseTopDown(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseTopDown(visitor);
        if(Matched!=null) Matched.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(WhileKeyword!=null) WhileKeyword.traverseBottomUp(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseBottomUp(visitor);
        if(Matched!=null) Matched.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("WhileMatched(\n");

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

        if(Matched!=null)
            buffer.append(Matched.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [WhileMatched]");
        return buffer.toString();
    }
}
