package day02;

/*
 * 变量作用域(分为局部变量和全局变量)
 * 1. 局部变量一般是指类成员方法中的变量
 * 2. 全局变量又称属性,作用域为整个类体
 * 3.
 */
public class _VarScope
{

    public static void main(String[] args)
    {
        Cat cat=new Cat();
        cat.cry();
        cat.eat();
    }
}

class Cat
{
    // age为全局变量，作用域为整个cat类，cry、eat等方法使用属性
    int age=10;

    public void cry()
    {
        // n和name为局部变量，作用域为cry方法
        int n=10;
        String name="Jack";
        System.out.println("在cry方法中使用属性age="+age);
    }

    public void eat()
    {
        System.out.println("在eat方法中使用属性age="+age);
    }
}