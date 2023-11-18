package day04;
//子类中访问构造器，都会先调用父类构造器，再执行自己
public class _extends
{
    public static void main(String[] args)
    {
        Teacher t=new Teacher("张三",20,"Java");
    }
}
class People
{
    private String name;
    private int age;
    public People()
    {

    }
    public People(String name,int age)
    {
        this.name=name;
        this.age=age;
        System.out.println("People的有参构造器执行了");
    }

}

class Teacher extends People
{
    private String skill;
    public Teacher(String name,int age,String skill)
    {
        /*
        在使用this或super调用构造方法时，必须将它们作为构造方法中的第一条语句。
        如果在构造方法中先执行其他语句，再调用this或super，则会出现编译错误
        */
        super(name,age);
        this.skill=skill;
        System.out.println("Teacher的有参构造器执行了");
    }

}