package day05.Interface;

public class Dog extends Animal implements CatchMouseAble
{
    // 子类继承抽象父类，需要实现父类的所有抽象方法
    @Override
    public void cry()
    {
        System.out.println("狗 ， 汪汪!!!");
    }

    // 子类 扩展父类方法 表达自己的个性
    public void keepHouse()
    {
        System.out.println("狗天生就会看家...");
    }

    // 实现接口
    @Override
    public void catchMouse()
    {
        System.out.println("狗经过努力学习 ，掌握了自己抓老鼠本领 , 也能抓老鼠!!!");
    }
}
