// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class IfUnmatched extends Unmatched {

    private IfHelper IfHelper;
    private ConditionIfParen ConditionIfParen;
    private Statement Statement;

    public IfUnmatched (IfHelper IfHelper, ConditionIfParen ConditionIfParen, Statement Statement) {
        this.IfHelper=IfHelper;
        if(IfHelper!=null) IfHelper.setParent(this);
        this.ConditionIfParen=ConditionIfParen;
        if(ConditionIfParen!=null) ConditionIfParen.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
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

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfHelper!=null) IfHelper.accept(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfHelper!=null) IfHelper.traverseTopDown(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfHelper!=null) IfHelper.traverseBottomUp(visitor);
        if(ConditionIfParen!=null) ConditionIfParen.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfUnmatched(\n");

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

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfUnmatched]");
        return buffer.toString();
    }
}
