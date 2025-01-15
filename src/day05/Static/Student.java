package day05.Static;

import lombok.Data;

/*
 * 代码块根据有无static修饰分为两种：静态代码块、实例代码块
 * 1.静态代码块
 * 格式: static{}
 * 特点: 类加载时自动执行，由于类只会加载一次，因此静态代码块也只执行一次
 * 作用: 完成类的初始化，例如：对类变量的初始化赋值,静态代码块不需要创建对象就能够执行
 * 2.实例代码块
 * 格式: {}
 * 特点: 每次创建对象时，执行实例代码块，在构造器前执行
 * 作用: 和构造器一样，完成类的初始化，例如：对实例变量的进行赋值,实例代码块需要创建对象才能执行
 */
@Data
public class Student
{
    //静态变量
    static String schoolName;

    //实例变量
    int age;
    String name;

    //实例代码块：实例代码块会执行在每一个构造方法之前
    {
        System.out.println("实例代码块执行了~~");
        age = 18;
        System.out.println("有人创建了对象：" + this);
    }

    // 静态代码块，只会执行一次，并且在类加载时自动执行，静态代码块比实例代码块优先执行
    static
    {
        System.out.println("静态代码块执行了~~");
        schoolName = "公司";
    }

    public Student()
    {
        System.out.println("无参数构造器执行了~~");
    }

    public Student(String name)
    {
        System.out.println("有参数构造器执行了~~");
    }

    public Student(String name, int age)
    {
        this.name = name;
        this.age = age;
    }


    //Student类会默认继承Object类，此时可以重写toString()方法
    @Override
    public String toString()
    {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args)
    {
        Student student = new Student("panda", 21);
    }
}


