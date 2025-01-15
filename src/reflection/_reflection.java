package reflection;

/**
 * 反射: 加载类，并允许以编程的方式解剖类中的各种成分(成员变量、方法、构造器等)
 * 1.加载类，获取类的字节码
 * 2.获取类的构造器：constructor
 * 3.获取类的成员变量：field对象
 * 4.获取类的成员方法：method对象
 */
public class _reflection
{
    public _reflection()
    {

    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        Class s = People.class;
        System.out.println(s.getName()); // 全类名: reflection.People
        System.out.println(s.getSimpleName()); // 简名: People

        // 在使用 Java 的反射机制时，通常需要提供类的全限定类名来获取 Class 对象
        Class s1 = Class.forName("reflection.People");

        // s1和s指向同一个对象
        System.out.println(s1 == s); // true

        People people = new People();
        Class s2 = people.getClass();
        // s2和s1指向同一个对象
        System.out.println(s2 == s1); // true
    }
}
