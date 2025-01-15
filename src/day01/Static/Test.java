package day01.Static;

public class Test
{
    public static void main(String[] args)
    {
        //创建4个对象
        new User();
        new User();
        new User();
        new User();

        //查看系统创建了多少个User对象
        System.out.println("系统创建的User对象个数：" + User.number);
    }
}