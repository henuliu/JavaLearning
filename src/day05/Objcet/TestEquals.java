package day05.Objcet;

public class TestEquals
{
    public static void main(String[] args)
    {
        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge("1001");

        Student s2 = new Student();
        s2.setName("张三");
        s2.setAge("1001");

        System.out.println(s1 == s2); // 比较内存地址
        System.out.println(s1.equals(s2)); // 比较业务上是否相同 比如比较学号、姓名是否相同
    }
}
