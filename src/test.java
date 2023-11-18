import java.io.*;

public class test
{
    public String Name;
    public int Age;
    public static void main(String[] args) throws IOException
    {
        test a=new test();
        System.out.println(a.toString());
    }

    @Override
    public String toString()
    {
        return "该对象的名字是"+this.Name+",年龄是"+this.Age;
    }
    public test()
    {
        this.Name="张三";
        this.Age=20;
    }

}