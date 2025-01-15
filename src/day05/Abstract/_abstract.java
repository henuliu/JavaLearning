package day05.Abstract;

/*
 * 关键字abstract是抽象的意思，它可以修饰类也可以修饰方法
 * 1.抽象类是不能创建对象的，但是它可以作为父类让子类继承。而且子类必须重写父类的所有抽象方法
 * 2.子类继承父类如果不复写父类的抽象方法，要想不出错，这个子类也必须是抽象类
 * 3.只要有抽象方法必为抽象类
 * 4.抽象类通常用于定义一个模板，其中包含一些通用的实现和一些需要子类实现的具体行为
 * 5.反过来用，当我们不知道系统未来具体的业务实现时，我们可以先定义抽象类，将来让子类去实现，以方便系统的扩展。
 */
public abstract class _abstract
{
    public static void main(String[] args)
    {
        B b = new B();
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
