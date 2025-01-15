package day04.Extends;

/*
 * 继承可以解决代码复用，当多个类存在相同的属性和方法时，可以从这些类中抽象出父类，在父类中定义它们
 * 所有的子类不需要重新定义这些属性和方法，只需要extends来声明继承父类即可
 * 非私有的属性和方法可以在子类直接访问，但是私有属性和方法不能直接访问，要通过父类提高的公共方法去访问
 * instanceof 是 Java 中的一个关键字，用于判断对象是否是指定类型或其子类型的实例。它返回一个布尔值
 */

public class _extends
{
    public static void main(String[] args)
    {
        Teacher t=new Teacher("张三",20,"Java");
        System.out.println("子类间接访问父类的name:"+t.getName());

        // t是People的实例
        System.out.println(t instanceof People); //true
    }
}

class Teacher extends People
{
    private String skill;
    public Teacher(String name,int age,String skill)
    {
        /*
            子类中访问构造器，都会先调用父类构造器，再执行自己
            在使用this或super调用构造方法时，必须将它们作为构造方法中的第一条语句。
            如果在构造方法中先执行其他语句，再调用this或super，则会出现编译错误
        */
        super(name,age);
        this.skill=skill;
        System.out.println();
        System.out.println("Teacher的有参构造器执行了");
    }

}