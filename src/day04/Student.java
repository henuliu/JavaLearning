package day04;

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

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

}


