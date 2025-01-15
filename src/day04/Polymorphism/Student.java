package day04.Polymorphism;

public class Student extends People{

    @Override
    public void run()
    {
        System.out.println("我是student， 我有student的跑法!!!");
    }

    public void study()
    {
        System.out.println("我是student， 我有study方法...");
    }
}
