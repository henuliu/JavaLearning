package day04.Polymorphism;

/*
 * 多态，指方法或对象有多种形态，建立在封装和继承的基础上
 * 1.方法的多态: 重写和重载就体现了多态
 * 2.对象的多态:
 * 一个对象的编译类型和运行类型可以不一致
 * 编译类型在定义对象时就确定了，不能改变
 * 编译类型看定义时等号的左边，运行类型看等号的右边
 */
// 当使用多态方式调用方法时，首先检查父类中是否有该方法，如果没有，则编译错误；如果有，再去调用子类的同名方法。
public class _polymorphism
{
    public static void main(String[] args)
    {
        show(new Cat());  // 以 Cat 对象调用 show 方法
        show(new Dog());  // 以 Dog 对象调用 show 方法

        Animal a = new Cat();  // 向上转型
        a.eat();               // 调用的是 Cat 的 eat
        Cat c = (Cat) a;        // 向下转型
        c.work();        // 调用的是 Cat 的 work
    }

    public static void show(Animal a)
    {
        a.eat();
        // 类型判断
        if (a instanceof Cat)
        {
            // 猫做的事情
            Cat c = (Cat) a;
            c.work();
        }
        else if (a instanceof Dog)
        {
            // 狗做的事情
            Dog c = (Dog) a;
            c.work();
        }
    }
}

abstract class Animal
{
    abstract void eat();
}

class Cat extends Animal
{
    public void eat()
    {
        System.out.println("吃鱼");
    }

    public void work()
    {
        System.out.println("抓老鼠");
    }
}

class Dog extends Animal
{
    public void eat()
    {
        System.out.println("吃骨头");
    }

    public void work()
    {
        System.out.println("看家");
    }
}
