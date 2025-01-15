package day05.InnerClass;

/*
 * 匿名内部类是一种特殊的局部内部类；所谓匿名，指的是程序员不需要为这个类声明名字
 * 1.匿名内部类本质上是一个没有名字的子类对象、或者接口的实现类对象
 * 2.简化了创建子类对象、实现类对象的书写格式
 * 3.在调用方法时，当方法的形参是一个接口或者抽象类，为了简化代码书写，而直接传递匿名内部类对象给方法。这样就可以少写一个类
 */
public class AnonymousInnerClass
{
    public static void main(String[] args)
    {
        Animal animal=new Animal()
        {
            @Override
            public void cry()
            {
                System.out.println("Animal的匿名内部类");
            }
        };
        animal.cry();
    }
}
abstract class Animal
{
    public abstract void cry();

}