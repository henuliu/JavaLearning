package day05.Objcet;

public class TestClone
{
    public static void main(String[] args) throws CloneNotSupportedException
    {
        User u1 = new User("1001", "zhangsan", "wo666", new double[]{99.0, 99.5});
        //调用方法克隆得到一个新对象

        User u2 = (User) u1.clone();
        System.out.println(u2.getId());
        System.out.println(u2.getUsername());
        System.out.println(u2.getPassword());
        System.out.println(u2.getScores());


    }

}
