package day05.Abstract;

// 抽象类和普通相比，有得有失：得到的是拥有抽象方法的权利，失去的是new的能力
// 只要有抽象方法必抽象类
public abstract class _abstract
{
    public static void main(String[] args)
    {
        B b=new B();
        b.test();
    }
}

// 抽象类
abstract class A
{
    static String schoolName;
    //成员变量
    private String name;

    //构造方法
    public A()
    {

    }

    //抽象方法
    public abstract void test();

    //实例方法
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}

//B类继承A类，必须复写test方法
class B extends A
{
    @Override
    public void test()
    {
        System.out.println("我继承了抽象类A，并重写了抽象方法test()");
    }
}
