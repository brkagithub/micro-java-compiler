// generated with ast extension for cup
// version 0.8
// 17/0/2023 20:41:12


package rs.ac.bg.etf.pp1.ast;

public class ClassList extends List {

    private List List;
    private ClassDeclaration ClassDeclaration;

    public ClassList (List List, ClassDeclaration ClassDeclaration) {
        this.List=List;
        if(List!=null) List.setParent(this);
        this.ClassDeclaration=ClassDeclaration;
        if(ClassDeclaration!=null) ClassDeclaration.setParent(this);
    }

    public List getList() {
        return List;
    }

    public void setList(List List) {
        this.List=List;
    }

    public ClassDeclaration getClassDeclaration() {
        return ClassDeclaration;
    }

    public void setClassDeclaration(ClassDeclaration ClassDeclaration) {
        this.ClassDeclaration=ClassDeclaration;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(List!=null) List.accept(visitor);
        if(ClassDeclaration!=null) ClassDeclaration.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(List!=null) List.traverseTopDown(visitor);
        if(ClassDeclaration!=null) ClassDeclaration.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(List!=null) List.traverseBottomUp(visitor);
        if(ClassDeclaration!=null) ClassDeclaration.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassList(\n");

        if(List!=null)
            buffer.append(List.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassDeclaration!=null)
            buffer.append(ClassDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassList]");
        return buffer.toString();
    }
}
