package day02;

//构造器,用于创建和初始化对象。它在创建对象时被自动调用，并且没有返回值
/*
构造器的名称与类名完全相同。
构造器没有返回类型，甚至没有 void 关键字。
构造器可以有参数，也可以没有参数。
如果没有提供构造器，Java 会自动生成一个默认的无参构造器，该构造器不执行任何操作。
如果提供了带参数的构造器，但没有提供无参构造器，那么只能通过指定参数来创建对象
*/
public class _Constructor
{
    private String name;
    private int age;
    public _Constructor(String name,int age)
    {
        this.name=name;
        this.age=age;
    }

    public static void main(String[] args)
    {
        _Constructor constructor=new _Constructor("张三",20);
        System.out.println("姓名为："+constructor.name+",年龄为:"+constructor.age);
    }
}
