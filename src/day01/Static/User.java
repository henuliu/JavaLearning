package day01.Static;

public class User
{
    public static int number;

    //每次创建对象时，number自增一下
    public User()
    {
        User.number++;
    }
}