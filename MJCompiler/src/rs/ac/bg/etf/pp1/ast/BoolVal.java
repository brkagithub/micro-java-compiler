// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class BoolVal extends ConstValue {

    private String boo;

    public BoolVal (String boo) {
        this.boo=boo;
    }

    public String getBoo() {
        return boo;
    }

    public void setBoo(String boo) {
        this.boo=boo;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolVal(\n");

        buffer.append(" "+tab+boo);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolVal]");
        return buffer.toString();
    }
}
