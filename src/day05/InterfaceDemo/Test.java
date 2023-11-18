package day05.InterfaceDemo;

interface Driver
{
    void drive();
}

interface Singer
{
    void sing();
}

class Student
{

}

//A类是Student的子类，同时也实现了Dirver接口和Singer接口
class A extends Student implements Driver, Singer
{
    @Override
    public void drive()
    {

    }

    @Override
    public void sing()
    {

    }
}

public class Test
{
    public static void main(String[] args)
    {
        //想唱歌的时候，A类对象就表现为Singer类型
        Singer s = new A();
        s.sing();

        //想开车的时候，A类对象就表现为Driver类型
        Driver d = new A();
        d.drive();
    }
}
