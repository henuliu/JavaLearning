package day05.Interface;

/*
 * 1.接口是用来被类实现（implements）的，称之为实现类
 * 2.接口的方法就是做事的能力 怎么做接口不管，只是定义标准，怎么实现是厂商的事情
 * 3.一个类是可以实现多个接口的，实现接口必须重写所有接口的全部抽象方法，否则这个类也必须是抽象类
 * 4.接口弥补了单继承的不足，同时可以轻松实现在多种业务场景之间的切换
 * 5.Java中，接口不能直接实例化，但可以通过其实现类来创建对象
 * 6.接口中的成员变量默认是 public static final 的常量，是类级别的常量，不可修改，供实现该接口的类使用
 */
public class _interface implements CatchMouseAble
{
    @Override
    public void catchMouse()
    {
        System.out.println("抓老鼠");
    }

    public static void main(String[] args)
    {

    }
}
