package day05.Polymorphism;

public class Teacher extends People{

    @Override
    public void run()
    {
        System.out.println("我是teacher， 我有teacher的跑法!!!");
    }

    public void teach()
    {
        System.out.println("我是teacher ， 我会teach方法");
    }
}
