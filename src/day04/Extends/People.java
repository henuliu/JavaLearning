package day04.Extends;

class People
{
    private String name;
    private int age;

    public People()
    {
        System.out.println("People的无参构造器执行了");
    }
    public People(String name,int age)
    {
        this.name=name;
        this.age=age;
        System.out.println("People的有参构造器执行了");
    }

    // 父类提供一个public方法
    public String getName()
    {
        return name;
    }

}